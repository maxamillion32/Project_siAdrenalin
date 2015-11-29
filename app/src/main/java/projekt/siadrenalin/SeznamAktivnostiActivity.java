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

import projekt.adapter.AktivnostiArrayAdapter;
import projekt.data.TipSporta;

public class SeznamAktivnostiActivity extends ListActivity
{
	MyApplication app;
	AktivnostiArrayAdapter ar;
    ListView izbranSport;

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setTitle(R.string.seznam_aktivnosti);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		app = (MyApplication) getApplication();
		ar = new AktivnostiArrayAdapter(this, app.getAll());

		//SharedPreferences settings = getSharedPreferences("languague", MODE_PRIVATE);
		//String lng = settings.getString("languague", "");

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle(R.string.seznam_aktivnosti);

        izbranSport = getListView();
        izbranSport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TipSporta tmp = (TipSporta) getListAdapter().getItem(i);
                app.tipSporta = tmp;
                final Intent intent = new Intent(getBaseContext(), SeznamLokacijActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });

		setListAdapter(ar);
		this.getListView().setLongClickable(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
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
		
		TipSporta tmp = (TipSporta) getListAdapter().getItem(position);
		app.tipSporta = tmp;
		final Intent intent = new Intent(this, SeznamLokacijActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
	}
	
}
