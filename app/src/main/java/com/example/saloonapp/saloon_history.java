package com.example.saloonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class saloon_history extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf,cf1;
    private saloon_history_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_history);
        setTitle("History");

        SharedPreferences sh = getSharedPreferences("sp1",MODE_PRIVATE);
        final String id1 = sh.getString("saloon_ref", "");

        Query query=db.collection("saloon/"+id1+"/history").orderBy("time", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<object2> options = new FirestoreRecyclerOptions.Builder<object2>()
                .setQuery(query, object2.class)
                .build();

        adapter=new saloon_history_adapter(options);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
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
}