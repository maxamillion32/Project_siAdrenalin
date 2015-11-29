package projekt.siadrenalin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.siadrenalin.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import projekt.data.TipSporta;

public class PosodobiBazo extends Activity{
	private Button posodobi;
	private List<TipSporta> allTipSporta;
	MyApplication app;
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_posodobibazo);
		
		app = (MyApplication) getApplication();
        allTipSporta = app.getAll();
		
        dialog = new ProgressDialog(this);
        
		posodobi = (Button) findViewById(R.id.gumb_posodobi);
		posodobi.setOnClickListener(new OnClickListener() {
						
			@Override
			public void onClick(View arg0) {
				
				dialog.setMessage("Posodabljam...");
			    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			    dialog.setCancelable(false);
			    dialog.show();							    
				int max = 100;
				dialog.setMax(max);
			    
				new UpdateTask().execute(max);
			    }
		});
	}
	private class UpdateTask extends AsyncTask<Integer, Integer, Long>{
		@Override
		protected Long doInBackground(Integer... a){
			long totalSize = 0;
			
				StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().penaltyLog().penaltyDialog().build());
				
				@SuppressWarnings("unused")
				JSONObject json = null;
				
				@SuppressWarnings("unused")
				String str = "";
				HttpResponse response;
				HttpClient myClient = new DefaultHttpClient();
				HttpPost myConnection = new HttpPost("http://ferisrv5.uni-mb.si/~mt5452/mojServisPosodobi.php");
				
				int jump = 100/allTipSporta.size();
				
				publishProgress(0);
				for(int i=0; i<allTipSporta.size(); i++)
				{					
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(allTipSporta.size());
					nameValuePairs.add(new BasicNameValuePair("nazivTipSporta", allTipSporta.get(i).getNaziv().toString()));
					nameValuePairs.add(new BasicNameValuePair("opisTipSporta", allTipSporta.get(i).getOpis().toString()));
					
					for(int j=0; j<allTipSporta.get(i).getData().size(); j++)
					{
						nameValuePairs.add(new BasicNameValuePair("nazivSporta", allTipSporta.get(i).getData().get(j).getNaziv().toString()));
						nameValuePairs.add(new BasicNameValuePair("opisSporta", allTipSporta.get(i).getData().get(j).getOpis().toString()));
						nameValuePairs.add(new BasicNameValuePair("idTipSporta", Integer.toString(i)));
						
						try 
						{
							myConnection.setEntity(new UrlEncodedFormEntity(nameValuePairs));
							response = myClient.execute(myConnection);
							str = EntityUtils.toString(response.getEntity(), "UTF-8");					
						} 
						catch (ClientProtocolException e) 
						{
							e.printStackTrace();
						} 
						catch (IOException e) {
							e.printStackTrace();
						}
						
						if(j == 0)
						{
							nameValuePairs.clear();
						}
						
					}
					publishProgress(jump);
					jump = jump + jump;
				}
				
			
			return totalSize;
		}
		
		@Override
		protected void onProgressUpdate(Integer... progress){
			setProgressPercent(progress[0]);
		}
		
		@Override
		protected void onPostExecute(Long result){
			setData();
		}
	
	}
	private void setData(){
		dialog.dismiss();
		Toast.makeText(getApplicationContext(), "Uspešno posodobljeno!", Toast.LENGTH_SHORT).show();
	}
	
	public void setProgressPercent(Integer integer){
		dialog.setProgress(integer);
	}

}
