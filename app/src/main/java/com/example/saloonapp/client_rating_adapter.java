package com.example.saloonapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class client_rating_adapter extends FirestoreRecyclerAdapter<object2,client_rating_adapter.holder> {

    private saloonclick sk;

    public client_rating_adapter(@NonNull FirestoreRecyclerOptions<object2> options,saloonclick sk) {
        super(options);
        this.sk=sk;
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull object2 model) {
        holder.saloon_name.setText(model.getSaloon_name());
        holder.timing.setText(model.getCurrent_time());
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_cardview,parent,false);
        return new client_rating_adapter.holder(v);
    }

    class holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView saloon_name;
        TextView timing;

        public holder(@NonNull View itemView) {
            super(itemView);
            saloon_name=itemView.findViewById(R.id.textView38);
            timing=itemView.findViewById(R.id.textView39);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sk.onsaloonclick(getItem(getAdapterPosition()).getTime(),getAdapterPosition());
        }
    }

    public interface saloonclick{
        void onsaloonclick(String s, int position);
    }


}
