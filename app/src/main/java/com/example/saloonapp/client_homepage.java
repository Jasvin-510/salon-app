package com.example.saloonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.Serializable;

public class client_homepage extends AppCompatActivity implements object_adapter.saloonclick {

    Button button;

    private FirebaseFirestore dp=FirebaseFirestore.getInstance();
    private CollectionReference ref=dp.collection("saloon");
    private CollectionReference ref1=dp.collection("customer");
    private object_adapter adapter;
    private String id1,clientcity,client_ref,key,a;
    ImageView menu1,filter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_homepage);

        getSupportActionBar().hide();

        menu1=findViewById(R.id.menu1);
        filter1=findViewById(R.id.filter);

        SharedPreferences sh = getSharedPreferences("sp",MODE_PRIVATE);
        id1 = sh.getString("client_email", "");
        clientcity=sh.getString("city", "");
        client_ref=sh.getString("client_ref","");
        key=sh.getString("key","1");


        Query query = ref.whereEqualTo("verified", true)
                  .whereEqualTo("city", clientcity)
                 .orderBy("rating", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<object> options = new FirestoreRecyclerOptions.Builder<object>()
                .setQuery(query, object.class)
                .build();
        adapter = new object_adapter(options,this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        if(key.equals("1")) {
            Query query1 = ref.whereEqualTo("verified", true)
                    .whereEqualTo("city", clientcity)
                    .whereEqualTo("w","yes")
                    .orderBy("rating", Query.Direction.DESCENDING);
            FirestoreRecyclerOptions<object> options1 = new FirestoreRecyclerOptions.Builder<object>()
                    .setQuery(query1, object.class)
                    .build();
            adapter.updateOptions(options1);
        }
        else if(key.equals("2"))
        {
            Query query2 = ref.whereEqualTo("verified", true)
                    .whereEqualTo("city", clientcity)
                    .whereEqualTo("m","yes")
                    .orderBy("rating", Query.Direction.DESCENDING);
            FirestoreRecyclerOptions<object> options2 = new FirestoreRecyclerOptions.Builder<object>()
                    .setQuery(query2, object.class)
                    .build();
            adapter.updateOptions(options2);
       }

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),client_menu_1.class);
                startActivity(intent2);
            }
        });

        filter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),client_filter.class));
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    public void onsaloonclick(String s, int position) {
        Intent intent1 = new Intent(this, client_saloon_info.class);
        intent1.putExtra("ID",  s);
        startActivity(intent1);
    }
}