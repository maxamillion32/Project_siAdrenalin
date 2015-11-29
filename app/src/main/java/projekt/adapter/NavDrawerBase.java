package projekt.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.siadrenalin.R;

import java.util.ArrayList;
import java.util.List;

import projekt.model.NavItem;
import projekt.siadrenalin.MainActivity;
import projekt.siadrenalin.MapPane;
import projekt.siadrenalin.MyApplication;
import projekt.siadrenalin.Priljubljene;
import projekt.siadrenalin.vBlizini;

/**
 * Created by Mitja on 12.5.2015.
 */
public class NavDrawerBase extends Activity implements AdapterView.OnItemClickListener
{
    MyApplication app;
    public DrawerLayout drawerLayout;
    public ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private Activity sendingAct;

    public static final Integer ICONS[] = {
            R.drawable.icon_back,
            R.drawable.seznam, //Home
            R.drawable.zemljevid, //Zemljevid
            R.drawable.vblizini, //V blizini
            R.drawable.favorite_ikona, //Favorite
             };
    List<NavItem> rowItems;

    protected void onCreateDrawer(Activity sendingActivity)
    {
        this.sendingAct = sendingActivity;
        app = (MyApplication) this.getApplication();
        // R.id.drawer_layout should be in every activity with exactly the same id.
        drawerLayout = (DrawerLayout) findViewById(R.id.layout_navigation);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        rowItems = new ArrayList<NavItem>();
        boolean bPlayServices = app.getPlayServices();

        for (int i = 0; i < ICONS.length; i++) {

            if(bPlayServices)
            {
                NavItem item = new NavItem(ICONS[i]);
                rowItems.add(item);
            }
            else if((bPlayServices == false && i==3) || (bPlayServices == false && i==4))
            {

            }
        }

        drawerToggle = new ActionBarDrawerToggle((Activity) this, drawerLayout, R.drawable.ic_drawer, 0, 0)
        {
            public void onDrawerClosed(View view) {}
            public void onDrawerOpened(View drawerView) {}
        };
        drawerLayout.setDrawerListener(drawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerList = (ListView) findViewById(R.id.lv_navigation);


        NavAdapter adapter = new NavAdapter(this,R.layout.nav_item, rowItems);
        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int izbran = Integer.parseInt(Integer.toString(position));
        Intent intent = new Intent();
        switch (izbran)
        {
            case 0:
                onBackPressed();
                break;
            case 1:
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case 2:
                intent.setClass(getApplicationContext(), MapPane.class);
                if(sendingAct.getClass().equals(MapPane.class)) {
                    Toast.makeText(getApplicationContext(), "Se že nahajate tu", Toast.LENGTH_SHORT).show();
                    break;
                }
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case 3:
                intent.setClass(getApplicationContext(), vBlizini.class);
                if(sendingAct.getClass().equals(vBlizini.class)) {
                    Toast.makeText(getApplicationContext(), "Se že nahajate tu", Toast.LENGTH_SHORT).show();
                    break;
                }
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case 4:
                intent.setClass(getApplicationContext(),Priljubljene.class);
                if(sendingAct.getClass().equals(Priljubljene.class)) {
                    Toast.makeText(getApplicationContext(), "Se že nahajate tu", Toast.LENGTH_SHORT).show();
                    break;
                }
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            default:
                break;
        }
    }
}

