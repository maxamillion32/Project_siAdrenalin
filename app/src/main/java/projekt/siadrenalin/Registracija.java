package projekt.siadrenalin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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

public class Registracija extends Activity {
	
	private EditText uporabnisko;
	private EditText priimek;
	private EditText geslo;
	private EditText geslo2;
	private EditText email;
	private Spinner starost;
	private RadioButton moski;
	private RadioButton zenska;
	private Button potrdi;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registracija);
				
		uporabnisko = (EditText) findViewById(R.id.editText_uporabnisko);
		priimek = (EditText) findViewById(R.id.editText_priimek);
		geslo = (EditText) findViewById(R.id.editText_pass);
		geslo2 = (EditText) findViewById(R.id.editText_pass2);
		email = (EditText) findViewById(R.id.editText_email);
		starost = (Spinner) findViewById(R.id.spinner_starost);
		moski = (RadioButton) findViewById(R.id.radioButton_spol);
		zenska = (RadioButton) findViewById(R.id.radioButton_spol2);
		potrdi = (Button) findViewById(R.id.gumb_registracija);
		
		potrdi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(geslo.getText().toString().equals(geslo2.getText().toString()))
				{
					StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().penaltyLog().penaltyDialog().build());
					JSONObject json = null;
					String str = "";
					HttpResponse response;
					HttpClient myClient = new DefaultHttpClient();
					HttpPost myConnection = new HttpPost("http://ferisrv5.uni-mb.si/~mt5452/mojServisReg.php");

					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
					
					nameValuePairs.add(new BasicNameValuePair("ime", uporabnisko.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("priimek", priimek.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("uporabnisko", uporabnisko.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("geslo", geslo.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("starost", starost.getSelectedItem().toString()));
					nameValuePairs.add(new BasicNameValuePair("email", email.getText().toString()));
					if(moski.isChecked())
					{
						nameValuePairs.add(new BasicNameValuePair("spol", "M"));
					}
					
					if(zenska.isChecked())
					{
						nameValuePairs.add(new BasicNameValuePair("spol", "Ž"));
					}
					
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

							if ((json.getString("dodaja").toString().equals("uspesna"))) 
							{
								Toast.makeText(getApplicationContext(), "Registracija uspešna!", Toast.LENGTH_LONG).show();
								
								Intent intent = new Intent();
								intent.setClass(getApplicationContext(), Prijava.class);
								startActivity(intent);
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
					Toast.makeText(getApplicationContext(), "Gesli se ne ujemata!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

}
