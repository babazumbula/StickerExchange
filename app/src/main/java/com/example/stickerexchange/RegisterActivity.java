package com.example.stickerexchange;

import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RegisterActivity extends Activity{

	EditText inputUserName;
	EditText inputEmail;
	EditText inputPhone;
	EditText inputPassword;
	Button registerBtn;
	ProgressBar pb;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
 
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        inputUserName = (EditText) findViewById(R.id.usrnameRegPg);
        inputEmail = (EditText) findViewById(R.id.emailRegPg);
        inputPhone = (EditText) findViewById(R.id.phone);
        inputPassword = (EditText) findViewById(R.id.passwordRegPg);
        registerBtn = (Button) findViewById(R.id.btnRegister);
        pb = (ProgressBar) findViewById(R.id.progressBarRegPg);
 
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
        
        registerBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				
				new RegisterUser().execute();
			}
		});
    }
	
	class RegisterUser extends AsyncTask<String, String, JSONObject>{
		
		String userName = inputUserName.getText().toString();
		String email = inputEmail.getText().toString();
		String phone = inputPhone.getText().toString();
		String password = inputPassword.getText().toString();

		@Override
		protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		registerBtn.setVisibility(View.INVISIBLE);
		pb.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected JSONObject doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.registerUser(userName, email, phone, password);
			
			return json;
		}
		
		@Override
		protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		
		pb.setVisibility(View.INVISIBLE);
		registerBtn.setVisibility(View.VISIBLE);
		

		}
		
	}
	
}
