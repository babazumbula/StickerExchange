package com.example.stickerexchange;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by vukan.boskovic on 15.10.2015..
 */
public class UserList extends ListActivity {

    Button editBtn;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);

        editBtn = (Button) findViewById(R.id.btnEdit);
        editBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Exchange.class);
                startActivity(i);

            }
        });
    }

}
