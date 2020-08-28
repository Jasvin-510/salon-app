package com.example.saloonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class saloon_homepage2 extends AppCompatActivity {

    TextView  name,con,add,cmen,cwomen,m1,m2,m3,m4,m5,g1,g2,g3,g4,g5;
    Button yesbutton,nobutton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf,cf1;
    private DocumentReference cd1,cd2,cd3;
    private String sm,sg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_homepage2);
        setTitle("Order Info");

         Intent intent = getIntent();
         final String time_id = intent.getStringExtra("time");
         final String saloon_ref=intent.getStringExtra("saloon_ref");

         name=findViewById(R.id.textView12);
         con=findViewById(R.id.textView17);
         add=findViewById(R.id.textView14);
         cmen=findViewById(R.id.textView18);
         cwomen=findViewById(R.id.textView24);
         m1=findViewById(R.id.textView19);
         m2=findViewById(R.id.textView20);
         m3=findViewById(R.id.textView21);
         m4=findViewById(R.id.textView22);
         m5=findViewById(R.id.textView23);
         g1=findViewById(R.id.textView25);
         g2=findViewById(R.id.textView26);
         g3=findViewById(R.id.textView27);
         g4=findViewById(R.id.textView28);
         g5=findViewById(R.id.textView29);
         yesbutton=findViewById(R.id.button4);
         nobutton=findViewById(R.id.button5);

         db.collection("saloon/"+saloon_ref+"/request")
                .whereEqualTo("time",time_id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                           for(QueryDocumentSnapshot d:queryDocumentSnapshots)
                           {
                               cd3=db.document("saloon/"+saloon_ref+"/request/"+d.getId());
                               object2 obj=d.toObject(object2.class);
                               name.setText(obj.getName());
                               con.setText(obj.getMobile_no());
                               add.setText(obj.getAddress());
                               sm="a";


                               if(d.getString("men_hair_cutting").equals("yes")) { sm="b"; }
                               else { m1.setVisibility(View.GONE); }

                               if(d.getString("men_hair_colour").equals("yes")) {sm="b"; } else { m2.setVisibility(View.GONE); }
                               if(d.getString("men_hair_styling").equals("yes")) { sm="b";} else { m3.setVisibility(View.GONE); }
                               if(d.getString("men_facial_and_skin_care").equals("yes")) { sm="b";} else { m4.setVisibility(View.GONE); }
                               if(d.getString("men_massages").equals("yes")) {sm="b"; } else { m5.setVisibility(View.GONE); }
                               if(sm.equals("a")) { cmen.setVisibility(View.GONE); }
                               sg="a";

                               if(d.getString("women_eye_brow").equals("yes")) {sg="b"; } else { g1.setVisibility(View.GONE); }
                               if(d.getString("women_nail_service").equals("yes")) {sg="b"; } else { g2.setVisibility(View.GONE); }
                               if(d.getString("women_facial").equals("yes")) {sg="b"; } else { g3.setVisibility(View.GONE); }
                               if(d.getString("women_custom_makeup").equals("yes")) {sg="b"; } else { g4.setVisibility(View.GONE); }
                               if(d.getString("women_hair_cutting").equals("yes")) {sg="b"; } else { g5.setVisibility(View.GONE); }
                               if(sg.equals("a")) { cwomen.setVisibility(View.GONE); }

                               yesbutton.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       nobutton.setVisibility(View.INVISIBLE);
                                       db.collection("saloon/"+saloon_ref+"/history")
                                               .whereEqualTo("time",time_id)
                                               .get()
                                               .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                   @Override
                                                   public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                       for(QueryDocumentSnapshot d1:queryDocumentSnapshots)
                                                       {
                                                             cd1=db.document("saloon/"+saloon_ref+"/history/"+d1.getId());
                                                             cd1.update("status","complete");
                                                             db.collection("customer/"+d1.getString("client_ref")+"/history")
                                                                   .whereEqualTo("time",time_id)
                                                                   .get()
                                                                   .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                       @Override
                                                                       public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                           for(QueryDocumentSnapshot d2:queryDocumentSnapshots)
                                                                           {
                                                                               cd2=db.document("customer/"+d2.getString("client_ref")+"/history/"+d2.getId());
                                                                               cd2.update("status","complete");
                                                                           }
                                                                       }
                                                                   });
                                                                  cd3.delete();
                                                           startActivity(new Intent(getApplicationContext(),saloon_homepage.class));

                                                       }


                                                   }
                                               });
                                   }
                               });

                               nobutton.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       yesbutton.setVisibility(View.INVISIBLE);
                                       db.collection("saloon/"+saloon_ref+"/history")
                                               .whereEqualTo("time",time_id)
                                               .get()
                                               .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                   @Override
                                                   public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                       for(QueryDocumentSnapshot d1:queryDocumentSnapshots)
                                                       {
                                                           cd1=db.document("saloon/"+saloon_ref+"/history/"+d1.getId());
                                                           cd1.update("status","cancelled");
                                                           db.collection("customer/"+d1.getString("client_ref")+"/history")
                                                                   .whereEqualTo("time",time_id)
                                                                   .get()
                                                                   .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                       @Override
                                                                       public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                           for(QueryDocumentSnapshot d2:queryDocumentSnapshots)
                                                                           {
                                                                               cd2=db.document("customer/"+d2.getString("client_ref")+"/history/"+d2.getId());
                                                                               cd2.update("status","cancelled");
                                                                           }
                                                                       }
                                                                   });
                                                           cd3.delete();
                                                           startActivity(new Intent(getApplicationContext(),saloon_homepage.class));
                                                       }
                                                   }
                                               });
                                   }
                               });
                           }
                    }
                });


         
    }

}