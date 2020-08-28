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

public class login_client extends AppCompatActivity {

    EditText id_login_client,password_login_client;
    Button login_client;
    FirebaseAuth fAuth;
    TextView register,password;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf =db.collection("saloon");
    private CollectionReference cf1 =db.collection("customer");
    private String city,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);
        setTitle("Login for Customer");

        login_client=findViewById(R.id.login_regis_client);
        id_login_client=findViewById(R.id.id_regis_client);
        register=findViewById(R.id.textView31);
        password=findViewById(R.id.textView36);
        password_login_client=findViewById(R.id.password_regis_client);
        fAuth=FirebaseAuth.getInstance();

        SharedPreferences sh = getSharedPreferences("sp", MODE_PRIVATE);
         String id11 = sh.getString("client_email","ja");
       // Toast.makeText(this, "="+id11, Toast.LENGTH_SHORT).show();

        if(id11.equals("ja"))
        {
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), client_homepage.class);
            startActivity(intent);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register_client.class));
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email1=id_login_client.getText().toString().trim();
                if (TextUtils.isEmpty(email1))
                {
                    Toast.makeText(login_client.this, "Enter email", Toast.LENGTH_SHORT).show();
                    id_login_client.setError("email is required.");
                }
                else
                {
                    fAuth.sendPasswordResetEmail(email1);
                    Toast.makeText(login_client.this, "Link send to your email-", Toast.LENGTH_SHORT).show();
                }
            }
        });
        

        login_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id,pass;

                final String email=id_login_client.getText().toString().trim();
                final String password=password_login_client.getText().toString().trim();
            cf.whereEqualTo("id",email)
                  .get()
                  .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                      @Override
                      public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                          String check = null;
                          for (QueryDocumentSnapshot d : queryDocumentSnapshots) {
                              check = d.getString("id");
                          }

                if(TextUtils.isEmpty(check))
                {
                    if (TextUtils.isEmpty(email))
                    {
                        id_login_client.setError("id is required.");
                    }
                    else if (TextUtils.isEmpty(password))
                    {
                        password_login_client.setError("password is required.");
                    } else
                        {
                        fAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful())
                                        {
                                            cf1.whereEqualTo("id",email)
                                                    .get()
                                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                                                        {
                                                            Toast.makeText(getApplicationContext(), "client login successful!", Toast.LENGTH_LONG).show();
                                                            for (QueryDocumentSnapshot d:queryDocumentSnapshots)
                                                            {
                                                                city=d.getString("city");
                                                                ref=d.getId();
                                                                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                                                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                                                myEdit.putString("client_ref",ref);
                                                                myEdit.putString("client_email",email);
                                                                myEdit.putString("password",password);
                                                                myEdit.putString("city",city);
                                                                myEdit.putString("key","1");
                                                                myEdit.commit();
                                                            }
                                                                Intent intent = new Intent(getApplicationContext(), client_homepage.class);
                                                                startActivity(intent);
                                                        }
                                                    });
                                        } else
                                            {
                                            Toast.makeText(getApplicationContext(), "client login failed!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                }
                else
                {
                    Toast.makeText(login_client.this, "invalid id!!", Toast.LENGTH_SHORT).show();
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
