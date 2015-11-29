package projekt.siadrenalin;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.siadrenalin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import projekt.adapter.PriljubljeneArrayAdapter;
import projekt.data.Sport;
import projekt.data.TipSporta;
import projekt.model.SharedPrefs;

public class Priljubljene extends ListActivity{

	MyApplication app;
    SharedPrefs sharedPreference;
    private Context context;
	PriljubljeneArrayAdapter ar;
    String language;
    Locale myLocale;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (MyApplication) getApplication();

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.priljubljene);

        this.context = getApplicationContext();
        setFirstLocale(getSharedPreferences("CommonPrefs", MODE_PRIVATE).getString("Language", "sl"));

        //Objekt za shared prefs
        sharedPreference = new SharedPrefs();

		ar = new PriljubljeneArrayAdapter(context, vrniPriljubljene(), this);
		setListAdapter(ar);
	}

    @Override
    protected void onResume() {
        super.onResume();
        //setFirstLocale(getSharedPreferences("CommonPrefs", MODE_PRIVATE).getString("Language", ""));
        setTitle(R.string.priljubljene);
    }

    public void setFirstLocale(String lang)
    {
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //restartActivity();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }

    public boolean checkFavoriteItem(String checkProduct) {
        boolean check = false;
        List<String> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (String product : favorites) {
                if (product.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
    public  ArrayList<Sport> vrniPriljubljene()
    {
        //lsSport hrani objekte za končni prikaz
        ArrayList<Sport> lsSport = new ArrayList<Sport>();

        //lsSportTip hrani vse objekte
        List<TipSporta> lsSportTip = app.getAll();

        for(int i=0; i<lsSportTip.size(); i++)
        {
            for(int j=0; j<lsSportTip.get(i).getData().size(); j++)
            {
                if(checkFavoriteItem(lsSportTip.get(i).getData().get(j).getId()))
                {
                    lsSport.add(lsSportTip   .get(i).getData().get(j));
                }
            }
        }
        return lsSport;
    }
}
