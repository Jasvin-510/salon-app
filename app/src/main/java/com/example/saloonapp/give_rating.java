package com.example.saloonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class give_rating extends AppCompatActivity {

    RatingBar ratingBar;
    Button bt1;
    TextView name,m1,m2,m3,m4,m5,g1,g2,g3,g4,g5,men,women;
    private String s1,s2,s3,s4;

    private FirebaseFirestore dp=FirebaseFirestore.getInstance();
    private CollectionReference ref1=dp.collection("saloon");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_rating);
        setTitle("Give Rating");

        ratingBar=findViewById(R.id.ratingBar2);
        bt1=findViewById(R.id.button9);
        name=findViewById(R.id.textView40);
        men=findViewById(R.id.textView41);
        m1=findViewById(R.id.textView42);
        m2=findViewById(R.id.textView43);
        m3=findViewById(R.id.textView44);
        m4=findViewById(R.id.textView45);
        m5=findViewById(R.id.textView46);
        women=findViewById(R.id.textView47);
        g1=findViewById(R.id.textView48);
        g2=findViewById(R.id.textView49);
        g3=findViewById(R.id.textView50);
        g4=findViewById(R.id.textView51);
        g5=findViewById(R.id.textView52);

        Intent intent = getIntent();
        final  String id1=intent.getStringExtra("client_ref");
        final  String id2=intent.getStringExtra("time");

        dp.collection("customer/"+id1+"/history")
                .whereEqualTo("time",id2)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot q:queryDocumentSnapshots)
                        {
                            s1="a";
                            s4=q.getId();
                            s3=q.getString("saloon_ref");
                            name.setText(q.get("saloon_name").toString());
                            if(q.getString("men_hair_cutting").equals("yes"))
                            {s1="b";m1.setText("hair cutting");}else{m1.setVisibility(View.GONE);}
                            if(q.getString("men_hair_styling").equals("yes"))
                            {s1="b";m2.setText("hair styling");}else{m2.setVisibility(View.GONE);}
                            if(q.getString("men_hair_colour").equals("yes"))
                            {s1="b";m3.setText("hair colour");}else{m3.setVisibility(View.GONE);}
                            if(q.getString("men_facial_and_skin_care").equals("yes"))
                            {s1="b";m4.setText("facial and skin care");}else{m4.setVisibility(View.GONE);}
                            if(q.getString("men_massages").equals("yes"))
                            {s1="b";m5.setText("massages");}else{m5.setVisibility(View.GONE);}
                            if(s1.equals("a"))
                            { men.setVisibility(View.GONE); }

                            s2="a";
                            if(q.getString("women_custom_makeup").equals("yes"))
                            {s2="b";g1.setText("custom makeup");}else{g1.setVisibility(View.GONE);}
                            if(q.getString("women_eye_brow").equals("yes"))
                            {s2="b";g2.setText("eye brow");}else{g2.setVisibility(View.GONE);}
                            if(q.getString("women_facial").equals("yes"))
                            {s2="b";g3.setText("facial");}else{g3.setVisibility(View.GONE);}
                            if(q.getString("women_hair_cutting").equals("yes"))
                            {s2="b";g4.setText("hair cutting");}else{g4.setVisibility(View.GONE);}
                            if(q.getString("women_nail_service").equals("yes"))
                            {s2="b";g5.setText("nail service");}else{g5.setVisibility(View.GONE);}
                            if(s2.equals("a"))
                            { women.setVisibility(View.GONE); }

                        }
                    }
                });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String t1=String.valueOf(ratingBar.getRating());
                if(t1.equals("0.0"))
                {
                    Toast.makeText(give_rating.this, "please give rating!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                  dp.document("saloon/"+s3)
                          .get()
                          .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                              @Override
                              public void onSuccess(DocumentSnapshot documentSnapshot) {
                                  float f1= Float.parseFloat(documentSnapshot.getString("total_client"));
                                  float f2= Float.parseFloat(documentSnapshot.getString("total_vote"));

                                  f1= (float) (f1+1.0);
                                  f2=f2+Float.parseFloat(t1);

                                  Map<String, Object> map=new HashMap<>();
                                  map.put("total_client",Float.toString(f1));
                                  map.put("total_vote",String.valueOf(f2));
                                  map.put("rating",String.valueOf((f2/f1)));
                                  DocumentReference d1=dp.document("saloon/"+s3);
                                  d1.set(map, SetOptions.merge());
                                  DocumentReference d2=dp.document("customer/"+id1+"/history/"+s4);
                                  d2.update("rating",t1);
                                  startActivity(new Intent(getApplicationContext(),client_rating.class));
                              }
                          });

                }
            }
        });

    }
}