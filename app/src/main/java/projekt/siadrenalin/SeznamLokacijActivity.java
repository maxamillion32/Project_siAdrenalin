package projekt.siadrenalin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.siadrenalin.R;

import projekt.adapter.LokacijeArrayAdapter;
import projekt.data.Sport;


public class SeznamLokacijActivity extends ListActivity{

    MyApplication app;
    LokacijeArrayAdapter ar;
    ListView izbranSport;

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.seznam_lokacij);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (MyApplication) getApplication();
        ar = new LokacijeArrayAdapter(this, app.tipSporta.getData());
        setListAdapter(ar);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.seznam_lokacij);

        izbranSport = getListView();
        izbranSport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sport tmp = (Sport) getListAdapter().getItem(i);
                app.sport = tmp;

                final Intent intent = new Intent(getBaseContext(), Lokacija.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });


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
    public void onBackPressed() {
        // finish() is called in super: we only override this method to be able to override the transition
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
    public void onClick(View v) {

    }
}
