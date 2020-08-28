package com.example.saloonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class client_saloon_info extends AppCompatActivity {

    TextView name, add, no, female, male;
    Button button;
    CheckBox select1, select2, select3, select4, select5, select6, select7, select8, select9, select10;
    TextView price1, price2, price3, price4, price5, price6, price7, price8, price9, price10;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf = db.collection("saloon");
    private DocumentReference cf1;
    private CollectionReference cf2, cf3, cf4;
    private QueryDocumentSnapshot d;
    private String s1,s2,s3,s4,s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_saloon_info);
        setTitle("Saloon Info");

        name = findViewById(R.id.textView);
        add = findViewById(R.id.textView7);
        no = findViewById(R.id.textView8);
        male = findViewById(R.id.textView9);
        female = findViewById(R.id.textView10);
        button = findViewById(R.id.button2);
        select1 = findViewById(R.id.select1);
        select2 = findViewById(R.id.select2);
        select3 = findViewById(R.id.select3);
        select4 = findViewById(R.id.select4);
        select5 = findViewById(R.id.select5);
        select6 = findViewById(R.id.select6);
        select7 = findViewById(R.id.select7);
        select8 = findViewById(R.id.select8);
        select9 = findViewById(R.id.select9);
        select10 = findViewById(R.id.select10);

        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        price3 = findViewById(R.id.price3);
        price4 = findViewById(R.id.price4);
        price5 = findViewById(R.id.price5);
        price6 = findViewById(R.id.price6);
        price7 = findViewById(R.id.price7);
        price8 = findViewById(R.id.price8);
        price9 = findViewById(R.id.price9);
        price10 = findViewById(R.id.price10);

        Intent intent2 = getIntent();
        final String id1 = intent2.getStringExtra("ID");

        SharedPreferences sh = getSharedPreferences("sp",MODE_PRIVATE);
        final String id3 = sh.getString("client_email", "");
        final String id4=sh.getString("city", "");
        final String id11=sh.getString("client_ref","");
      /*  final String id3 = intent2.getStringExtra("email");
        final String id4=intent2.getStringExtra("city");
        final String id11=intent2.getStringExtra("client_ref");*/

        cf.whereEqualTo("id", id1).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (final QueryDocumentSnapshot document : task.getResult()) {
                                d = document;
                                cf3 = db.collection("saloon").document(document.getId()).collection("history");
                                cf2 = db.collection("saloon").document(document.getId()).collection("request");
                                object object1 = d.toObject(object.class);
                                name.setText(object1.getName());
                                add.setText(d.get("address").toString());
                                no.setText(d.get("number").toString());

                                final Map<String, String> m1 = (Map<String, String>) d.get("male");
                                final Map<String, String> g1 = (Map<String, String>) d.get("female");
                                s1="a";s2="a";s3="a";s4="a";s5="a";

                                Iterator myVeryOwnIterator = m1.keySet().iterator();
                                while(myVeryOwnIterator.hasNext()) {
                                    String key=(String)myVeryOwnIterator.next();
                                    String value=(String)m1.get(key);
                                    if(key.equals("Hair Cutting")) { price1.setText(value);s1="b"; }
                                    if(key.equals("Hair Colour")) { price2.setText(value);s2="b"; }
                                    if(key.equals("Hair Styling")) { price3.setText(value);s3="b"; }
                                    if(key.equals("Facial and Skin Care")) { price4.setText(value);s4="b"; }
                                    if(key.equals("Massages")) { price5.setText(value);s5="b"; }
                                }
                                if(s1.equals("a")){price1.setVisibility(View.GONE);select1.setVisibility(View.GONE);}
                                if(s2.equals("a")){price2.setVisibility(View.GONE);select2.setVisibility(View.GONE);}
                                if(s3.equals("a")){price3.setVisibility(View.GONE);select3.setVisibility(View.GONE);}
                                if(s4.equals("a")){price4.setVisibility(View.GONE);select4.setVisibility(View.GONE);}
                                if(s5.equals("a")){price5.setVisibility(View.GONE);select5.setVisibility(View.GONE);}

                                if(s1.equals("a") && s2.equals("a") && s3.equals("a") && s4.equals("a") && s5.equals("a"))
                                {
                                    male.setVisibility(View.GONE);
                                }
                                s1="a";s2="a";s3="a";s4="a";s5="a";


                                Iterator myVeryOwnIterator1 = g1.keySet().iterator();
                                while(myVeryOwnIterator1.hasNext()) {
                                    String key=(String)myVeryOwnIterator1.next();
                                    String value=(String)g1.get(key);
                                    if(key.equals("Eye brow")) { price6.setText(value);s1="b"; }
                                    if(key.equals("Nail Service")) { price7.setText(value);s2="b"; }
                                    if(key.equals("Facial")) { price8.setText(value);s3="b"; }
                                    if(key.equals("Custom Makeup")) { price9.setText(value);s4="b"; }
                                    if(key.equals("Hair Cutting")) { price10.setText(value);s5="b"; }
                                }
                                if(s1.equals("a")){price6.setVisibility(View.GONE);select6.setVisibility(View.GONE);}
                                if(s2.equals("a")){price7.setVisibility(View.GONE);select7.setVisibility(View.GONE);}
                                if(s3.equals("a")){price8.setVisibility(View.GONE);select8.setVisibility(View.GONE);}
                                if(s4.equals("a")){price9.setVisibility(View.GONE);select9.setVisibility(View.GONE);}
                                if(s5.equals("a")){price10.setVisibility(View.GONE);select10.setVisibility(View.GONE);}

                                if(s1.equals("a") && s2.equals("a") && s3.equals("a") && s4.equals("a") && s5.equals("a"))
                                {
                                    female.setVisibility(View.GONE);
                                }

                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        db.collection("customer").whereEqualTo("id", id3)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            for (QueryDocumentSnapshot document1 : task.getResult())
                                                            {
                                                                cf4=db.collection("customer").document(document1.getId()).collection("history");
                                                                Map<String, Object> m = new HashMap<>();
                                                                m.put("saloon_email", id1);
                                                                m.put("client_email", id3);
                                                                m.put("name", document1.get("name"));
                                                                m.put("mobile_no", document1.get("number"));
                                                                m.put("address", document1.get("address"));

                                                                String che;
                                                                che="a";
                                                                if (select1.isChecked()) {che="b"; m.put("men_hair_cutting", "yes"); }
                                                                else { m.put("men_hair_cutting", "no"); }

                                                                if (select2.isChecked()) { che="b";m.put("men_hair_colour", "yes"); }
                                                                else { m.put("men_hair_colour", "no"); }

                                                                if (select3.isChecked()) { che="b";m.put("men_hair_styling", "yes"); }
                                                                else { m.put("men_hair_styling", "no"); }

                                                                if (select4.isChecked()) {
                                                                    che="b"; m.put("men_facial_and_skin_care", "yes");
                                                                }else { m.put("men_facial_and_skin_care", "no"); }

                                                                if (select5.isChecked()) {
                                                                    che="b";   m.put("men_massages", "yes");
                                                                }else { m.put("men_massages", "no"); }

                                                                if (select6.isChecked()) {
                                                                    che="b"; m.put("women_eye_brow", "yes");
                                                                }else { m.put("women_eye_brow", "no"); }

                                                                if (select7.isChecked()) {
                                                                    che="b";   m.put("women_nail_service", "yes");
                                                                }else { m.put("women_nail_service", "no"); }

                                                                if (select8.isChecked()) {
                                                                    che="b"; m.put("women_facial", "yes");
                                                                }else { m.put("women_facial", "no"); }

                                                                if (select9.isChecked()) {
                                                                    che="b";  m.put("women_custom_makeup", "yes");
                                                                }else { m.put("women_custom_makeup", "no"); }

                                                                if (select10.isChecked()) {
                                                                    che="b"; m.put("women_hair_cutting", "yes");
                                                                }else { m.put("women_hair_cutting", "no"); }

                                                                if(che.equals("a"))
                                                                {
                                                                    Toast.makeText(client_saloon_info.this, "select service", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {
                                                                    long time = System.currentTimeMillis();
                                                                    m.put("time", Long.toString(time));
                                                                    Date today = new Date();

                                                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                                                                    String dateToStr = format.format(today);
                                                                    m.put("current_time", dateToStr);
                                                                    m.put("client_ref", document1.getId());
                                                                    m.put("saloon_ref", document.getId());
                                                                    m.put("rating", "no_rating");
                                                                    cf2.add(m);
                                                                    m.put("status", "pending");
                                                                    cf3.add(m);
                                                                    m.put("saloon_name", d.getString("name"));
                                                                    cf4.add(m);
                                                                    Toast.makeText(client_saloon_info.this, "booking complete!!!", Toast.LENGTH_SHORT).show();
                                                                    Intent intent = new Intent(getApplicationContext(), client_homepage.class);
                                                                    //   intent.putExtra("email", id3);
                                                                    //   intent.putExtra("city",id4);
                                                                    //   intent.putExtra("client_ref",id11);
                                                                    startActivity(intent);
                                                                }
                                                            }
                                                        } else {
                                                            Toast.makeText(client_saloon_info.this, "error while loading!!! ", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                });
                            }
                        }
                        }
                });


    }
}