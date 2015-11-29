package projekt.siadrenalin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import com.easy.facebook.android.apicall.GraphApi;
import com.easy.facebook.android.error.EasyFacebookError;
import com.easy.facebook.android.facebook.FBLoginManager;
import com.easy.facebook.android.facebook.Facebook;
import com.easy.facebook.android.facebook.LoginListener;
import com.siadrenalin.R;

public class FacebookConnect extends Activity implements LoginListener {

	private FBLoginManager fbManager;
	private MyApplication app;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (MyApplication)getApplication();
		shareFacebook();
	}

	public void shareFacebook() {

        // mo&#xfffd;ne pravice
		/*
		 * String permissions[] = { "read_stream", "user_relationship_details",
		 * "user_religion_politics", "user_work_history", "user_relationships",
		 * "user_interests", "user_likes", "user_location", "user_hometown",
		 * "user_education_history", "user_activities", "offline_access" };
		 */
		String permissions[] = { "publish_actions" };

		fbManager = new FBLoginManager(this, R.layout.black, "617355071727493",
				permissions);

		if (fbManager.existsSavedFacebook()) {
			fbManager.loadFacebook();
		} else {

			fbManager.login();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		fbManager.loginSuccess(data);
	}

	@Override
	public void loginFail() {
		fbManager.displayToast("Login failed!");

	}

	@Override
	public void logoutSuccess() {
		fbManager.displayToast("Logout success!");
	}

	@Override
	public void loginSuccess(Facebook facebook) {

		GraphApi graphApi = new GraphApi(facebook);

		String response = "";

		try {
			enableStrictMode();
			response = graphApi
					.setStatus(
							app.sFbShare,
							"");
		} catch (EasyFacebookError e) {
			e.toString();
		}

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Deli na facebook");
		alertDialog.setMessage("Uspešno posodobljeno stanje na: " + response);
		alertDialog.setIcon(R.drawable.facebook_logo);
		alertDialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface arg0) {
				Intent main = new Intent();
				main.setClass(getBaseContext(), MainActivity.class);
				startActivity(main);

			}
		});

		alertDialog.show();
	}

	public void enableStrictMode() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
	}
}
