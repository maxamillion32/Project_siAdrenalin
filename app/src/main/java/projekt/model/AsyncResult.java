package projekt.model;

import org.json.JSONObject;

/**
 * Created by Mitja on 30.4.2015.
 * Original Code from github.com user: kiril-stanoev
 * Direct link: https://github.com/telerik/Android-samples/tree/master/Blogs/Json-Reader-2
 */
public interface AsyncResult
{
    void onResult(JSONObject object);
}
