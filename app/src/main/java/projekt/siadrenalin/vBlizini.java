package projekt.siadrenalin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siadrenalin.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import projekt.adapter.NavDrawerBase;
import projekt.data.Sport;
import projekt.data.TipSporta;
import projekt.model.AsyncResult;
import projekt.model.DownloadJSON;

public class vBlizini extends NavDrawerBase implements SeekBar.OnSeekBarChangeListener {

    private class Razdalja
    {
        Sport obSport;
        double sRazdalja;
        String sCas;

        public Razdalja(Sport obSport, double sRazdalja, String sCas) {
            this.obSport = obSport;
            this.sRazdalja = sRazdalja;
            this.sCas = sCas;
        }

        public Sport getObSport() {
            return obSport;
        }

        public double getsRazdalja() {
            return sRazdalja;
        }

        public String getsCas() {
            return sCas;
        }

        public void setObSport(Sport obSport) {
            this.obSport = obSport;
        }

        public void setsRazdalja(double sRazdalja) {
            this.sRazdalja = sRazdalja;
        }

        public void setsCas(String sCas) {
            this.sCas = sCas;
        }
    }

	public static final String TIPSPORTA_FILE = "tipsporta.json";
	MyApplication app;
	private List<TipSporta> allTipSporta;
	private SeekBar sbDistance;
	private GoogleMap map;
	private LatLng llMojaLokacija;
	private TextView labela;
    private TextView tvDstIzbira;
    private Circle mCircle = null;
    LinearLayout head;
    SlidingUpPanelLayout slidePanel;
    double radiusInMeters;
    Switch swZracnaDst;
    Activity aThis;
    List<Razdalja> lRazdalja;

    boolean bMarkerSelected = false;
    Marker mSelected;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.blizina);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vblizini);
        super.onCreateDrawer(this);
        app = ((MyApplication)this.getApplication());
        this.aThis = this;

        //Dobimo vse tipe športov iz MainActivity
        allTipSporta = app.getAll();

        lRazdalja = new ArrayList<Razdalja>();

        tvDstIzbira = (TextView)findViewById(R.id.text_DstIzbira);
        tvDstIzbira.setText(R.string.zracna_dst);
        swZracnaDst = (Switch)findViewById(R.id.sw_ZracnaDst);

        sbDistance = (SeekBar) this.findViewById(R.id.seekBar_km);
        sbDistance.setOnSeekBarChangeListener(this);

        radiusInMeters = 10000;

        //Label za prikaz izbire distance
        labela = (TextView) this.findViewById(R.id.textView_vrednost);
        labela.setText(radiusInMeters/1000 + "km");

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        //map.setMyLocationEnabled(true);

        head = (LinearLayout)findViewById(R.id.FlashBarLayout);
        slidePanel = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        slidePanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        slidePanel.setTouchEnabled(false);

        GPSTracker gpsTracker = new GPSTracker(this);
        double Latitude = gpsTracker.latitude;
        double Longitude = gpsTracker.longitude;
        gpsTracker.stopUsingGPS();

        llMojaLokacija = new LatLng(Latitude, Longitude);
        map.addMarker(new MarkerOptions().title(getApplicationContext().getResources().getString(R.string.moja_lokacija)).position(llMojaLokacija).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(llMojaLokacija, 8));

        Location A = new Location("A");
        A.setLatitude(Latitude);
        A.setLongitude(Longitude);

        //LatLng tmp2 = new LatLng(46.563785, 15.639484);
        //map.addMarker(new MarkerOptions().title("Moja lokacija").position(llMojaLokacija));

        /*Location A = new Location("A");
		A.setLatitude(Latitude);
		A.setLongitude(Longitude);*/
        LatLng llA = new LatLng(A.getLatitude(), A.getLongitude());

        CircleOptions circleOptions = new CircleOptions()
                .center(llA)   //set center
                .radius(radiusInMeters)   //set radius in meters
                .fillColor(0x440066CC)  //default
                .strokeColor(Color.BLUE)
                .strokeWidth(3);

        mCircle = map.addCircle(circleOptions);

        for(int i=0; i<allTipSporta.size(); i++)
        {
        	for(int j=0; j<allTipSporta.get(i).getData().size(); j++)
        	{
        		LatLng tmp = allTipSporta.get(i).getData().get(j).getKoordinate();
        		
        		Location B = new Location("B");
        		B.setLatitude(tmp.latitude);
        		B.setLongitude(tmp.longitude);
        		
        		double distance = B.distanceTo(A);
        		if(distance < sbDistance.getProgress()*1000)
        		{
        			map.addMarker(new MarkerOptions().title(allTipSporta.get(i).getData().get(j).getNaziv()).snippet(getApplicationContext().getResources().getString(R.string.razdalja)+": " + String.valueOf(distance / 1000).substring(0, 5) + "km").position(tmp));
        		}
                lRazdalja.add(new Razdalja(allTipSporta.get(i).getData().get(j), 0, ""));
        	}
        }

        swZracnaDst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {

                if(!app.isNetworkConnected())
                {
                    Toast.makeText(getApplicationContext(), R.string.off_internet, Toast.LENGTH_SHORT).show();
                    swZracnaDst.setChecked(true);
                    return;
                }

                map.clear();
                mSelected = null;
                bMarkerSelected = false;
                map.addMarker(new MarkerOptions().title(getApplicationContext().getResources().getString(R.string.moja_lokacija)).position(llMojaLokacija).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                sbDistance.setProgress(10);

                if(!b)
                {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(aThis);
                        alertDialog.setTitle(R.string.izrRazdalj_title) // your dialog title
                                .setMessage(R.string.izrRazdalj_msg) // a message above the buttons
                                .setPositiveButton(R.string.potrdi, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    /*Zaženemo google distance matrix*/
                                        new getDistance().execute();
                                    }
                                })
                                .setNegativeButton(R.string.preklic, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                }
            }
        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals(R.string.moja_lokacija))
                    return false;

                if (bMarkerSelected == false) {
                    if (mSelected != null)
                    {
                        mSelected.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }
                    mSelected = marker;
                    bMarkerSelected = true;
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else {
                    mSelected.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    mSelected = marker;
                    bMarkerSelected = false;
                }
                return false;
            }
        });
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(bMarkerSelected)
                {
                    mSelected.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    bMarkerSelected = false;
                    mSelected = null;
                }
            }
        });
    }

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		String vrednost = String.valueOf(progress);
        labela.setText(vrednost + " km");

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		//Toast.makeText(getBaseContext(), "    ", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
		GPSTracker gpsTracker = new GPSTracker(this);
		double Latitude = gpsTracker.latitude;
		double Longitude = gpsTracker.longitude;
		gpsTracker.stopUsingGPS();

		/*Location A = new Location("A");
        A.setLatitude(46.563785);
        A.setLongitude(15.639484);*/


		Location A = new Location("A");
		A.setLatitude(Latitude);
		A.setLongitude(Longitude);
        LatLng llA = new LatLng(A.getLatitude(), A.getLongitude());
		
		map.clear();
        mSelected = null;
        bMarkerSelected = false;

        map.addMarker(new MarkerOptions().title(getApplicationContext().getResources().getString(R.string.moja_lokacija)).position(llMojaLokacija).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        for(int i=0; i<lRazdalja.size(); i++)
        {
            LatLng tmp = lRazdalja.get(i).getObSport().getKoordinate();

            Location B = new Location("B");
            B.setLatitude(tmp.latitude);
            B.setLongitude(tmp.longitude);

            if(swZracnaDst.isChecked())
            {
                double distance = B.distanceTo(A);

                if(i==0)
                {
                    radiusInMeters = seekBar.getProgress()*1000;
                    CircleOptions circleOptions = new CircleOptions()
                            .center(llA)   //set center
                            .radius(radiusInMeters)   //set radius in meters
                            .fillColor(0x440066CC)  //default
                            .strokeColor(Color.BLUE)
                            .strokeWidth(3);

                    mCircle = map.addCircle(circleOptions);
                }
                if(distance < seekBar.getProgress()*1000)
                {
                    map.addMarker(new MarkerOptions().title(lRazdalja.get(i).getObSport().getNaziv()).snippet(R.string.razdalja+ ": " + String.valueOf(distance / 1000).substring(0, 5) + "km").position(tmp));
                }
            }
            else
            {
                if(lRazdalja.get(i).getsRazdalja() < seekBar.getProgress())
                {
                    map.addMarker(new MarkerOptions().title(lRazdalja.get(i).getObSport().getNaziv()).snippet(getApplicationContext().getResources().getString(R.string.razdalja)+ ": " + String.valueOf(lRazdalja.get(i).getsRazdalja()) + "km" + "\n" + getApplicationContext().getResources().getString(R.string.cas) +  ": " + lRazdalja.get(i).getsCas()).position(tmp));
                }
            }
        }
	}
    class getDistance extends AsyncTask<String, String, String>
    {
        ProgressDialog progDailog = new ProgressDialog(vBlizini.this);
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progDailog.setMessage(getApplicationContext().getResources().getString(R.string.pridobivanje_pod));
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            getMatrix();
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);

            progDailog.dismiss();
        }
    }

    private void getMatrix()
    {
        String sLokacija = String.valueOf(llMojaLokacija.latitude + "," + llMojaLokacija.longitude);
        String req = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +
                    sLokacija +
                    "&destinations=";

        for(int i=0; i<lRazdalja.size(); i++)
        {
            //lRazdalja.add(new Razdalja(allTipSporta.get(i).getData().get(j), "", ""));
            req = req + lRazdalja.get(i).getObSport().getKoordinate().latitude + "," + lRazdalja.get(i).getObSport().getKoordinate().longitude + "|";
        }

        new DownloadJSON(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                pokazi(object);
            }
        }).execute(req);
    }

    private void pokazi(JSONObject ob)
    {
        //OK
        try
        {
            JSONArray rows = ob.getJSONArray("elements");
            for (int r = 0; r < rows.length(); ++r)
            {
                JSONObject row = rows.getJSONObject(r);
                JSONObject columnsDistance = row.getJSONObject("distance");
                JSONObject columnsDuration = row.getJSONObject("duration");

                String Distance = columnsDistance.getString("text");
                double iDistance = Double.parseDouble(Distance.substring(0, Distance.lastIndexOf(" ")));

                String Duration = columnsDuration.getString("text");
                //int iDuration = Integer.parseInt(Duration.substring(Duration.lastIndexOf(" ")));

                lRazdalja.get(r).setsRazdalja(iDistance);
                lRazdalja.get(r).setsCas(Duration);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
