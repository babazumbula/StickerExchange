package com.example.stickerexchange;


import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class UserFunctions {
	
	private JSONParser jsonParser;
	
	private static String loginURL = "http://www.mjcelebrity.rs/task_manager/v1/loginUser";
	private static String registerURL = "http://www.mjcelebrity.rs/task_manager/v1/register";
	private static String checkExchangeAbilityURL = "http://www.mjcelebrity.rs/task_manager/v1/checkExchAbility";
	
	private static String login_tag = "login";
	private static String register_tag = "register";

    String globalVariables = GlobalVariables.getUserID();
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	
	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String username, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	

	public JSONObject registerUser(String username, String email, String phone, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("password", password));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		return json;
	}


	public void updateExchangeResources(ArrayList<String>[] sticker){



        String selectedID1,selectedID2,selectedID3,selectedID4,selectedID5,selectedID6,selectedID7,selectedID8 = null;
		final ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

		for (List selection : sticker) {
            selectedID1 = selection.get(0).toString();
            selectedID2 = selection.get(1).toString();
            selectedID3 = selection.get(2).toString();
            selectedID4 = selection.get(3).toString();
            selectedID5 = selection.get(4).toString();
			selectedID6 = selection.get(5).toString();
			selectedID7 = selection.get(6).toString();
			selectedID8 = selection.get(7).toString();

            params.add(new BasicNameValuePair("userID", globalVariables));
			params.add(new BasicNameValuePair("selectedID1", selectedID1));
			params.add(new BasicNameValuePair("selectedID2", selectedID2));
			params.add(new BasicNameValuePair("selectedID3", selectedID3));
			params.add(new BasicNameValuePair("selectedID4", selectedID4));
			params.add(new BasicNameValuePair("selectedID5", selectedID5));
			params.add(new BasicNameValuePair("selectedID6", selectedID6));
			params.add(new BasicNameValuePair("selectedID7", selectedID7));
			params.add(new BasicNameValuePair("selectedID8", selectedID8));
		}

		JSONObject json = jsonParser.getJSONFromUrl(checkExchangeAbilityURL, params);

	}


/*
	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	

	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	*/
}
