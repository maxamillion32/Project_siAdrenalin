package projekt.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siadrenalin.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import projekt.siadrenalin.AboutActivity;
import projekt.siadrenalin.Deli;
import projekt.siadrenalin.MapPane;
import projekt.siadrenalin.Priljubljene;
import projekt.siadrenalin.SeznamAktivnostiActivity;
import projekt.siadrenalin.vBlizini;

/**
 * Created by Mitja on 1.5.2015.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private String email;       //String Resource for header view email
    private String avtor;       //String Resource for quotes avtor
    Context context;
    Activity act;

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;

        TextView textView;
        ImageView imageView;
        TextView Name;
        TextView email;
        TextView author;
        Context cntx;
        Activity acti;

        public ViewHolder(View itemView,int ViewType, Context c, Activity a) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            cntx = c;
            acti = a;

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            }
            else{
                Name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                email = (TextView) itemView.findViewById(R.id.email);       // Creating Text View object from header.xml for email
                author = (TextView)itemView.findViewById(R.id.text_avtor);
                Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }
        @Override
        public void onClick(View v)
        {
            int izbran = getPosition();
            Intent intent = new Intent();
            switch (izbran)
            {
                case 1:
                    intent.setClass(cntx, SeznamAktivnostiActivity.class);
                    acti.startActivity(intent);
                    acti.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    break;
                case 2:
                    intent.setClass(cntx, MapPane.class);
                    acti.startActivity(intent);
                    acti.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    break;
                case 3:
                    intent.setClass(cntx, vBlizini.class);
                    acti.startActivity(intent);
                    acti.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    break;
                case 4:
                    intent.setClass(cntx, Priljubljene.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    acti.startActivity(intent);
                    acti.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    break;
                case 5:
                    //TODO Activity kuponi
                    break;
                case 6:
                    intent.setClass(cntx, AboutActivity.class);
                    acti.startActivity(intent);
                    acti.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    break;
                case 7:
                    //TODO Activity o avtorju
                    break;
                case 8:
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(acti);
                    alertDialog.setTitle("Deli na Facebook") // your dialog title
                            .setMessage("Na Facebook zid bo deljeno stanje: \nZ mobilno aplikacijo siAdrenalin odkrivam adrenalinska doživetja Slovenije!") // a message above the buttons
                            .setIcon(R.drawable.ic_action_facebook)// the icon besides the title you have to change it to the icon/image you have.
                            .setPositiveButton("Potrdi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent();
                                    intent.setClass(cntx, Deli.class);
                                    acti.startActivity(intent);
                                }

                            }).setNegativeButton("Prekliči", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                    });
                    alertDialog.show();
                    break;
                case 9:

                    AlertDialog.Builder alertDialogTwitter = new AlertDialog.Builder(acti);
                    alertDialogTwitter.setTitle("Deli na Twitter") // your dialog title
                            .setMessage("Na Twitter bo deljeno stanje: \nZ mobilno aplikacijo siAdrenalin odkrivam adrenalinska doživetja Slovenije!") // a message above the buttons
                            .setIcon(R.drawable.ic_action_twitter)// the icon besides the title you have to change it to the icon/image you have.
                            .setPositiveButton("Potrdi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deliTwitter();
                                }

                            }).setNegativeButton("Prekliči", new DialogInterface.OnClickListener() {
                                @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                        }
                    });
                    alertDialogTwitter.show();
                    break;
                default:
                    break;
            }
        }

        public void deliTwitter()
        {
            String tweetUrl="";
            try
            {
                tweetUrl = "https://twitter.com/intent/tweet?text=" +
                        URLEncoder.encode("Z mobilno aplikacijo siAdrenalin odkrivam adrenalinska doživetja Slovenije! ", "UTF-8")+
                        URLEncoder.encode("#siAdrenalin #IfeelsLOVEnia", "UTF-8");


            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

            Intent intent = new Intent();
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

            // Narrow down to official Twitter app, if available:
            List<ResolveInfo> matches = acti.getPackageManager().queryIntentActivities(intent, 0);
            for (ResolveInfo info : matches) {
                if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                    intent.setPackage(info.activityInfo.packageName);
                }
            }
            acti.startActivity(intent);
        }
    }

    public DrawerAdapter(String Titles[], int Icons[], String Name, String Email, String Author, Context passedContext, Activity passedActivity){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mNavTitles = Titles;    //have seen earlier
        mIcons = Icons;
        name = Name;
        email = Email;
        avtor = Author;
        context = passedContext;
        act = passedActivity;
        //in adapter
    }

    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item,parent,false); //Inflating the layout

            ViewHolder vhItem = new ViewHolder(v,viewType, context, act); //Creating ViewHolder and passing the object of type view

            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header,parent,false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v,viewType, context, act); //Creating ViewHolder and passing the object of type view

            return vhHeader; //returning the object created


        }
        return null;

    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
            holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
            holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
        }
        else{

            //holder.profile.setImageResource(profile);           // Similarly we set the resources for header view
            holder.Name.setText(name);
            holder.email.setText(email);
            holder.author.setText(avtor);
        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
    }


    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }



    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}
