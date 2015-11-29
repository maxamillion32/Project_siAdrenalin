package projekt.siadrenalin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.model.LatLng;
import com.siadrenalin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import projekt.data.Sport;
import projekt.data.TipSporta;
import projekt.model.AsyncResult;
import projekt.model.DownloadJSON;


/**
 * Created by Mitja on 30.4.2015.
 */
public class IntroActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 0;
    MyApplication app;
    Activity act;
    JSONObject t;
    private String gqlGetOcene = "https://spreadsheets.google.com/tq?tqx=out:json&tq=select%20C%2C%20avg(D)%2C%20avg(E)%2C%20avg(F)%2C%20avg(G)%20group%20by%20C%20label%20C%20%27id_sporta%27%2C%20avg(D)%20%27ocena_druzine%27%2C%20avg(E)%20%27ocena_urejenost%27%2C%20avg(F)%20%27ocena_cene%27%2C%20avg(G)%20%27ocena_zabava%27&key=1m1eU1I09VCeVlE0qCqyFKnYGC9g_knkHV3DIx_akFlE";
    private String sMainTable = "https://docs.google.com/spreadsheets/d/1XtE377HK4jJvySOQlg4JH3I560uiLwUvzJwz8El72QM/gviz/tq?tqx=out:json";
    private JSONObject JSONTipSporta;
    private String[] sLinki;
    private ArrayList<String> lsJSONSporti;
    private ArrayList<TipSporta> lsTipSporta; //hrani samo posamezne tipe sportov
    private ArrayList<String> lsJSONTipSporta; //hrani celotni JSON string
    private ProgressDialog progDailog;
    private boolean bNoData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JSONTipSporta = new JSONObject();
        t = new JSONObject();
        sLinki = new String[25];
        for(int i=0; i<sLinki.length; i++)
        {
            sLinki[i] = " ";
        }

        lsJSONTipSporta = new ArrayList<>();
        lsTipSporta = new ArrayList<>();
        lsJSONSporti = new ArrayList<>();

        app = (MyApplication) this.getApplication();
        act = this;
        setContentView(R.layout.activity_intro);

        progDailog = new ProgressDialog(IntroActivity.this, R.style.MyTheme);
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        progDailog.setCancelable(false);
        progDailog.show();

        Window window = progDailog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        File file = new File(getExternalFilesDir("SIADRENALIN_DATA"), "tipsporta.json");
        if(!file.exists() && !app.isNetworkConnected())
        {
            bNoData = true;
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(IntroActivity.this);
            alertDialog.setTitle(R.string.pozor) // your dialog title
                    .setMessage(getApplicationContext().getResources().getString(R.string.noData)) // a message above the buttons
                    .setNegativeButton(R.string.potrdi, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }});
            alertDialog.setCancelable(false);
            alertDialog.show();
        }

        if (app.isNetworkConnected()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new LoadThings().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new LoadTipSportaData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new LoadThings().execute();
                new LoadTipSportaData().execute();
            }
        } else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!bNoData)
                    {progDailog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    overridePendingTransition(0, 0);}
                    else
                    {

                    }
                }
            }, 3000);

        }
    }

    private void processJson(JSONObject object) {

        try {
            String jsonString = object.toString();
            FileOutputStream fos = openFileOutput("ocene.json", Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class LoadThings extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... aurl) {
            new DownloadJSON(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                    processJson(object);

                }
            }).execute(gqlGetOcene);
            return null;
        }

        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
        }
    }

    class LoadTipSportaData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... aurl) {

            InputStream is = null;

            try {
                URL url = new URL(sMainTable);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                // Starts the query
                conn.connect();
                int responseCode = conn.getResponseCode();
                is = conn.getInputStream();

                String contentAsString = convertStreamToString(is);

                int start = contentAsString.indexOf("{", contentAsString.indexOf("{") + 1);
                int end = contentAsString.lastIndexOf("}");

                //String jsonResponse = contentAsString.substring(start, end);
                lsJSONTipSporta.add(contentAsString.substring(start, end));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }


        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);

            for (int ind = 0; ind < lsJSONTipSporta.size(); ind++) {
                JSONObject table = null;
                try {
                    table = new JSONObject(lsJSONTipSporta.get(ind));
                    JSONArray rows = table.getJSONArray("rows");

                    for (int r = 0; r < rows.length(); ++r) {
                        JSONObject row = rows.getJSONObject(r);
                        JSONArray columns = row.getJSONArray("c");

                        TipSporta tmp = new TipSporta();
                        //ID
                        tmp.setId(columns.getJSONObject(0).getString("v"));
                        //naziv
                        tmp.setNaziv(columns.getJSONObject(1).getString("v"));

                        if (lsTipSporta.size() == 0) {
                            lsTipSporta.add(tmp);
                        } else {
                            if (lsTipSporta.get(lsTipSporta.size() - 1).getId().equals(columns.getJSONObject(0).getString("v"))) {
                                tmp = null;
                            } else {
                                lsTipSporta.add(tmp);
                            }
                        }

                        sLinki[r] = columns.getJSONObject(3).getString("v");

                        //lsTipSporta.add(tmp);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new LoadSportData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new LoadSportData().execute();
            }
            /*for(int i=0; i<sLinki.length; i++)
            {

            }*/
            //new LoadSportData().execute();
            //new LoadSportData().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
            //STANJE - vsi podatki iz glavne tabele (main_table) so prene�eni
        }
    }


    class LoadSportData extends AsyncTask<String, String, String> {
        //ProgressDialog progDailog = new ProgressDialog(IntroActivity.this, R.style.MyTheme);
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... aurl) {

            for (int i = 0; i < sLinki.length; i++) {
                InputStream is = null;
                if (!sLinki[i].equals(" ")) {

                    try {
                        URL url = new URL(sLinki[i]);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setReadTimeout(10000 /* milliseconds */);
                        conn.setConnectTimeout(15000 /* milliseconds */);
                        conn.setRequestMethod("GET");
                        conn.setDoInput(true);
                        // Starts the query
                        conn.connect();
                        int responseCode = conn.getResponseCode();
                        is = conn.getInputStream();

                        String contentAsString = convertStreamToString(is);

                        int start = contentAsString.indexOf("{", contentAsString.indexOf("{") + 1);
                        int end = contentAsString.lastIndexOf("}");

                        //String jsonResponse = contentAsString.substring(start, end);
                        lsJSONSporti.add(contentAsString.substring(start, end));

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            return null;
        }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);

            //STANJE - vsi podatki prenešeni
            for (int ind = 0; ind < lsJSONSporti.size(); ind++) {
                JSONObject table = null;
                try {
                    table = new JSONObject(lsJSONSporti.get(ind));
                    JSONArray rows = table.getJSONArray("rows");

                    for (int r = 0; r < rows.length(); ++r) {
                        JSONObject row = rows.getJSONObject(r);
                        JSONArray columns = row.getJSONArray("c");

                        Sport tmp = new Sport();
                        //ID
                        tmp.setId(columns.getJSONObject(1).getString("v") + columns.getJSONObject(0).getString("v").substring(1));
                        //naziv
                        tmp.setNaziv(columns.getJSONObject(2).getString("v"));
                        //opis
                        tmp.setOpis(columns.getJSONObject(3).getString("v"));
                        //telefon
                        tmp.setStevilka(columns.getJSONObject(4).getString("v"));
                        //email
                        tmp.setEmail(columns.getJSONObject(5).getString("v"));
                        //naslov
                        tmp.setNaslov(columns.getJSONObject(6).getString("v"));
                        //adrenalin
                        tmp.setAdrenalin(Double.parseDouble(columns.getJSONObject(7).getString("v")));
                        //koordinate
                        String[] sKoor = columns.getJSONObject(8).getString("v").split(",");
                        tmp.setKoordinate(new LatLng(Double.parseDouble(sKoor[0]), Double.parseDouble(sKoor[1])));
                        //odpiralni_cas
                        tmp.setOdpiralni(columns.getJSONObject(9).getString("v"));
                        //cenik
                        tmp.setCenik(columns.getJSONObject(10).getString("v"));

                        //logotip
                        String urlLogotip = columns.getJSONObject(11).getString("v");

                        File file = new File(getExternalFilesDir("SIADRENALIN_DATA"), "tipsporta.json");
                        if (file.exists()) {
                            app.allTipSporta = app.loadTipSporta("tipsporta.json");
                            if (app.vrniSport(tmp.getId()).getLogotipUrl().equals(urlLogotip)) {
                                tmp.setLogotipUrl(urlLogotip);
                                tmp.setLogotip(app.vrniSport(tmp.getId()).getLogotip());
                            } else {
                                tmp.setLogotip(new downloadImg().execute(urlLogotip, tmp.getId() + "_" + "logo" + ".png").get());
                                tmp.setLogotipUrl(urlLogotip);
                            }
                        } else {
                            tmp.setLogotip(new downloadImg().execute(urlLogotip, tmp.getId() + "_" + "logo" + ".png").get());
                            tmp.setLogotipUrl(urlLogotip);
                        }


                        //slika galerije
                        String urlSlika = columns.getJSONObject(12).getString("v");
                        if (file.exists()) {
                            if (app.vrniSport(tmp.getId()).getSlikaUrl().equals(urlSlika)) {
                                tmp.setSlikaUrl(urlSlika);
                                tmp.setSlika(app.vrniSport(tmp.getId()).getSlika());
                            } else {
                                tmp.setSlika(new downloadImg().execute(urlSlika, tmp.getId() + "_" + "slika" + ".png").get());
                                tmp.setSlikaUrl(urlSlika);
                            }
                        } else {
                            tmp.setSlika(new downloadImg().execute(urlSlika, tmp.getId() + "_" + "slika" + ".png").get());
                            tmp.setSlikaUrl(urlSlika);
                        }

                        for (int x = 0; x < lsTipSporta.size(); x++) {
                            if (lsTipSporta.get(x).getId().equals(columns.getJSONObject(1).getString("v"))) {
                                lsTipSporta.get(x).vstaviSport(tmp);
                                break;
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            //Manjka, da nastavimo dobljene podatke za ustrezne
            app.saveTipSporta("tipsporta.json", lsTipSporta);
            app.allTipSporta = app.loadTipSporta("tipsporta.json");

            progDailog.dismiss();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

    class downloadImg extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String filepath = "";
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                //urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory().getAbsoluteFile();
                String filename = params[1];
                Log.i("Local filename:", "" + filename);
                File file = new File(getExternalFilesDir("SIADRENALIN_DATA"), filename);
                if (file.createNewFile()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                    Log.i("Progress:", "downloadedSize:" + downloadedSize + "totalSize:" + totalSize);
                }
                fileOutput.close();
                if (downloadedSize == totalSize) filepath = file.getPath();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                filepath = null;
                e.printStackTrace();
            }
            Log.i("filepath:", " " + filepath);
            return filepath;
        }
    }
}


