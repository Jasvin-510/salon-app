package com.example.saloonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class saloon_menu_1 extends AppCompatActivity {

    Button history,logout,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_menu_1);
        setTitle("Menu");

        SharedPreferences sh = getSharedPreferences("sp1",MODE_PRIVATE);
        final String id9 = sh.getString("saloon_ref", "");


        history=findViewById(R.id.button7);
        logout=findViewById(R.id.button11);
        profile=findViewById(R.id.button15);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),saloon_update_info.class));
            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), saloon_history.class);
                intent3.putExtra("saloon_ref", id9);
                startActivity(intent3);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = getSharedPreferences("sp1", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sh.edit();
                myEdit.remove("saloon_ref");
                myEdit.remove("email");
                myEdit.remove("password");
                myEdit.remove("name");
                myEdit.remove("link");
                myEdit.commit();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),saloon_homepage.class));
    }
}