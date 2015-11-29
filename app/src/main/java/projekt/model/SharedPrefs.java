package projekt.model;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projekt.data.Sport;

/**
 * Created by Mitja on 23.4.2015.
 */
public class SharedPrefs {

    public static final String PREFS_NAME = "projekt.siadrenalin.priljubljene";
    public static final String FAVORITES = "priljubljene";

    public SharedPrefs() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<String> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, String ob_sport) {
        List<String> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<String>();
        favorites.add(ob_sport);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, String ob_sport) {
        ArrayList<String> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(ob_sport);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<String> getFavorites(Context context)
    {
        SharedPreferences settings;
        List<String> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES))
        {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            String[] favoriteItems = gson.fromJson(jsonFavorites,
                    String[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<String>(favorites);
        } else
            return null;

        return (ArrayList<String>) favorites;
    }
}
