package com.example.saloonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class saloon_homepage extends AppCompatActivity implements saloon_request_adapter.saloonclick{

 //   TextView getid;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference cf,cf1;
    private saloon_request_adapter adapter;
    private String saloon_id_ref;
    ImageView menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_homepage);

        getSupportActionBar().hide();

        menu2=findViewById(R.id.menu2);

        Intent intent = getIntent();
        SharedPreferences sh = getSharedPreferences("sp1",MODE_PRIVATE);
        final String id1 = sh.getString("saloon_ref", "");
     //   final String id1 =intent.getStringExtra("saloon_ref");
        saloon_id_ref=id1;


                            Query query=db.collection("saloon/"+id1+"/request")
                                    .orderBy("time",Query.Direction.ASCENDING);
                            FirestoreRecyclerOptions<object2> options = new FirestoreRecyclerOptions.Builder<object2>()
                                    .setQuery(query, object2.class)
                                    .build();
                            adapter = new saloon_request_adapter(options,this);
                            RecyclerView recyclerView = findViewById(R.id.recycler_view1);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setAdapter(adapter);

                            menu2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent2 = new Intent(getApplicationContext(),saloon_menu_1.class);
                                  //  intent2.putExtra("saloon_ref",  id1);
                                    startActivity(intent2);
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
    public void onsaloonclick(String s, int position)
    {
        Intent intent1 = new Intent(this, saloon_homepage2.class);
        intent1.putExtra("time",s);
        intent1.putExtra("saloon_ref",saloon_id_ref);
        startActivity(intent1);
    }
}