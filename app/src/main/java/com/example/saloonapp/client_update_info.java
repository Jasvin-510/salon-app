package com.example.saloonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class client_update_info extends AppCompatActivity {

    EditText id_regis_client,name_regis_client,password_regis_client,mo_no_regis_client,add_regis_client,client_city;
    Button change;
    FirebaseAuth fAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf =db.collection("customer");
    private String email,password,doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_update_info);

        setTitle("Change Peofile");

        name_regis_client =findViewById(R.id.name_regis_client);
        mo_no_regis_client =findViewById(R.id.mo_no_regis_client);
        add_regis_client =findViewById(R.id.add_regis_client);
        client_city=findViewById(R.id.client_city);
        SharedPreferences sh = getSharedPreferences("sp", MODE_PRIVATE);
        email = sh.getString("client_email","ja");
        password=sh.getString("password","0");
        doc=sh.getString("client_ref","a");

        change=findViewById(R.id.button3);

        fAuth=FirebaseAuth.getInstance();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(client_city.getText().toString().trim()))
                {
                    client_city.setError("city is required");
                }
                else if(TextUtils.isEmpty(mo_no_regis_client.getText().toString().trim()))
                {
                    mo_no_regis_client.setError("mobile number is required");
                }
                else if(TextUtils.isEmpty(name_regis_client.getText().toString().trim()))
                {
                    name_regis_client.setError("name is required");
                }
                else if(TextUtils.isEmpty(add_regis_client.getText().toString().trim()))
                {
                    add_regis_client.setError("address is required");
                }
                else
                {
                    Map<String, Object> client = new HashMap<>();
                    client.put("id", email);
                    client.put("password", password);
                    client.put("name", name_regis_client.getText().toString().trim());
                    client.put("number", mo_no_regis_client.getText().toString().trim());
                    client.put("address", add_regis_client.getText().toString().trim());
                    client.put("city",client_city.getText().toString().trim().toLowerCase());
                    SharedPreferences sh = getSharedPreferences("sp", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sh.edit();
                    myEdit.remove("client_ref");
                    myEdit.remove("client_email");
                    myEdit.remove("city");
                    myEdit.remove("password");
                    myEdit.commit();

                    myEdit.putString("client_ref",doc);
                    myEdit.putString("client_email",email);
                    myEdit.putString("password",password);
                    myEdit.putString("city",client_city.getText().toString().trim().toLowerCase());
                    myEdit.putString("key","1");
                    myEdit.commit();

                    db.collection("customer").document(doc).set(client);
                    Toast.makeText(client_update_info.this, "profile changed succesfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), client_menu_1.class));

                }
            }
        });
    }
}