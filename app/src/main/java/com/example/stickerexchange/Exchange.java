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
    ArrayList<Integer> intListOfCheckedRadioButtons = new ArrayList<Integer>();
    ArrayList<String> stringListOfCheckedRadiobuttons = new ArrayList<String>();


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
        sticker1 = (RadioGroup) findViewById(R.id.st_1);
		sticker2 = (RadioGroup) findViewById(R.id.st_2);
		sticker3 = (RadioGroup) findViewById(R.id.st_3);
		sticker4 = (RadioGroup) findViewById(R.id.st_4);
		sticker5 = (RadioGroup) findViewById(R.id.st_5);
        sticker6 = (RadioGroup) findViewById(R.id.st_6);
        sticker7 = (RadioGroup) findViewById(R.id.st_7);
        sticker8 = (RadioGroup) findViewById(R.id.st_8);


        radioGroup = (RadioGroup) findViewById(R.id.st_7);

        getIntCheckedRadioButtonIDs(layout);
        getStringCheckedRadioButtonIDs(intListOfCheckedRadioButtons);
        prepareSelectedIdsForSendingToJSON(stringListOfCheckedRadiobuttons);

/*
        int count = layout.getChildCount();
        ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
        for (int i=0;i<count;i++) {
            View o = layout.getChildAt(i);
            if (o instanceof RadioButton) {
                listOfRadioButtons.add((RadioButton)o);
            }
        }
*/


        btnNext.setOnClickListener(new View.OnClickListener() {
            ArrayList<String> selectedIDs = new ArrayList<String>();
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                int selected1,selected2,selected3,selected4,selected5,selected6,selected7,selected8;
                String selectedID1,selectedID2,selectedID3,selectedID4,selectedID5,selectedID6,selectedID7,selectedID8 = null;

				 selected1 = sticker1.getCheckedRadioButtonId();
                 selected2 = sticker2.getCheckedRadioButtonId();
                 selected3 = sticker3.getCheckedRadioButtonId();
                 selected4 = sticker4.getCheckedRadioButtonId();
                 selected5 = sticker5.getCheckedRadioButtonId();
                 selected6 = sticker6.getCheckedRadioButtonId();
                 selected7 = sticker7.getCheckedRadioButtonId();
                 selected8 = sticker8.getCheckedRadioButtonId();

                selectedID1 = getResources().getResourceEntryName(selected1);
                selectedID2 = getResources().getResourceEntryName(selected2);
                selectedID3 = getResources().getResourceEntryName(selected3);
                selectedID4 = getResources().getResourceEntryName(selected4);
                selectedID5 = getResources().getResourceEntryName(selected5);
                selectedID6 = getResources().getResourceEntryName(selected6);
                selectedID7 = getResources().getResourceEntryName(selected7);
                selectedID8 = getResources().getResourceEntryName(selected8);


                selectedIDs.add(selectedID1);
                selectedIDs.add(selectedID2);
                selectedIDs.add(selectedID3);
                selectedIDs.add(selectedID4);
                selectedIDs.add(selectedID5);
                selectedIDs.add(selectedID6);
                selectedIDs.add(selectedID7);
                selectedIDs.add(selectedID8);


                new CheckExchangeAbility().execute(selectedIDs);

			}
		});
        
	}

}


class CheckExchangeAbility extends AsyncTask<java.util.ArrayList,String,String>{



    protected String doInBackground(java.util.ArrayList... params) {

        UserFunctions  userFunction = new UserFunctions();
        userFunction.updateExchangeResources(params);

        return null;
    }
}
