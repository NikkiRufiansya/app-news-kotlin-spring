package com.nikki.newsappjava.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nikki.newsappjava.NewsAcitivity;
import com.nikki.newsappjava.R;
import com.nikki.newsappjava.model.NewsModels;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyView> {

    private Context context;
    private List<NewsModels> data;

    public NewsAdapter(Context context, List<NewsModels> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NewsAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyView holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(data.get(position).title);
        holder.desc.setText(data.get(position).description);
        byte[] decodedString = Base64.decode(data.get(position).image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.image.setImageBitmap(decodedByte);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsAcitivity.class);
                intent.putExtra("image",data.get(position).getImage());
                intent.putExtra("content", data.get(position).getContent());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, desc;

        CardView cardView;

        public MyView(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_thumnail);
            title = itemView.findViewById(R.id.tv_title);
            desc = itemView.findViewById(R.id.tv_content);
            cardView = itemView.findViewById(R.id.cv_news);
        }
    }
}
