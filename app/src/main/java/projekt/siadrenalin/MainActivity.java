package projekt.siadrenalin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.siadrenalin.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends Activity {

    MyApplication app;
    Activity act;


    String[] quotes = {
            "Vaš mobilni vodič po ekstremni plati Slovenije.",
            "One way to get the most out of life is to look upon it as an adventure.",
            "Life is either a great adventure or nothing.",
            "Life is an adventure, it's not a package tour.",
            "Risk-taking is a great adventure. And life should be full of adventures.",
            "Life is full of adventure. There's no such thing as a clear pathway.",
            "Življenje je kot ljubezen, ni lahko in mirno stanje, ampak težka in čudovita pustolovščina.",
            "Pogum ni odsotnost strahu, temveč zmaga nad njim. Pogumen človek ni tisti, ki strahu ne čuti, ampak tisti, ki strah premaga.",
            "Es sind immer die Abenteurer, die große Dinge vollbringen.",
            "Das Leben ist und birgt Abenteuer, sie sind das Gewürz im Einerlei.",
            "Veliko veselja in adrenalinskih užitkov z aplikacijo siAdrenalin"
    };

    String[] avtors = {
            "siAdrenalin",
            "William Feather",
            "Helen Keller",
            "Eckhart Tolle",
            "Herbie Hancock",
            "Guy Laliberte",
            "Andre Fayol",
            "Nelson Mandela",
            "Charles de Secondat",
            "Marietta Grade",
            "siAdrenalin"
    };

    Random rand = new Random();
    Locale myLocale;
    boolean bPrvic = true;
    RelativeLayout layoutMain;

    //Meni
    private TextView quote;
    private TextView quoteAuthor;

    final int RQS_GooglePlayServices = 0;

    @Override
    public void onBackPressed() {

        System.exit(0);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getActionBar().hide();
        setContentView(R.layout.activity_main2);

        app = (MyApplication) this.getApplication();

        act = this;

        //setFirstLocale(getSharedPreferences("CommonPrefs", MODE_PRIVATE).getString("Language", "sl"));

        if (!app.getPlayServices())
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle(R.string.pozor) // your dialog title
                    .setMessage(getString(R.string.playServiceMsg)) // a message above the buttons
                    .setIcon(R.drawable.megaphone_icon)// the icon besides the title you have to change it to the icon/image you have.
                    .setPositiveButton(R.string.potrdi, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
                            GooglePlayServicesUtil.getErrorDialog(resultCode, act, RQS_GooglePlayServices).show();
                        }

                    }).setNegativeButton(R.string.preklic, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    LinearLayout gumbZemljevid = (LinearLayout)findViewById(R.id.layout_zemljevid);
                    gumbZemljevid.setVisibility(View.GONE);

                    LinearLayout gumbVBlizini = (LinearLayout)findViewById(R.id.layout_vblizini);
                    gumbVBlizini.setVisibility(View.GONE);

                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }

        int iRand = rand.nextInt(quotes.length);
        quote = (TextView)findViewById(R.id.text_quote);
        quote.setText("\"" + quotes[iRand] + "\"");

        quoteAuthor = (TextView)findViewById(R.id.text_quoteAuthor);
        quoteAuthor.setText(" - " + avtors[iRand]);

        layoutMain = (RelativeLayout)findViewById(R.id.Rel_layout_main);

        double width = getResources().getDisplayMetrics().widthPixels/1.1;

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)layoutMain.getLayoutParams();

        params.width = (int) width;

        layoutMain.setLayoutParams(params);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle your other action bar items...
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Set correct language (default or chosen)
        Locale locale = new Locale(myLocale.getLanguage());
        Locale.setDefault(locale);
        Configuration config = new Configuration(newConfig); // get Modifiable Config from actual config changed
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // Pass any configuration change to the drawer toggls
    }

    public void odpriSeznam(View v) {

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SeznamAktivnostiActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void odpriKarto(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MapPane.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void odpriBlizina(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, vBlizini.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void odpriPrijavo(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Prijava.class);
        startActivity(intent);
    }

    public  void odpriOsiAdrenalin(View v)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, AboutActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void odpriPriljubljene(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Priljubljene.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void deliFacebook(View v)
    {
        if(app.isNetworkConnected())
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle(R.string.facebook_share) // your dialog title
                    .setMessage(R.string.twitterMsg) // a message above the buttons
                    .setIcon(R.drawable.ic_action_facebook)// the icon besides the title you have to change it to the icon/image you have.
                    .setPositiveButton(R.string.potrdi, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            app.sFbShare = getApplicationContext().getResources().getString(R.string.facebookMsg);
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(), Deli.class);
                            startActivity(intent);
                        }

                    }).setNegativeButton(R.string.preklic, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), R.string.off_internet, Toast.LENGTH_SHORT).show();
        }

    }

    public void deliTwitter(View v)
    {
        if(app.isNetworkConnected())
        {
            AlertDialog.Builder alertDialogTwitter = new AlertDialog.Builder(MainActivity.this);
            alertDialogTwitter.setTitle(R.string.twitter_share) // your dialog title
                    .setMessage(getString(R.string.twitterMsg)) // a message above the buttons
                    .setIcon(R.drawable.ic_action_twitter)// the icon besides the title you have to change it to the icon/image you have.
                    .setPositiveButton(R.string.potrdi, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deliTwitter();
                        }

                    }).setNegativeButton(R.string.preklic, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            alertDialogTwitter.show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), R.string.off_internet, Toast.LENGTH_SHORT).show();
        }
    }

    public void deliTwitter()
    {
        String tweetUrl="";
        try
        {
            tweetUrl = "https://twitter.com/intent/tweet?text=" +
                    URLEncoder.encode(getApplicationContext().getResources().getString(R.string.twitterMsg), "UTF-8")+
                    URLEncoder.encode("#siAdrenalin #IfeelsLOVEnia", "UTF-8");


        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        Intent intent = new Intent();
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

        // Narrow down to official Twitter app, if available:
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                intent.setPackage(info.activityInfo.packageName);
            }
        }
        startActivity(intent);
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

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void saveLocale(String lang)
    {
        bPrvic = false;
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void Deli(View v) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.facebook_share) // your dialog title
                .setMessage(getString(R.string.facebookMsg)) // a message above the buttons
                .setIcon(R.drawable.facebook_logo)// the icon besides the title you have to change it to the icon/image you have.
                .setPositiveButton(R.string.potrdi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        app.sFbShare = getApplicationContext().getResources().getString(R.string.facebookMsg);
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, Deli.class);
                        startActivity(intent);
                    }

                }).setNegativeButton(R.string.preklic, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        alertDialog.show();
    }

    public void NastaviAngleski(View v) {
        setLocale("en");
    }

    public void NastaviSlovenski(View v) {
        setLocale("sl");
    }

    public void NastaviNemski(View v) {
        setLocale("de");
    }
}
