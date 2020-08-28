package com.example.saloonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class login_saloon extends AppCompatActivity
{
    EditText id_login_saloon,password_login_saloon;
    Button login_saloon;
    FirebaseAuth fAuth;
    TextView register,password;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf =db.collection("customer");
    private CollectionReference cf1 =db.collection("saloon");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_saloon);
        setTitle("login for saloon");

        login_saloon=findViewById(R.id.login_regis_client);
        id_login_saloon=findViewById(R.id.id_regis_client);
        password=findViewById(R.id.textView53);
        register=findViewById(R.id.textView37);
        password_login_saloon=findViewById(R.id.password_regis_client);
        fAuth=FirebaseAuth.getInstance();

        SharedPreferences sh = getSharedPreferences("sp1", MODE_PRIVATE);
        final String id11 = sh.getString("saloon_ref","ja");

        if(id11.equals("ja"))
        {

        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), saloon_homepage.class);
            startActivity(intent);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register_saloon.class));
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email1=id_login_saloon.getText().toString().trim();
                if (TextUtils.isEmpty(email1))
                {
                    Toast.makeText(login_saloon.this, "Enter email", Toast.LENGTH_SHORT).show();
                    id_login_saloon.setError("email is required");
                }
                else
                {
                    fAuth.sendPasswordResetEmail(email1);
                    Toast.makeText(login_saloon.this, "Link sent to your email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login_saloon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=id_login_saloon.getText().toString().trim();
                final String password=password_login_saloon.getText().toString().trim();
                cf.whereEqualTo("id",email)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                String check = null;
                                for (QueryDocumentSnapshot d : queryDocumentSnapshots) 
                                {
                                    check = d.getString("id");
                                }
                                if (TextUtils.isEmpty(check)) {
                                    if (TextUtils.isEmpty(email)) {
                                        id_login_saloon.setError("id is required.");
                                    } else if (TextUtils.isEmpty(password)) {
                                        password_login_saloon.setError("password is required.");
                                    } else {
                                        fAuth.signInWithEmailAndPassword(email, password)
                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(getApplicationContext(), "saloon login successful!", Toast.LENGTH_LONG).show();

                                                            db.collection("saloon").whereEqualTo("id", email).get()
                                                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                        @Override
                                                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                            for (QueryDocumentSnapshot d : queryDocumentSnapshots) {
                                                                                final String id2 = d.getId();
                                                                                SharedPreferences sharedPreferences = getSharedPreferences("sp1", MODE_PRIVATE);
                                                                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                                                                myEdit.putString("saloon_ref",id2);
                                                                                myEdit.putString("email",email);
                                                                                myEdit.putString("password",password);
                                                                                myEdit.putString("name",d.get("name").toString());
                                                                                myEdit.putString("link",d.get("link").toString());
                                                                                myEdit.commit();
                                                                                Intent intent = new Intent(getApplicationContext(), saloon_homepage.class);
                                                                              //  intent.putExtra("saloon_ref", id2);
                                                                                startActivity(intent);
                                                                            }
                                                                        }
                                                                    });
                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "saloon login failed!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                    }
                                } else {
                                    Toast.makeText(login_saloon.this, "invalid id!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}