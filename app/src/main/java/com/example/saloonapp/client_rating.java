package com.example.saloonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class client_rating extends AppCompatActivity implements client_rating_adapter.saloonclick{

    private FirebaseFirestore dp=FirebaseFirestore.getInstance();
    private CollectionReference ref=dp.collection("saloon");
    private CollectionReference ref1=dp.collection("customer");
    private client_rating_adapter adapter;
    private String id1,clientcity,client_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_rating);
        setTitle("Rating");

        SharedPreferences sh = getSharedPreferences("sp",MODE_PRIVATE);
        client_ref =sh.getString("client_ref","");

        Query query=dp.collection("customer/"+client_ref+"/history").whereEqualTo("status","complete")
                .whereEqualTo("rating","no_rating");
        FirestoreRecyclerOptions<object2> options = new FirestoreRecyclerOptions.Builder<object2>()
                .setQuery(query, object2.class)
                .build();
        adapter = new client_rating_adapter(options,this);
        RecyclerView recyclerView = findViewById(R.id.recycler_rating);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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
        startActivity(new Intent(getApplicationContext(),client_menu_1.class));
    }

    @Override
    public void onsaloonclick(String s, int position) {
        Intent intent5 = new Intent(this, give_rating.class);
      //  Toast.makeText(this, "a="+s, Toast.LENGTH_SHORT).show();
        intent5.putExtra("time", s);
        intent5.putExtra("client_ref",client_ref);
        startActivity(intent5);
    }
}