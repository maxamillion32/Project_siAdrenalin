package projekt.siadrenalin;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siadrenalin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import projekt.adapter.NavDrawerBase;
import projekt.data.Sport;
import projekt.data.TipSporta;
import projekt.model.AsyncResult;
import projekt.model.DownloadJSON;
import projekt.model.HttpRequest;
import projekt.model.SharedPrefs;

/**
 * Created by Mitja on 31.3.2015.
 */
public class Lokacija extends NavDrawerBase{

    private Sport ob_sport;             //Sport objekt, kjer hranimo informacije o trenutno izbranem sportu
    private TipSporta ob_tipSporta;
    private MyApplication app;
    private boolean obstaja = false;
    private boolean bNoPrefs;
    private String[] priljubljene = {""};
    private String favorites = "";
    SharedPrefs sharedPref;
    private Context context;
    private boolean bInternet=true;
    //Galerija
    ImageView ivGalerija;
    //private CustomPagerAdapter mCustomPagerAdapter;
    //private ViewPager mViewPager;
    //Kontakt & priljubljene
    private ImageView ivKlic;
    private ImageView ivEmail;
    private ImageView ivPriljubljene;
    //Zemljevid
    private GoogleMap mMapView;
    //Naslov in opis
    private TextView tv_opis;
    private TextView tv_naslov;
    //Stevec
    private Stevec stevecAdrenalin;
    private ImageView ivStevecInfo;
    //Rating bar
    private RatingBar rb_druzina;
    private RatingBar rb_zabava;
    private RatingBar rb_urejenost;
    private RatingBar rb_cene;
    //Odpiralni cas
    private LinearLayout ll_casi;
    //Cenik
    private LinearLayout ll_cenik;
    /* ----- OCENJEVANJE ----- */
    private RelativeLayout layout_ocene;
    private boolean bAllowVote=false;
    private final String key = "&key=1m1eU1I09VCeVlE0qCqyFKnYGC9g_knkHV3DIx_akFlE";
    private final String url = "https://spreadsheets.google.com/tq?tqx=out:json&tq=";
    private String naslov = "https://spreadsheets.google.com/tq?key=1m1eU1I09VCeVlE0qCqyFKnYGC9g_knkHV3DIx_akFlE";
    private String gqlCheckID = "https://spreadsheets.google.com/tq?tqx=out:json&tq=select%20B%2CC%20where%20B%20%3D%20%2715%27%20AND%20C%20%3D%205&key=1m1eU1I09VCeVlE0qCqyFKnYGC9g_knkHV3DIx_akFlE";
    private String gqlGetOcene = "https://spreadsheets.google.com/tq?tqx=out:json&tq=select%20C%2C%20avg(D)%2C%20avg(E)%2C%20avg(F)%2C%20avg(G)%20group%20by%20C%20label%20C%20%27id_sporta%27%2C%20avg(D)%20%27ocena_druzine%27%2C%20avg(E)%20%27ocena_urejenost%27%2C%20avg(F)%20%27ocena_cene%27%2C%20avg(G)%20%27ocena_zabava%27&key=1m1eU1I09VCeVlE0qCqyFKnYGC9g_knkHV3DIx_akFlE";
    //Rating bar iz dialoga
    private RatingBar rb_DialogDruzine;
    private RatingBar rb_DialogUrejenost;
    private RatingBar rb_DialogCene;
    private RatingBar rb_DialogZabava;

    //Slike
    public static String[] mResources = new String[5];

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(ob_sport.getNaziv());
        updateRating();
        if(bInternet)
            update();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokacija);
        super.onCreateDrawer((Activity)this);
        this.context = getApplicationContext();

        //Iz razreda MyApplication dobimo objekt, ki je bil izbran iz seznama
        app = (MyApplication) getApplication();
        ob_sport = app.sport; //izbran objekt
        ob_tipSporta = app.tipSporta;

        setTitle(ob_sport.getNaziv());
        ActionBar actionBar = getActionBar();

        //Nastavimo ikono actionBara na logotip sporta
        /*Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeFile(ob_sport.getLogotip());
        BitmapDrawable bd = new BitmapDrawable(res, bitmap);
        actionBar.setIcon(bd);*/


        //mCustomPagerAdapter = new CustomPagerAdapter(this);

        //Zaradi čim hitrejšega delovanja najprej nastavimo ocene destinacije
        //Nastavimo ocene (RatingBar)
        rb_zabava = (RatingBar)findViewById(R.id.rating_zabava);
        LayerDrawable stars = (LayerDrawable) rb_zabava.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starFullySelected
        stars.getDrawable(1).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starPartiallySelected
        stars.getDrawable(0).setColorFilter(Color.rgb(255,255,255), PorterDuff.Mode.SRC_ATOP); //starNotSelected

        rb_druzina = (RatingBar)findViewById(R.id.rating_druzina);
        LayerDrawable stars2 = (LayerDrawable) rb_druzina.getProgressDrawable();
        stars2.getDrawable(2).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starFullySelected
        stars2.getDrawable(1).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starPartiallySelected
        stars2.getDrawable(0).setColorFilter(Color.rgb(255,255,255), PorterDuff.Mode.SRC_ATOP); //starNotSelected

        rb_urejenost = (RatingBar)findViewById(R.id.rating_urejenost);
        LayerDrawable stars3 = (LayerDrawable) rb_urejenost.getProgressDrawable();
        stars3.getDrawable(2).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starFullySelected
        stars3.getDrawable(1).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starPartiallySelected
        stars3.getDrawable(0).setColorFilter(Color.rgb(255,255,255), PorterDuff.Mode.SRC_ATOP); //starNotSelected

        rb_cene = (RatingBar)findViewById(R.id.rating_cene);
        LayerDrawable stars4 = (LayerDrawable) rb_cene.getProgressDrawable();
        stars4.getDrawable(2).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starFullySelected
        stars4.getDrawable(1).setColorFilter(Color.rgb(0, 0, 0), PorterDuff.Mode.SRC_ATOP); //starPartiallySelected
        stars4.getDrawable(0).setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_ATOP); //starNotSelected

        //Posodobi ocene
        layout_ocene = (RelativeLayout)findViewById(R.id.rl_Ocene);

        updateRating();

        if(app.isNetworkConnected())
        {
            new CheckVote().execute();
            update();
            bInternet = true;
        }
        else
            bInternet = false;


        layout_ocene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view)
            {
                if(bAllowVote)
                {
                    //Open vote dialog
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View customView = inflater.inflate(R.layout.dialog_oceni, null);
                    AlertDialog.Builder alert = new AlertDialog.Builder(Lokacija.this);

                    alert.setTitle(R.string.oceni);
                    alert.setMessage(R.string.oceni_des);
                    alert.setView(customView);

                    rb_DialogDruzine = (RatingBar) customView.findViewById(R.id.rating_druzina);
                    LayerDrawable stars = (LayerDrawable) rb_DialogDruzine.getProgressDrawable();
                    stars.getDrawable(2).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starFullySelected
                    stars.getDrawable(1).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starPartiallySelected
                    stars.getDrawable(0).setColorFilter(Color.rgb(255,255,255), PorterDuff.Mode.SRC_ATOP); //starNotSelected

                    rb_DialogUrejenost = (RatingBar) customView.findViewById(R.id.rating_urejenost);
                    LayerDrawable stars2 = (LayerDrawable) rb_DialogUrejenost.getProgressDrawable();
                    stars2.getDrawable(2).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starFullySelected
                    stars2.getDrawable(1).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starPartiallySelected
                    stars2.getDrawable(0).setColorFilter(Color.rgb(255,255,255), PorterDuff.Mode.SRC_ATOP); //starNotSelected

                    rb_DialogCene = (RatingBar) customView.findViewById(R.id.rating_cene);
                    LayerDrawable stars3 = (LayerDrawable) rb_DialogCene.getProgressDrawable();
                    stars3.getDrawable(2).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starFullySelected
                    stars3.getDrawable(1).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starPartiallySelected
                    stars3.getDrawable(0).setColorFilter(Color.rgb(255,255,255), PorterDuff.Mode.SRC_ATOP); //starNotSelected

                    rb_DialogZabava = (RatingBar) customView.findViewById(R.id.rating_zabava);
                    LayerDrawable stars4 = (LayerDrawable) rb_DialogZabava.getProgressDrawable();
                    stars4.getDrawable(2).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starFullySelected
                    stars4.getDrawable(1).setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC_ATOP); //starPartiallySelected
                    stars4.getDrawable(0).setColorFilter(Color.rgb(255,255,255), PorterDuff.Mode.SRC_ATOP); //starNotSelected

                    alert.setPositiveButton(R.string.potrdi, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            new Vote().execute();
                            bAllowVote = false;
                        }
                    });

                    alert.setNegativeButton(R.string.preklic, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });

                    alert.create().show();
                }
                else
                {
                    if(bInternet)
                        Toast.makeText(context, R.string.oddGlas, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, R.string.off_internet, Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Nastavimo galerijo
        //Poiščemo ustrezni ImageView iz vmesnika
        ivGalerija = (ImageView)findViewById(R.id.iv_galerija);
        //S pomočjo funkcije setImageURI nastavimo pot datoteke na pot, ki jo hrani
        //objekt športa (destinacije)
        ivGalerija.setImageURI(Uri.parse(ob_sport.getSlika()));
        ivGalerija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog builder = new Dialog(Lokacija.this);
                builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                builder.getWindow()
                        .setBackgroundDrawable(
                                new ColorDrawable(
                                        android.graphics.Color.TRANSPARENT));
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(
                            DialogInterface dialogInterface) {
                        // nothing;
                    }
                });

                ImageView imageView = new ImageView(Lokacija.this);
                imageView.setImageURI(Uri.parse(ob_sport.getSlika()));
                builder.addContentView(
                        imageView,
                        new RelativeLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));
                builder.show();
            }
        });
        /*mViewPager = (ViewPager) findViewById(R.id.pager);

        File imgFile = new File(ob_sport.getSlika());
        mResources[0] = imgFile.getAbsolutePath();

        mViewPager.setAdapter(mCustomPagerAdapter);*/

        //Nastavimo kontakt & priljubljene
        ImageView ivFacebook = (ImageView)findViewById(R.id.gumb_facebook);
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(app.isNetworkConnected())
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Lokacija.this);
                    alertDialog.setTitle(R.string.facebook_share) // your dialog title
                            .setMessage(getApplicationContext().getResources().getString(R.string.twitterSportMsg) + " " + ob_sport.getNaziv() + " #siAdrenalin") // a message above the buttons
                            .setIcon(R.drawable.ic_action_facebook)// the icon besides the title you have to change it to the icon/image you have.
                            .setPositiveButton(R.string.potrdi, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    app.sFbShare = R.string.twitterSportMsg + " " + ob_sport.getNaziv() + "#siAdrenalin";
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
                    Toast.makeText(context, R.string.off_internet, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivKlic = (ImageView) findViewById(R.id.gumb_poklici);
        ivKlic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.telKopiran + ob_sport.getStevilka(), Toast.LENGTH_LONG).show();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("stevilka", ob_sport.getStevilka());
                clipboard.setPrimaryClip(clip);
            }
        });

        //Poiščemo ustrezni ImageView iz vmesnika
        ImageView ivMail = (ImageView)findViewById(R.id.gumb_email);
        //Nastavimo dogodek ob kliku
        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ker za pošiljanje elektronske pošte potrebujemo podatkovni prenos najpre preverimo dostop do interneta
                if(app.isNetworkConnected())
                {
                    //Ob dostopu do interneta se zažene Intent s nastavljenimi podatki za elektronsko pošto
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:" + ob_sport.getEmail() + "?subject=" + ob_sport.getNaziv() + "- siAdrenalin");
                    intent.setData(data);
                    startActivity(intent);
                }
                //V primeru, da ni internetne povezave, to uporabniku sporočimo
                else
                {
                    Toast.makeText(context, R.string.off_internet, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView ivTwitter = (ImageView)findViewById(R.id.gumb_twitter);
        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogTwitter = new AlertDialog.Builder(Lokacija.this);
                alertDialogTwitter.setTitle(R.string.twitter_share) // your dialog title
                        .setMessage(getApplicationContext().getResources().getString(R.string.twitterSportMsg) + " " + ob_sport.getNaziv() + " #siAdrenalin #IfeelsLOVEnia") // a message above the buttons
                        .setIcon(R.drawable.ic_action_twitter)// the icon besides the title you have to change it to the icon/image you have.
                        .setPositiveButton(R.string.potrdi, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deliTwitter(R.string.twitterSportMsg + " " + ob_sport.getNaziv() + " #siAdrenalin #IfeelsLOVEnia");
                            }

                        }).setNegativeButton(R.string.preklic, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogTwitter.show();
            }
        });

        //Odpremo Shared Prefs
        ivPriljubljene = (ImageView) findViewById(R.id.cb_favorite);
        sharedPref = new SharedPrefs();

        if(checkFavoriteItem(ob_sport))
        {
            ivPriljubljene.setImageResource(R.drawable.ic_favorite_logo_active);
        }
        else
        {
            ivPriljubljene.setImageResource(R.drawable.ic_favorite_logo_metal);
        }


        ivPriljubljene = (ImageView) findViewById(R.id.cb_favorite);
        ivPriljubljene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Dodaj k priljubljenim + spremeni ikono
                //Ob kliku dodamo
                if(checkFavoriteItem(ob_sport))
                {
                    ivPriljubljene.setImageResource(R.drawable.ic_favorite_logo_metal);

                   //Odstranimo iz priljubljenih
                    sharedPref.removeFavorite(context, ob_sport.getId());
                    Toast.makeText(context, R.string.odsPriljubljenim, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ivPriljubljene.setImageResource(R.drawable.ic_favorite_logo_active);

                    //Dodamo k priljubljenim
                    sharedPref.addFavorite(context, ob_sport.getId());
                    Toast.makeText(context, R.string.dodPriljubljenim, Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(app.getPlayServices())
        {
            //Poiščemo ustrezni element na uporabniškem vmesniku
            mMapView = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_view)).getMap();

            //Izklopimo vso interakcijo z zemljevidom, saj želimo zgolj prikaz
            mMapView.getUiSettings().setAllGesturesEnabled(false);
            //Iz objekta destinacije oz. športa dobimo koordinate
            LatLng pozicija = ob_sport.getKoordinate();
            //Na zemljevid postavimo označbo
            mMapView.addMarker(new MarkerOptions().position(pozicija));
            //Nastavimo pozicijo pogleda točno na točko destinacije in nastavimo povečavo
            mMapView.moveCamera(CameraUpdateFactory.newLatLngZoom(ob_sport.getKoordinate(), 10.0f));
        }
        else
        {
            android.support.v7.widget.CardView cardZemljevid = (android.support.v7.widget.CardView)findViewById(R.id.card_zemljevid);
            cardZemljevid.setVisibility(View.GONE);
        }

        //Nastavimo naslov pod zemljevidom
        tv_naslov = (TextView)findViewById(R.id.textView_naslov);
        tv_naslov.setText(ob_sport.getNaslov());

        //Nastavimo opis na izbran objekt
        tv_opis = (TextView)findViewById(R.id.text_opis_sporta);
        tv_opis.setText(ob_sport.getOpis());

        //Poiščemo ustrezni LinearLayout
        ll_casi = (LinearLayout)findViewById(R.id.tl_odpiralni_cas);
        //Iz niza v obliki dan,čas;dan,čas dobimo polje z elementi dan,čas
        String[] stVrednost = ob_sport.getOdpiralni().split(";"); //Iz vrednosti v posameznem objektu Sport dobimo polje stringov v obliki "dan,cas"

        //Pomikamo se od začetka polja do konca
        for(int i=0; i<stVrednost.length; i++)
        {
            //Iz niza v obliki dan,čas dobimo polje z vrednostjo dan na prvem indeksu in vednostjo čas na drugem indeksu
            String[] stDanInCas = stVrednost[i].split(",");

            //Nov, korenski relative layout z ustreznimi nastavitvami, ki služi kot vrstica za oba TextView-a
            RelativeLayout rl = new RelativeLayout(this);
            rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            //Ustvarimo now TextView, za prikaz dneva
            TextView tvDan = new TextView(this);
            tvDan.setText(stDanInCas[0]);
            tvDan.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            //Prvemu TextView-u nastavimo pozicijo na skrajni levi strani
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tvDan.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

            tvDan.setLayoutParams(params);

            //Prvi TextViev dodamo v korenski RelativeLayout
            rl.addView(tvDan);

            RelativeLayout rl2 = new RelativeLayout(this);
            rl2.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            //Ustvarimo now TextView, za prikaz dneva
            TextView tvCas = new TextView(this);//Drugi text view (Cas)
            tvCas.setText(stDanInCas[1]);
            tvCas.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams)tvCas.getLayoutParams();
            params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            tvCas.setLayoutParams(params2);
            rl2.addView(tvCas);

            if(i != 0)
            {
                ImageView divider = new ImageView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
                lp.setMargins(10, 10, 10, 10);
                divider.setLayoutParams(lp);
                divider.setBackgroundColor(Color.LTGRAY);
                rl.addView(divider);
            }

            ll_casi.addView(rl);
            ll_casi.addView(rl2);
        }

        //Nastavimo cenik
        ll_cenik = (LinearLayout)findViewById(R.id.tl_cenik);
        String[] stCene = ob_sport.getCenik().split(";");
        for(int i=0; i<stCene.length; i++)
        {
            String[] stOpisCena = stCene[i].split(","); //Iz vrednosti polja stVrednost dobimo polje stringov v obliki "dan" na indeksu 0 in "cas" na indeksu 1

            RelativeLayout rl = new RelativeLayout(this); //Nov relative layout, ki služi kot vrstica za oba textview-a
            rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            TextView tvOpis = new TextView(this); //Prvi text view (Opis)
            tvOpis.setText(stOpisCena[0]);
            tvOpis.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tvOpis.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

            tvOpis.setLayoutParams(params);
            rl.addView(tvOpis);

            RelativeLayout rl2 = new RelativeLayout(this); //Nov relative layout, ki služi kot vrstica za textview cene
            rl2.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            TextView tvCena = new TextView(this);//Drugi text view (Cena)
            tvCena.setText(stOpisCena[1]);
            tvCena.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams)tvCena.getLayoutParams();
            params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            tvCena.setLayoutParams(params2);

            rl2.addView(tvCena);

            if(i != 0)
            {
                ImageView divider = new ImageView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
                lp.setMargins(10, 10, 10, 10);
                divider.setLayoutParams(lp);
                divider.setBackgroundColor(Color.LTGRAY);
                rl.addView(divider);
            }

            ll_cenik.addView(rl);
            ll_cenik.addView(rl2);
        }

        //Natavimo adrenalin-o meter
        stevecAdrenalin = (Stevec)findViewById(R.id.stevecAdrenalin);
        stevecAdrenalin.setLabelConverter(new Stevec.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((double) Math.round(progress));
            }
        });
        stevecAdrenalin.setSpeed(ob_sport.getAdrenalin());

        stevecAdrenalin.setMaxSpeed(100);
        stevecAdrenalin.setMajorTickStep(20);
        stevecAdrenalin.setMinorTicks(2);

        stevecAdrenalin.addColoredRange(0, 50, Color.GREEN);
        stevecAdrenalin.addColoredRange(50, 80, Color.YELLOW);
        stevecAdrenalin.addColoredRange(80, 100, Color.RED);

        ivStevecInfo = (ImageView)findViewById(R.id.iv_stevec_info);
        ivStevecInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.meterMsg, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void deliTwitter(String naziv)
    {
        String tweetUrl="";
        try
        {
            tweetUrl = "https://twitter.com/intent/tweet?text=" +
                    URLEncoder.encode(naziv, "UTF-8");


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
    private void update() {

        new DownloadJSON(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
                updateRating();
            }
        }).execute(gqlGetOcene);
    }

    private void checkAllowVote()
    {
        String gql= null;
        try {
            gql = url +
                    URLEncoder.encode("select B,C where B ='" +
                            Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID) +
                            "' AND C=" + "'#" + ob_sport.getId() + "'", "UTF-8") +
                    key;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        new DownloadJSON(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                checkAllowVoteJSON(object);
            }
        }).execute(gql);
    }

    private void openJSON(JSONObject object) {

        try {
            String jsonString = object.toString();
            FileOutputStream fos = openFileOutput("ocene.json", Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkAllowVoteJSON(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");

            if(rows.length() == 0)
            {
                bAllowVote = true;
            }
            else
            {
                bAllowVote = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String pokaziJSON()
    {
        String json = null;
        try {

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("ocene.json")));

            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }

            json = stringBuffer.toString();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public void postVote(String ocena_druzine, String ocena_urejenost, String ocena_cene, String ocena_zabava) {

        String fullUrl = "https://docs.google.com/forms/d/1gL7wCV6cB3aDhgbn5uDpwIk-OC3jUM8O60Bx5A3dtBQ/formResponse";
        HttpRequest mReq = new HttpRequest();
        String IDnaprave = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String IDsporta = "#" + ob_sport.getId();

        String data = null;
        try {
            data = "entry.574466512=" + URLEncoder.encode(IDnaprave, "UTF-8") + "&" +
                    "entry.801462073=" + URLEncoder.encode(IDsporta, "UTF-8") + "&" +
                    "entry.1563419787=" + URLEncoder.encode(ocena_druzine,"UTF-8") + "&" +
                    "entry.1620829358=" + URLEncoder.encode(ocena_urejenost, "UTF-8") + "&" +
                    "entry.397574996=" + URLEncoder.encode(ocena_cene, "UTF-8") + "&" +
                    "entry.619316501=" + URLEncoder.encode(ocena_zabava, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        try
        {
            String response = mReq.sendPost(fullUrl, data);
            updateRating();
        }
        catch (Exception ex)
        {
            Log.i("Http SEND ima težavo:", ex.toString());
        }

    }
    private void processJson(JSONObject object) {

        try {
            String jsonString = object.toString();
            FileOutputStream fos = openFileOutput("ocene.json", Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();
            updateRating();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateRating()
    {
        try {
            //Odpremo JSON datoteko in jo shranimo kot JSON objekt
            JSONObject obj = new JSONObject(pokaziJSON());

            //Pripravimo nize za vsak tip ocene
            String ocenaDruzine = "";
            String ocenaUrejenost = "";
            String ocenaCene = "";
            String ocenaZabava = "";

            //Iz JSON objekta pridobimo vse vrstice
            JSONArray rows = obj.getJSONArray("rows");
            //Pomikamo se skozi vrstice
            for (int r = 0; r < rows.length(); ++r)
            {
                //Pri pomikanju izbiramo vrstico po vrstico
                JSONObject row = rows.getJSONObject(r);
                //Iz vsake vrstice pridobimo še stolpce
                JSONArray columns = row.getJSONArray("c");

                //Najprej pridobimo podatek o ID-ju (identifikacijski številki destinacije)
                String sportID = columns.getJSONObject(0).getString("v");

                //ID primerjamo s ID-jem objekta trenutno izbrane destinacije
                if(sportID.equals("#" + ob_sport.getId())) {

                    //Ob ujemanju posodobimo uporabniški vmesnik
                    ocenaDruzine = columns.getJSONObject(1).getString("v");
                    rb_druzina.setRating(Float.parseFloat(ocenaDruzine));

                    ocenaUrejenost = columns.getJSONObject(2).getString("v");
                    rb_urejenost.setRating(Float.parseFloat(ocenaUrejenost));

                    ocenaCene = columns.getJSONObject(3).getString("v");
                    rb_cene.setRating(Float.parseFloat(ocenaCene));


                    ocenaZabava = columns.getJSONObject(4).getString("v");
                    rb_zabava.setRating(Float.parseFloat(ocenaZabava));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        // finish() is called in super: we only override this method to be able to override the transition
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
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

    class Vote extends AsyncTask<String, String, String> {
        ProgressDialog progDailog = new ProgressDialog(Lokacija.this);
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progDailog.setMessage(getApplicationContext().getResources().getString(R.string.nalagam_));
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }
        @Override
        protected String doInBackground(String... aurl) {
            postVote(Float.toString(rb_DialogDruzine.getRating()), Float.toString(rb_DialogUrejenost.getRating()), Float.toString(rb_DialogCene.getRating()), Float.toString(rb_DialogZabava.getRating()));
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            update();
            updateRating();
            progDailog.dismiss();
        }
    }

    class CheckVote extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... aurl) {
            checkAllowVote();
            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);

        }
    }

    class updateRating extends AsyncTask<String, String, String> {
        ProgressDialog progDailog = new ProgressDialog(Lokacija.this);
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

            new DownloadJSON(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                    processJson(object);
                }
            }).execute(gqlGetOcene);

            try {
                JSONObject obj = new JSONObject(pokaziJSON());
                String ocenaDruzine = "";
                String ocenaUrejenost = "";
                String ocenaCene = "";
                String ocenaZabava = "";

                JSONObject searchObject = new JSONObject();
                JSONArray rows = obj.getJSONArray("rows");
                for (int r = 0; r < rows.length(); ++r)
                {
                    JSONObject row = rows.getJSONObject(r);
                    JSONArray columns = row.getJSONArray("c");
                    String sportID = columns.getJSONObject(0).getString("v");

                    if(sportID.equals("#" + ob_sport.getId())) {
                        ocenaDruzine = columns.getJSONObject(1).getString("v");
                        rb_druzina.setRating(Float.parseFloat(ocenaDruzine));

                        ocenaUrejenost = columns.getJSONObject(2).getString("v");
                        rb_urejenost.setRating(Float.parseFloat(ocenaUrejenost));

                        ocenaCene = columns.getJSONObject(3).getString("v");
                        rb_cene.setRating(Float.parseFloat(ocenaCene));


                        ocenaZabava = columns.getJSONObject(4).getString("v");
                        rb_zabava.setRating(Float.parseFloat(ocenaZabava));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String unused) {
            super.onPostExecute(unused);
            progDailog.dismiss();

        }
    }

}
