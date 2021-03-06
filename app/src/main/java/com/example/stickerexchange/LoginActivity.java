package com.example.stickerexchange;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoginActivity extends Activity {
	
	EditText inputUsername;
	EditText inputPassword;
	Button   btnLogin;
	ProgressBar pb;
    public static GlobalVariables globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        inputUsername = (EditText) findViewById(R.id.usrname);
        inputPassword = (EditText) findViewById(R.id.pass);
        pb = (ProgressBar) findViewById(R.id.progressBar);

        globalVariables = new GlobalVariables();
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        
        btnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new HttpBackgorundRequest().execute();

			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    class HttpBackgorundRequest extends AsyncTask<String, String, String>{

    	TextView serverResponse;
    	String username = inputUsername.getText().toString();
		String password = inputPassword.getText().toString();
		String jsonMessage;
		String jsonError;
		String jsonPhone;
		String jsonEmail;
		
		@Override
		protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		btnLogin.setVisibility(View.INVISIBLE);
		pb.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONObject json = null;

			UserFunctions userFunction = new UserFunctions();
			json = userFunction.loginUser(username, password);
			try {
				jsonError = json.getString("error");
                globalVariables.setUserID(json.getString("id"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(jsonError.equals("true")){
				try {
					jsonMessage = json.getString("message");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
    	
		@Override
		protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		serverResponse = (TextView) findViewById(R.id.serverResponse);
		pb.setVisibility(View.INVISIBLE);
		btnLogin.setVisibility(View.VISIBLE);
		
		if( jsonError.equals("true")){
			serverResponse.setText(jsonMessage);
		}else{
			Intent i = new Intent(LoginActivity.this, UserList.class);
			startActivity(i); 
		}

		
		}
    }
    
    
    
}
