package com.example.saloonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register_client extends AppCompatActivity {

    EditText id_regis_client,name_regis_client,password_regis_client,mo_no_regis_client,add_regis_client,client_city;
    Button login_regis_client;
    FirebaseAuth fAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf =db.collection("customer");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
        setTitle("Customer Registration");

        id_regis_client =findViewById(R.id.id_regis_client);
        name_regis_client =findViewById(R.id.name_regis_client);
        password_regis_client =findViewById(R.id.password_regis_client);
        mo_no_regis_client =findViewById(R.id.mo_no_regis_client);
        add_regis_client =findViewById(R.id.add_regis_client);
        client_city=findViewById(R.id.client_city);

        login_regis_client=findViewById(R.id.login_regis_client);

        fAuth=FirebaseAuth.getInstance();


        login_regis_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=id_regis_client.getText().toString().trim();
                final String password=password_regis_client.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    id_regis_client.setError("id is required.");
                }
                else if(TextUtils.isEmpty(password))
                {
                    password_regis_client.setError("password is required.");
                }
                else if(TextUtils.isEmpty(client_city.getText().toString().trim()))
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
                else {
                    fAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                        Map<String, Object> client = new HashMap<>();
                                        client.put("id", email);
                                        client.put("password", password);
                                        client.put("name", name_regis_client.getText().toString().trim());
                                        client.put("number", mo_no_regis_client.getText().toString().trim());
                                        client.put("address", add_regis_client.getText().toString().trim());
                                        client.put("city",client_city.getText().toString().trim().toLowerCase());
                                        cf.add(client);
                                        /*SharedPreferences sh = getSharedPreferences("sp", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sh.edit();
                                        myEdit.remove("client_ref");
                                        myEdit.remove("client_email");
                                        myEdit.remove("city");
                                        myEdit.remove("password");
                                        myEdit.commit();*/
                                        SharedPreferences sh1 = getSharedPreferences("sp", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit1 = sh1.edit();
                                        myEdit1.remove("client_ref");
                                        myEdit1.remove("client_email");
                                        myEdit1.remove("city");
                                        myEdit1.remove("password");
                                        myEdit1.commit();
                                        startActivity(new Intent(getApplicationContext(), login_client.class));
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration failed!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });
    }
}
