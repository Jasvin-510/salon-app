package com.example.saloonapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class client_history_adapter extends FirestoreRecyclerAdapter<object2,client_history_adapter.history_holder> {

    private FirebaseFirestore dp=FirebaseFirestore.getInstance();

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public client_history_adapter(@NonNull FirestoreRecyclerOptions<object2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull history_holder holder, int position, @NonNull object2 model) {

        holder.tv35.setVisibility(View.GONE);
        holder.name.setText(model.getSaloon_name());
        holder.add.setText(model.getCurrent_time());
        holder.status.setText(model.getStatus());
        if(model.getStatus().equals("cancelled"))
        {
            holder.rating1.setVisibility(View.GONE);
            holder.rating2.setVisibility(View.GONE);
            holder.ratingstar.setVisibility(View.GONE);
        }
        else
        {
            if(model.getRating().equals("no_rating")) {
                holder.ratingstar.setVisibility(View.GONE);
                holder.rating1.setVisibility(View.VISIBLE);
                holder.rating2.setVisibility(View.VISIBLE);
                holder.rating2.setText(model.getRating());
            }
            else
            {
                holder.ratingstar.setVisibility(View.VISIBLE);
                holder.rating1.setVisibility(View.VISIBLE);
                holder.rating2.setVisibility(View.GONE);
                holder.ratingstar.setRating(Float.parseFloat(model.getRating()));
            }
        }

        String s11,s12;
        s11="a";
        s12="a";
        if(model.getMen_hair_cutting().equals("yes")) {s11="b";holder.m1.setVisibility(View.VISIBLE);}
        else { holder.m1.setVisibility(View.GONE);}

        if(model.getMen_hair_colour().equals("yes")) {s11="b"; holder.m2.setVisibility(View.VISIBLE);}
        else { holder.m2.setVisibility(View.GONE); }

        if(model.getMen_hair_styling().equals("yes")) {s11="b";holder.m3.setVisibility(View.VISIBLE); }
        else { holder.m3.setVisibility(View.GONE); }

        if(model.getMen_facial_and_skin_care().equals("yes")) {s11="b"; holder.m4.setVisibility(View.VISIBLE); }
        else { holder.m4.setVisibility(View.GONE); }

        if(model.getMen_massages().equals("yes")) {s11="b";holder.m5.setVisibility(View.VISIBLE);}
        else { holder.m5.setVisibility(View.GONE); }

        if(s11.equals("a"))
        {
            holder.cmen.setVisibility(View.GONE);
        }
        else{
            holder.cmen.setVisibility(View.VISIBLE);
        }


        if(model.getWomen_eye_brow().equals("yes")) {s12="b";holder.g1.setVisibility(View.VISIBLE);}
        else { holder.g1.setVisibility(View.GONE); }

        if(model.getWomen_nail_service().equals("yes")) {s12="b";holder.g2.setVisibility(View.VISIBLE); }
        else { holder.g2.setVisibility(View.GONE); }

        if(model.getWomen_facial().equals("yes")) {s12="b";holder.g3.setVisibility(View.VISIBLE);}
        else { holder.g3.setVisibility(View.GONE); }

        if(model.getWomen_custom_makeup().equals("yes")) {s12="b";holder.g4.setVisibility(View.VISIBLE);}
        else { holder.g4.setVisibility(View.GONE); }

        if(model.getWomen_hair_cutting().equals("yes")) {s12="b";holder.g5.setVisibility(View.VISIBLE);}
        else { holder.g5.setVisibility(View.GONE); }

        if(s12.equals("a"))
        {
            holder.cwomen.setVisibility(View.GONE);
        }
        else
        {
            holder.cwomen.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    @Override
    public history_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.client_history_cardview,parent,false);
        return new history_holder(v);
    }

    class history_holder extends RecyclerView.ViewHolder{

        TextView name,con,cmen,cwomen,add,m1,m2,m3,m4,m5,g1,g2,g3,g4,g5,status,rating1,rating2,tv35;
        RatingBar  ratingstar;

        public history_holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView12);
          //  con=itemView.findViewById(R.id.textView17);
            add=itemView.findViewById(R.id.textView17);
            cmen=itemView.findViewById(R.id.textView18);
            cwomen=itemView.findViewById(R.id.textView24);
            m1=itemView.findViewById(R.id.textView19);
            m2=itemView.findViewById(R.id.textView20);
            m3=itemView.findViewById(R.id.textView21);
            m4=itemView.findViewById(R.id.textView22);
            m5=itemView.findViewById(R.id.textView23);
            g1=itemView.findViewById(R.id.textView25);
            g2=itemView.findViewById(R.id.textView26);
            g3=itemView.findViewById(R.id.textView27);
            g4=itemView.findViewById(R.id.textView28);
            g5=itemView.findViewById(R.id.textView29);
            status=itemView.findViewById(R.id.textView32);
            rating1=itemView.findViewById(R.id.textView33);
            rating2=itemView.findViewById(R.id.textView34);
            tv35=itemView.findViewById(R.id.textView35);
            ratingstar=itemView.findViewById(R.id.ratingBar3);
        }
    }
}
