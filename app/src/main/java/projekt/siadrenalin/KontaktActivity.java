package projekt.siadrenalin;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.siadrenalin.R;

/**
 * Created by Mitja on 22.7.2015.
 */
public class KontaktActivity extends FragmentActivity {

    Button owner;
    Button user;

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.kontakt_title);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontakt_me);
        setTitle(R.string.kontakt_title);
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fl_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of Fragment1
            FragmentUporabnik firstFragment = new FragmentUporabnik();
            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fl_container, firstFragment)
                    .commit();

            user = (Button)findViewById(R.id.gumb_uporabnik);
            user.setBackgroundColor(Color.rgb(224, 224, 224));
            user.setText(R.string.gumb_user);

            owner = (Button)findViewById(R.id.gumb_lastnik);
            owner.setText(R.string.gumb_lastnik);

            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentUporabnik fragmentUporabnik = new FragmentUporabnik();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_container, fragmentUporabnik)
                            .commit();

                    user.setBackgroundColor(Color.rgb(224, 224, 224));
                    owner.setBackgroundColor(Color.WHITE);
                }
            });


            owner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentLastnik fragmentLastnik = new FragmentLastnik();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fl_container, fragmentLastnik)
                            .commit();

                    user.setBackgroundColor(Color.WHITE);
                    owner.setBackgroundColor(Color.rgb(224, 224, 224));
                }
            });
        }
    }

}
