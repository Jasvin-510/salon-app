package com.example.saloonapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class saloon_request_adapter extends FirestoreRecyclerAdapter<object2,saloon_request_adapter.saloon_request_objectholder> {

    private saloon_request_adapter.saloonclick sk;

    public saloon_request_adapter(@NonNull FirestoreRecyclerOptions<object2> options, saloon_request_adapter.saloonclick sk) {
        super(options);
        this.sk=sk;
    }

    @Override
    protected void onBindViewHolder(@NonNull saloon_request_objectholder holder, int position, @NonNull object2 model) {
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
    }

    @NonNull
    @Override
    public saloon_request_objectholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.saloon_request_cardview,parent,false);
        return new saloon_request_objectholder(v);
    }



    class saloon_request_objectholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,address;

        public saloon_request_objectholder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.textView3);
            address=itemView.findViewById(R.id.textView5);
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
