package com.example.saloonapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.widget.Toast.*;

public class object_adapter extends FirestoreRecyclerAdapter<object,object_adapter.objectholder> {

    private saloonclick sk;

    public object_adapter(@NonNull FirestoreRecyclerOptions<object> options,saloonclick sk) {
        super(options);
        this.sk=sk;
    }

    @Override
    protected void onBindViewHolder(@NonNull objectholder holder, int position, @NonNull object model) {

        holder.saloon_name.setText(model.getName());
       // holder.saloon_rating.setText(model.getRating());
        holder.rating.setRating(Float.parseFloat( model.getRating()));
      //  holder.rating.setFocusable(true);
      //  holder.im.setImageBitmap(model.getLink().);
      //  holder.im.setImageResource(model.getLink());
      //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(, model.getLink());
      //  holder.im.setImageBitmap(model.getLink());
    }

    @NonNull
    @Override
    public objectholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new objectholder(v);
    }

    class objectholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView saloon_name;
     //   TextView saloon_rating;
        RatingBar rating;
     //   ImageView im;

        public objectholder(@NonNull View itemView) {
            super(itemView);

            saloon_name=itemView.findViewById(R.id.saloon_name);
         //   saloon_rating=itemView.findViewById(R.id.saloon_rating);
            rating=itemView.findViewById(R.id.ratingBar);
            //im=itemView.findViewById(R.id.imageView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sk.onsaloonclick(getItem(getAdapterPosition()).getId(),getAdapterPosition());
            //getItem(getAdapterPosition()).getId()
        }
    }

    public interface saloonclick{
        void onsaloonclick(String s, int position);
    }

}
