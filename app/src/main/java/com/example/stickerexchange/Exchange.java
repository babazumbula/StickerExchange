package com.example.stickerexchange;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Exchange extends Activity{
	
	private Button   btnNext;
	private RadioGroup sticker1;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange);
        
        btnNext = (Button) findViewById(R.id.btn_next);
        sticker1 = (RadioGroup) findViewById(R.id.st_1);
        
        btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selected = sticker1.getCheckedRadioButtonId();
				String selectedId = null;
				switch(selected){
				case R.id.st_1_needed : selectedId = "st_1_needed";
				break;
				
				case R.id.st_1_canGive : selectedId = "st_1_canGive";
				break;
				
				case R.id.st_1_none : selectedId = "st_1_none";
				break;
				
				}
				
				Log.e("selected: ",selectedId);
			}
		});
        
	}

}
