package com.example.saloonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class client_filter extends AppCompatActivity {

    CheckBox cm,cw;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_filter);
        setTitle("Filter");

        cm=findViewById(R.id.checkBox10);
        cw=findViewById(R.id.checkBox11);
        b1=findViewById(R.id.button12);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String s1,s2;
                s1="b";s2="b";
                if(cm.isChecked()) { s1="a"; }
                if(cw.isChecked()) { s2="a"; }

                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                if(s1.equals("a") && s2.equals("a")) {
                    myEdit.remove("key");myEdit.putString("key", "3");
                }
                else if(s1.equals("a")) {
                    myEdit.remove("key");myEdit.putString("key", "2");
                }
                else if(s2.equals("a")) {
                    myEdit.remove("key");myEdit.putString("key", "1");
                }
                myEdit.commit();
                startActivity(new Intent(getApplicationContext(),client_homepage.class));
            }
        });
    }
}