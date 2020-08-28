package com.example.saloonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class saloon_update_info extends AppCompatActivity {

    EditText saloon_id,saloon_password,saloon_name,saloon_number,saloon_address,saloon_city;
    EditText Text,Text2,Text3,Text4,Text5,Text6,Text7,Text8,Text9,Text10;
    Button button,image_button;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox,checkBox7,checkBox8,checkBox9;
    private Uri mImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageReference Folder,Folder1;
    private Bitmap link1;
    private ProgressBar progressBar;
    private String s1="a",link,email,password,name,id3,client,vote,rating;

    FirebaseAuth fAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf =db.collection("saloon");
    private ArrayList<String> check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_update_info);

        setTitle("Change Peofile");

       // saloon_id=findViewById(R.id.saloon_id);
      //  progressBar=findViewById(R.id.progressBar);
      //  saloon_password=findViewById(R.id.saloon_password);
       // saloon_name=findViewById(R.id.saloon_name);
        saloon_number=findViewById(R.id.phone_number);
        saloon_address=findViewById(R.id.saloon_address);
        saloon_city=findViewById(R.id.saloon_city);
        Text=findViewById(R.id.editTextNumber);
        Text2=findViewById(R.id.editTextNumber2);
        Text3=findViewById(R.id.editTextNumber3);
        Text4=findViewById(R.id.editTextNumber4);
        Text5=findViewById(R.id.editTextNumber5);
        Text6=findViewById(R.id.editTextNumber6);
        Text7=findViewById(R.id.editTextNumber7);
        Text8=findViewById(R.id.editTextNumber8);
        Text9=findViewById(R.id.editTextNumber9);
        Text10=findViewById(R.id.editTextNumber10);
        button=findViewById(R.id.button);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);
        checkBox7=findViewById(R.id.checkBox7);
        checkBox8=findViewById(R.id.checkBox8);
        checkBox9=findViewById(R.id.checkBox9);
        checkBox=findViewById(R.id.checkBox);
        SharedPreferences sh = getSharedPreferences("sp1", MODE_PRIVATE);
        email = sh.getString("email","ja");
        password=sh.getString("password","12");
        name=sh.getString("name","ja");
        link=sh.getString("link","l");
        id3=sh.getString("saloon_ref","ja");

        fAuth=FirebaseAuth.getInstance();
        check=new ArrayList<>();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   final String email = saloon_id.getText().toString().trim();
             //   final String password = saloon_password.getText().toString().trim();

                db.collection("saloon").document(id3).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                               client=documentSnapshot.get("total_client").toString();
                               vote=documentSnapshot.getString("total_vote");
                               rating=documentSnapshot.getString("rating");



                if(TextUtils.isEmpty(saloon_city.getText().toString().trim()))
                {
                    saloon_city.setError("city is required");
                }
                else if(TextUtils.isEmpty(saloon_number.getText().toString().trim()))
                {
                    saloon_number.setError("mobile number is required");
                }
                else if(TextUtils.isEmpty(saloon_address.getText().toString().trim()))
                {
                    saloon_address.setError("address is required");
                }
                else if(checkBox1.isChecked() && TextUtils.isEmpty(Text2.getText().toString().trim()))
                {
                    Text2.setError("price is required");
                }
                else if(checkBox2.isChecked() && TextUtils.isEmpty(Text3.getText().toString().trim()))
                {
                    Text3.setError("price is required");
                }
                else if(checkBox3.isChecked() && TextUtils.isEmpty(Text4.getText().toString().trim()))
                {
                    Text4.setError("price is required");
                }
                else if(checkBox4.isChecked() && TextUtils.isEmpty(Text5.getText().toString().trim()))
                {
                    Text5.setError("price is required");
                }
                else if(checkBox5.isChecked() && TextUtils.isEmpty(Text6.getText().toString().trim()))
                {
                    Text6.setError("price is required");
                }
                else if(checkBox6.isChecked() && TextUtils.isEmpty(Text7.getText().toString().trim()))
                {
                    Text7.setError("price is required");
                }
                else if(checkBox7.isChecked() && TextUtils.isEmpty(Text8.getText().toString().trim()))
                {
                    Text8.setError("price is required");
                }
                else if(checkBox8.isChecked() && TextUtils.isEmpty(Text9.getText().toString().trim()))
                {
                    Text9.setError("price is required");
                }
                else if(checkBox9.isChecked() && TextUtils.isEmpty(Text10.getText().toString().trim()))
                {
                    Text10.setError("price is required");
                }
                else if(checkBox.isChecked() && TextUtils.isEmpty(Text.getText().toString().trim()))
                {
                    Text.setError("price is required");
                }
                else {
                                        Map<String, Object> saloon = new HashMap<>();
                                        saloon.put("id", email);
                                        saloon.put("password", password);
                                        saloon.put("name", name);
                                        saloon.put("number", saloon_number.getText().toString().trim());
                                        saloon.put("address", saloon_address.getText().toString().trim());
                                        saloon.put("city",saloon_city.getText().toString().trim().toLowerCase());
                                        saloon.put("verified",true);
                                        saloon.put("link",link);
                                        saloon.put("total_client",client);
                                        saloon.put("total_vote",vote);
                                        saloon.put("rating",rating);
                                        Map<String, String> m1=new HashMap<>();
                                        Map<String, String> g1=new HashMap<>();

                                        if(checkBox1.isChecked())
                                        {
                                            m1.put("Hair Cutting",Text2.getText().toString());
                                        }

                                        String sm,sw;
                                        sm="a";
                                        if(checkBox2.isChecked()) { m1.put("Hair Colour",Text3.getText().toString());sm="b";}
                                        if(checkBox3.isChecked()) { m1.put("Hair Styling",Text4.getText().toString());sm="b";}
                                        if(checkBox4.isChecked()) { m1.put("Facial and Skin Care",Text5.getText().toString());sm="b";}
                                        if(checkBox5.isChecked()) { m1.put("Massages",Text6.getText().toString());sm="b";}

                                        sw="a";
                                        if(checkBox.isChecked())  { g1.put("Eye brow",Text.getText().toString());sw="b";}
                                        if(checkBox6.isChecked()) { g1.put("Nail Service",Text7.getText().toString());sw="b";}
                                        if(checkBox7.isChecked()) { g1.put("Facial",Text8.getText().toString());sw="b";}
                                        if(checkBox8.isChecked()) { g1.put("Custom Makeup",Text9.getText().toString());sw="b";}
                                        if(checkBox9.isChecked()) { g1.put("Hair Cutting",Text10.getText().toString());sw="b";}
                                        saloon.put("male",m1);
                                        saloon.put("female",g1);
                                        if(sm.equals("a"))
                                        {
                                            saloon.put("m","no");
                                        }
                                        else
                                        {
                                            saloon.put("m","yes");
                                        }

                                        if(sw.equals("a"))
                                        {
                                            saloon.put("w","no");
                                        }
                                        else
                                        {
                                            saloon.put("w","yes");
                                        }

                                        db.collection("saloon").document(id3).set(saloon);


                                        startActivity(new Intent(getApplicationContext(),saloon_menu_1.class));

                }
            }
        });


            }
        });
    }
}