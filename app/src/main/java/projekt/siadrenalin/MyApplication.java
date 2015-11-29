package projekt.siadrenalin;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import projekt.data.Sport;
import projekt.data.TipSporta;
import projekt.util.Podatki;


public class MyApplication extends Application
{
	//public static final String SPORTI_FILE = "sporti.json";
	public static final String TIPSPORTA_FILE = "tipsporta.json";
	private boolean mExternalStorageWriteable;
	private boolean mExternalStorageAvailable;
	
	public List<TipSporta> allTipSporta;
	public TipSporta tipSporta;
    public Sport sport;
	public Locale myLocale;
	public String sFbShare;

	@Override
	public void onCreate()
	{
		super.onCreate();
		
		updateExternalStorageState();

		allTipSporta = null;
		setFirstLocale(getSharedPreferences("CommonPrefs", MODE_PRIVATE).getString("Language", "sl"));
		//loadTipSporta(TIPSPORTA_FILE);

		//listaSportov = podatki.getLista();

        //Uporabimo če želimo podatke iz aplikacije shraniti fizično na sd-kartico
		//saveTipSporta(TIPSPORTA_FILE, listaSportov);
	}

	public void setFirstLocale(String lang)
	{
		myLocale = new Locale(lang);
		Locale.setDefault(myLocale);
		Configuration config = new Configuration();
		config.locale = myLocale;
		getBaseContext().getResources().updateConfiguration(config,
				getBaseContext().getResources().getDisplayMetrics());

	}


	public boolean getPlayServices()
	{
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
		if (resultCode != ConnectionResult.SUCCESS)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
	}

	public boolean isInternetAvailable() {
		try {
			InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name

			if (ipAddr.equals("")) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			return false;
		}

	}

	public void setLocale(String jezik)
	{
		String languageToLoad  = jezik; // your language
	    myLocale = new Locale(languageToLoad); 
	    Locale.setDefault(myLocale);
	    Configuration config = new Configuration();
	    config.locale = myLocale;
	    getBaseContext().getResources().updateConfiguration(config, 
	      getBaseContext().getResources().getDisplayMetrics());
	}
		
	void updateExternalStorageState() {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state))
		{
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		}
		else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals((state)))
		{
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		}
		else
		{
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig){
	    super.onConfigurationChanged(newConfig);
	    if (myLocale != null){
	        newConfig.locale = myLocale;
	        Locale.setDefault(myLocale);
	        getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
	    }
	}

	public List<TipSporta> getAll() 
	{
		if(allTipSporta == null)
		{
			allTipSporta = loadTipSporta(TIPSPORTA_FILE);
			if(allTipSporta == null)
			{
				Podatki tmp = new Podatki();
				allTipSporta = tmp.UstvariListo();
			}
		}		
		return allTipSporta;
	}
	public Sport vrniSport(String id)
	{
		for(int i=0; i<allTipSporta.size(); i++)
		{
			for(int j=0; j<allTipSporta.get(i).getData().size(); j++)
			{
				if(id.equals(allTipSporta.get(i).getData().get(j).getId()))
				{
					return allTipSporta.get(i).getData().get(j);
				}
			}
		}
		return null;
	}
	public List<TipSporta> loadTipSporta(String name)
	{
		if(this.mExternalStorageAvailable)
		{
			try
			{
				File file = new File(this.getExternalFilesDir("SIADRENALIN_DATA"), "" + name);
				FileInputStream fstream = new FileInputStream(file);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				StringBuffer sb = new StringBuffer();
				List<TipSporta> lista = new ArrayList<TipSporta>();
				
				String strLine;
				while((strLine = br.readLine()) != null)
				{
					sb.append(strLine).append('\n');
				}
				br.close();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				Type listaObjektov = new TypeToken<ArrayList<TipSporta>>(){}.getType();
				lista = gson.fromJson(sb.toString(), listaObjektov);

				if(lista == null)
				{
					System.out.println("Error: fromJson Format error");
				}
				else
				{
					System.out.println("Uspešno nalaganje");
				}
				return lista;
			}
			catch(IOException e)
			{
				System.out.println("Error load TipSporta "+e.getMessage());
			}
		}
		return null;
	}
	
	public boolean saveTipSporta(String filename, List<TipSporta> list) 
	{
		if (this.mExternalStorageWriteable) 
		{
			File file = new File(this.getExternalFilesDir("SIADRENALIN_DATA"), filename);

                try
                {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    PrintWriter pw = new PrintWriter(file);

                    //System.out.println(gson.toJson(list));
                    pw.println(gson.toJson(list));
                    pw.close();
                    return true;
                }
                catch (IOException e)
                {
                    System.out.println("Error save! "+e.getMessage());
                }

		}
		return false;
	}
	
}
