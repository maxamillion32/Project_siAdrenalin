package projekt.siadrenalin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.siadrenalin.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import projekt.adapter.NavDrawerBase;
import projekt.data.Sport;
import projekt.data.TipSporta;
import projekt.model.DirectionsJSONParser;
import projekt.model.SharedPrefs;

/**
 * Created by Mitja on 31.3.2015.
 */
public class MapPane extends NavDrawerBase{

    //Objekti sporta
	private List<TipSporta> allTipSporta;
    private MyApplication app;
    private Sport obSport;
    private SharedPrefs sharedPref;
    private Context context;

    //Sliding panel elementi
    private SlidingUpPanelLayout slidePanel;
    private LinearLayout head;
    private TextView tv_naziv;
    private View ivCard;
    private ImageView favorite;

    //Zemljevid
    private GoogleMap map;
    boolean bMarkerSelected = false;
    Marker mSelected;
    Marker customMarker; //Marker za navigacijo
    ArrayList<LatLng> markerPoints;
    View marker;

    //Header elementi
    private Spinner spStart;
    private Spinner spCilj;
    private Button gumbRoute;

    private boolean bOdprtaPot = false;

    //Spinner
    ArrayAdapter<CharSequence> adapter_cilj;
    ArrayAdapter<CharSequence> adapter_start;
    final int RQS_GooglePlayServices = 0;



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mSelected = null;
        bMarkerSelected = false;

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        super.onCreateDrawer((Activity) this);
        this.context = getApplicationContext();
        app = (MyApplication) getApplication();

        // Initializing
        markerPoints = new ArrayList<LatLng>();

        allTipSporta = app.getAll();

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.map_info, null);

        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker arg0) {
                // TODO Auto-generated method stub
            }

            @SuppressWarnings("unchecked")
            @Override
            public void onMarkerDragEnd(Marker arg0) {
                // TODO Auto-generated method stub
                //map.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));
            }

            @Override
            public void onMarkerDrag(Marker arg0) {
                // TODO Auto-generated method stub
            }
        });

        slidePanel = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        slidePanel.setTouchEnabled(false);

        head = (LinearLayout)findViewById(R.id.FlashBarLayout);
        head.setVisibility(View.GONE);
        spStart = (Spinner) findViewById(R.id.sp_OdLokacije);
        spCilj = (Spinner) findViewById(R.id.sp_DoLokacije);
        favorite = (ImageView) findViewById(R.id.favorite);
        gumbRoute = (Button) findViewById(R.id.gumb_route);
        gumbRoute.setBackgroundColor(Color.GREEN);
        tv_naziv = (TextView)findViewById(R.id.text_naziv);
        ivCard = (View) findViewById(R.id.iv_card);

        sharedPref = new SharedPrefs();
        //Spinner start
        // Create an ArrayAdapter using the string array and a default spinner layout
        Resources res = getResources();
        String[] start_array = res.getStringArray(R.array.start_destinacije_array);

        adapter_start = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                new ArrayList(Arrays.asList(start_array)));

        // Specify the layout to use when the list of choices appears
        adapter_start.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Spinner cilj
        String[] cilj_array = res.getStringArray(R.array.cilj_destinacije_array);

        adapter_cilj = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                new ArrayList(Arrays.asList(cilj_array)));

        // Specify the layout to use when the list of choices appears
        adapter_cilj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.1427915, 15.0390682), 6));

        //Narišemo zemljevid
        reDrawMap();


        spCilj.setAdapter(adapter_cilj);
        spStart.setAdapter(adapter_start);
        spCilj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (bOdprtaPot) {
                    map.clear();
                    reDrawMap();
                    markerPoints.clear();
                    gumbRoute.setBackgroundColor(Color.GREEN);
                    gumbRoute.setText(R.string.potrdi);
                    bOdprtaPot = false;
                } else
                    return;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        spStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (bOdprtaPot) {
                    map.clear();
                    reDrawMap();
                    markerPoints.clear();
                    gumbRoute.setBackgroundColor(Color.GREEN);
                    gumbRoute.setText(R.string.potrdi);
                    bOdprtaPot = false;
                } else
                    return;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if (marker.getSnippet().equals("-1")) {
                    if (slidePanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
                        slidePanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

                    return true;
                }
                if (bMarkerSelected == false) {
                    if (mSelected != null)
                        mSelected.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    mSelected = marker;
                    bMarkerSelected = true;
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else {
                    mSelected.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    mSelected = marker;
                    bMarkerSelected = false;
                }

                obSport = vrniSport(marker.getSnippet());
                map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));

                slidePanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

                tv_naziv.setText(obSport.getNaziv());

                if (checkFavoriteItem(obSport)) {
                    favorite.setImageResource(R.drawable.ic_favorite_logo_active);
                } else {
                    favorite.setImageResource(R.drawable.ic_favorite_logo_metal);
                }

                return true;
            }
        });


        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                slidePanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

            }
        });

        gumbRoute.setText(R.string.potrdi);
        gumbRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(spCilj.getSelectedItemPosition() == 0 || spStart.getSelectedItemPosition() == 0)
                {
                    return;
                }

                if(!app.isNetworkConnected())
                {
                    Toast.makeText(context, R.string.off_internet, Toast.LENGTH_SHORT).show();
                    return;
                }

                if(bOdprtaPot)
                {
                    markerPoints.clear();
                    map.clear();
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.1427915,15.0390682), 8));

                    for(int i=0; i<allTipSporta.size(); i++)
                    {
                        for(int j=0; j<allTipSporta.get(i).getData().size(); j++)
                        {
                            LatLng tmp = allTipSporta.get(i).getData().get(j).getKoordinate();

                            map.addMarker(new MarkerOptions().title(allTipSporta.get(i).getData().get(j).getNaziv()).snippet(allTipSporta.get(i).getData().get(j).getId()).position(tmp));
                        }
                    }
                    gumbRoute.setBackgroundColor(Color.GREEN);
                    gumbRoute.setText(R.string.potrdi);
                    bOdprtaPot = false;
                }
                else
                {
                    map.clear();
                    Sport obStart = new Sport();
                    double Latitude;
                    double Longitude;
                    int izbran = spStart.getSelectedItemPosition();

                    if(izbran == 1)
                    {
                        GPSTracker gpsTracker = new GPSTracker(context);
                        if(gpsTracker.isGPSEnabled == false)
                        {
                            Toast.makeText(getApplicationContext(), R.string.off_gps, Toast.LENGTH_LONG).show();
                            reDrawMap();
                            return;
                        }
                        else if(gpsTracker.isNetworkEnabled == false)
                        {
                            Toast.makeText(getApplicationContext(), R.string.off_internet, Toast.LENGTH_LONG).show();
                            reDrawMap();
                            return;
                        }
                        Latitude = gpsTracker.latitude;
                        Longitude = gpsTracker.longitude;
                        gpsTracker.stopUsingGPS();

                        LatLng moja = new LatLng(Latitude, Longitude);

                        map.addMarker(new MarkerOptions().title(getApplicationContext().getResources().getString(R.string.moja_lokacija)).position(moja).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).snippet("-1"));
                        obStart.setKoordinate(moja);
                        markerPoints.add(moja);
                    }
                    else if(izbran == 2) //Murska Sobota
                    {
                        Latitude = 46.6533476;
                        Longitude = 16.1712551;
                        map.addMarker(new MarkerOptions().title("Murska Sobota").snippet("-1").position(new LatLng(Latitude, Longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        markerPoints.add(new LatLng(Latitude, Longitude));
                    }
                    else if(izbran == 3) //Ptuj
                    {
                        Latitude = 46.4221986;
                        Longitude = 15.8709167;
                        map.addMarker(new MarkerOptions().title("Ptuj").snippet("-1").position(new LatLng(Latitude, Longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        markerPoints.add(new LatLng(Latitude, Longitude));
                    }
                    else if(izbran == 4) //Maribor
                    {
                        Latitude = 46.5535399;
                        Longitude = 15.6445498;
                        map.addMarker(new MarkerOptions().title("Maribor").snippet("-1").position(new LatLng(Latitude, Longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        markerPoints.add(new LatLng(Latitude, Longitude));
                    }
                    else if(izbran == 5) //Celje
                    {
                        Latitude = 46.2358323;
                        Longitude = 15.2546036;
                        map.addMarker(new MarkerOptions().title("Celje").snippet("-1").position(new LatLng(Latitude, Longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        markerPoints.add(new LatLng(Latitude, Longitude));
                    }
                    else if(izbran == 6) //Novo Mesto
                    {
                        Latitude = 45.8035885;
                        Longitude = 15.1696856;
                        map.addMarker(new MarkerOptions().title("Novo Mesto").snippet("-1").position(new LatLng(Latitude, Longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        markerPoints.add(new LatLng(Latitude, Longitude));
                    }
                    else if(izbran == 7) //Ljubljana
                    {
                        Latitude = 46.0661174;
                        Longitude = 14.5320991;
                        map.addMarker(new MarkerOptions().title("Ljubljana").snippet("-1").position(new LatLng(Latitude, Longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        markerPoints.add(new LatLng(Latitude, Longitude));
                    }
                    else if(izbran == 7) //Koper
                    {
                        Latitude = 45.5405794;
                        Longitude = 13.7251549;
                        map.addMarker(new MarkerOptions().title("Koper").snippet("-1").position(new LatLng(Latitude, Longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        markerPoints.add(new LatLng(Latitude, Longitude));
                    }
                    else
                    {
                        String izbranStart = spStart.getSelectedItem().toString();
                        String[] ena = izbranStart.split("\\|");
                        obStart = vrniSport(ena[0]);

                        //Latitude = obStart.getKoordinate().latitude;
                        //Longitude = obStart.getKoordinate().longitude;
                    }

                    String izbranCilj = spCilj.getSelectedItem().toString();
                    String[] dva = izbranCilj.split("\\|");
                    Sport obCilj = vrniSport(dva[0]);

                    if(spStart.getSelectedItemPosition() < 2 || spStart.getSelectedItemPosition() > 7)
                    {
                        map.addMarker(new MarkerOptions().title(obStart.getNaziv()).snippet(obStart.getId()).position(obStart.getKoordinate()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        //markerPoints.add(obStart.getKoordinate());
                    }
                    map.addMarker(new MarkerOptions().title(obCilj.getNaziv()).snippet(obCilj.getId()).position(obCilj.getKoordinate()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    markerPoints.add(obCilj.getKoordinate());

                    //Najprej se izvede klic na google directions API

                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);

                    gumbRoute.setBackgroundColor(Color.RED);
                    gumbRoute.setText(R.string.pocisti);
                    bOdprtaPot = true;
                }
            }
        });

        Button buttonClose = (Button)findViewById(R.id.iv_close);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidePanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                mSelected.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.1427915, 15.0390682), 8));
                mSelected =  null;
                bMarkerSelected = false;
            }
        });

        ivCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app.sport = obSport;
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), Lokacija.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (resultCode == ConnectionResult.SUCCESS){

        }else
        {
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices).show();
        }
    }

    private  void reDrawMap()
    {
        for(int i=0; i<allTipSporta.size(); i++)
        {
            for(int j=0; j<allTipSporta.get(i).getData().size(); j++)
            {
                LatLng tmp = allTipSporta.get(i).getData().get(j).getKoordinate();

                map.addMarker(new MarkerOptions().title(allTipSporta.get(i).getData().get(j).getNaziv()).snippet(allTipSporta.get(i).getData().get(j).getId()).position(tmp));

                //Nastavimo/napolnimo adapterje za navigacijo
                adapter_start.add(allTipSporta.get(i).getData().get(j).getId()+ "|" +allTipSporta.get(i).getData().get(j).getNaziv());
                adapter_start.notifyDataSetChanged();

                adapter_cilj.add(allTipSporta.get(i).getData().get(j).getId()+ "|" +allTipSporta.get(i).getData().get(j).getNaziv());
                adapter_cilj.notifyDataSetChanged();
            }
        }
    }

    public Sport vrniSport(String id)
    {
        Sport ob_sport = new Sport();

        for(int i=0; i<allTipSporta.size(); i++)
        {
            for(int j=0; j<allTipSporta.get(i).getData().size(); j++)
            {
                if(allTipSporta.get(i).getData().get(j).getId().equals(id))
                {
                    ob_sport =  allTipSporta.get(i).getData().get(j);
                }
            }
        }

        return ob_sport;
    }
    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Sport checkProduct) {
        boolean check = false;
        List<String> favorites = sharedPref.getFavorites(context);
        if (favorites != null) {
            for (String product : favorites) {
                if (product.equals(checkProduct.getId())) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager =(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();

        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        String mode = "mode=driving"; //mode=driving

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters+ "&" + mode;

        return url;
    }
    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception in url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        ProgressDialog progDailog = new ProgressDialog(MapPane.this);

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progDailog.setMessage(context.getResources().getString(R.string.kalkulacija_poti));
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
            progDailog.dismiss();
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        ProgressDialog progDailog = new ProgressDialog(MapPane.this);

        TextView tvPotAvto;
        TextView tvRazdalja;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            tvRazdalja = (TextView)marker.findViewById(R.id.text_razdalja);
            tvPotAvto = (TextView)marker.findViewById(R.id.text_potAvto);

            progDailog.setMessage(context.getResources().getString(R.string.dokoncevanje));
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if(result.size()<1){
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // Pomikamo se skozi celotni rezultat navigacije
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Pridobimo pot na indeksu i
                List<HashMap<String, String>> path = result.get(i);

                // Pridobimo korake poti na indeksu i
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    if(j==0){    // Pridobimo razdaljo
                        distance = (String)point.get("distance");
                        continue;
                    }else if(j==1){ // Pridobimo čas vožnje
                        duration = (String)point.get("duration");
                        continue;
                    }

                    //Iz posameznega koraka pridobimo točke oz. koordinate
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    //Koordinate dodamo v seznam koordinat oz. točk
                    points.add(position);
                }

                // Vse korake i-te poti z pripadajočimi točkami dodatmo v končni
                // seznam točk na zemljevidu ter nastavimo ustrezno debelino in
                // barvo za izris poti
                lineOptions.addAll(points);
                lineOptions.width(3);
                lineOptions.color(Color.RED);
            }

            //tvDistanceDuration.setText("Distance:"+distance + ", Duration:"+duration);
            double latMid = (markerPoints.get(0).latitude + markerPoints.get(1).latitude)/2;
            double lngMid = (markerPoints.get(0).longitude + markerPoints.get(1).longitude)/2;

            LatLng llSredina = new LatLng(latMid, lngMid);

            tvRazdalja.setText(distance);

            tvPotAvto.setText(duration);

            customMarker = map.addMarker(new MarkerOptions()
                    .position(llSredina)
                    .title("")
                    .snippet("-1")
                    .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getApplicationContext(), marker))).draggable(true));

            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);

            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            builder.include(markerPoints.get(0));
            builder.include(markerPoints.get(1));

            LatLngBounds bounds = builder.build();

            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 20);

            map.animateCamera(cu);

            progDailog.dismiss();
        }
    }
}