package projekt.siadrenalin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.siadrenalin.R;

public class Deli extends Activity {
	
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_deli);
		((TextView)getWindow().getDecorView().findViewById(android.R.id.title)).setGravity(Gravity.CENTER);
		super.onCreate(savedInstanceState);
		
		intent=new Intent(getBaseContext(),FacebookConnect.class);

		try{
	    	
			ConnectivityManager cMgr = (ConnectivityManager) this.getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE); 		
            NetworkInfo netInfo = cMgr.getActiveNetworkInfo();
            String status = netInfo.getState().toString();
            
            if (status.equals("CONNECTED")) {
            	enableStrictMode();
		    	startActivity(intent);
            } else {
			    Toast.makeText(getApplicationContext(), "No connection available",
				Toast.LENGTH_SHORT).show(); 
            }
    	
    	}
    	catch (Exception e) {
		    Toast.makeText(getApplicationContext(), "Connection refused",
		    Toast.LENGTH_SHORT).show(); 
    	}
	}
	public void enableStrictMode()
	{
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	 
	    StrictMode.setThreadPolicy(policy); 
	}

}
