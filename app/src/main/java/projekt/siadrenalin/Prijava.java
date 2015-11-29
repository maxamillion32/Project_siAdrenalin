package projekt.siadrenalin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Prijava extends Activity{

	private EditText uporabnisko;
	private EditText geslo;
	private Button gumbPotrdi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prijava);
		
		uporabnisko = (EditText) findViewById(R.id.text_uporabnisko);
		geslo = (EditText) findViewById(R.id.text_geslo);
		gumbPotrdi = (Button) findViewById(R.id.gumb_potrdiPrijavo);
		
		gumbPotrdi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(uporabnisko.getText().length() != 0 && geslo.getText().length() != 0)
				{
					StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().penaltyLog().penaltyDialog().build());
					JSONObject json = null;
					String str = "";
					HttpResponse response;
					HttpClient myClient = new DefaultHttpClient();
					HttpPost myConnection = new HttpPost("http://ferisrv5.uni-mb.si/~mt5452/mojServis.php");

					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
					
					nameValuePairs.add(new BasicNameValuePair("identifikator", uporabnisko.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("geslo", geslo.getText().toString()));
					
					try 
					{
						myConnection.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						response = myClient.execute(myConnection);
						str = EntityUtils.toString(response.getEntity(), "UTF-8");
						
						JSONArray jArray;
						try 
						{
							jArray = new JSONArray(str);
							json = jArray.getJSONObject(0);

							if ((json.getString("prijava").toString().equals("uspesna"))) 
							{
								Toast.makeText(getApplicationContext(), "Uspešna prijava!", Toast.LENGTH_LONG).show();
								
								Intent intent = new Intent();
								intent.setClass(getApplicationContext(), PosodobiBazo.class);
								startActivity(intent);
							} 
							else if((json.getString("prijava").toString().equals("neuspesna"))) 
							{
								Toast.makeText(getApplicationContext(), "Neuspešna prijava!", Toast.LENGTH_LONG).show();
							}	
						} catch (JSONException e) 
						{
							e.printStackTrace();
						}
					} 
					catch (ClientProtocolException e) 
					{
						e.printStackTrace();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Vpi�ite podatke!", Toast.LENGTH_LONG).show();
				}
		};
	});
}
	public void odpriRegistracijo(View v)
	{
		Intent intent = new Intent();
		intent.setClass(this, Registracija.class);
		startActivity(intent);
	}
}
