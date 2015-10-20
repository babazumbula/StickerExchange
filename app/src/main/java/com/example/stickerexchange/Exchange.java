package com.example.stickerexchange;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Exchange extends Activity{
	
	private Button   btnNext;
	private RadioGroup sticker1,sticker2,sticker3,sticker4,sticker5,sticker6,sticker7,sticker8;
    private RadioGroup radioGroup;
    private LinearLayout layout;
    static ArrayList<Integer> intListOfCheckedRadioButtons = new ArrayList<Integer>();
    static ArrayList<String> stringListOfCheckedRadiobuttons = new ArrayList<String>();


    private void getIntCheckedRadioButtonIDs(ViewGroup parent){

        int checked = 0;
        for (int i = 0 ; i < parent.getChildCount(); i++){
            final View child = parent.getChildAt(i);
            if(child instanceof RadioGroup){
                ((RadioGroup) child).getCheckedRadioButtonId();
                intListOfCheckedRadioButtons.add(((RadioGroup) child).getCheckedRadioButtonId());
            }
        }
    }

    private void getStringCheckedRadioButtonIDs(ArrayList<Integer> checkedIds){

        for(int i=0; i< checkedIds.size(); i++){
            stringListOfCheckedRadiobuttons.add(getResources().getResourceEntryName(checkedIds.get(i)));
        }
    }

    private void prepareSelectedIdsForSendingToJSON(ArrayList<String> checkedIds){

        for(int i=0; i< checkedIds.size(); i++){
            switch(checkedIds.get(i).substring(5,9)){
                case "none" : stringListOfCheckedRadiobuttons.set(i,"0");
                    break;
                case "give" : stringListOfCheckedRadiobuttons.set(i,"1");
                    break;
                case "need" : stringListOfCheckedRadiobuttons.set(i,"2");
            }
        }
    }

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange);

        layout = (LinearLayout) findViewById(R.id.exchangeView);
        btnNext = (Button) findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            ArrayList<String> selectedIDs = new ArrayList<String>();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                getIntCheckedRadioButtonIDs(layout);
                getStringCheckedRadioButtonIDs(intListOfCheckedRadioButtons);
                prepareSelectedIdsForSendingToJSON(stringListOfCheckedRadiobuttons);
                new CheckExchangeAbility().execute(stringListOfCheckedRadiobuttons);

			}
		});
        stringListOfCheckedRadiobuttons.clear();
        intListOfCheckedRadioButtons.clear();
	}

}


class CheckExchangeAbility extends AsyncTask<java.util.ArrayList,String,String>{



    protected String doInBackground(java.util.ArrayList... params) {

        UserFunctions  userFunction = new UserFunctions();
        userFunction.updateExchangeResources(params);
        Exchange.stringListOfCheckedRadiobuttons.clear();
        Exchange.intListOfCheckedRadioButtons.clear();
        return null;
    }
}
