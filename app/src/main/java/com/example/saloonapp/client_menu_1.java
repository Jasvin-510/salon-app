package com.example.saloonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class client_menu_1 extends AppCompatActivity {
    TextView t1;
    Button b1,b2,b3,profile;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),client_homepage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_menu_1);
        setTitle("Menu");

        SharedPreferences sh = getSharedPreferences("sp", MODE_PRIVATE);
        final String id10 = sh.getString("client_ref", "");


        b1 = findViewById(R.id.button6);
        b2 = findViewById(R.id.button8);
        b3 = findViewById(R.id.button10);
        profile=findViewById(R.id.button13);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),client_update_info.class));
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), client_history.class);
                // intent3.putExtra("client_ref", id10);
                startActivity(intent3);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(getApplicationContext(), client_rating.class);
                // intent4.putExtra("client_ref", id10);
                startActivity(intent4);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = getSharedPreferences("sp", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sh.edit();
                myEdit.remove("client_ref");
                myEdit.remove("client_email");
                myEdit.remove("city");
                myEdit.remove("password");
                myEdit.commit();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}