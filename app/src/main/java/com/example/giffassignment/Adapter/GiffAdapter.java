package com.example.giffassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.giffassignment.Activity.BrowserActivity;
import com.example.giffassignment.R;
import com.example.giffassignment.ViewHolder.GifHolder;
import com.example.giffassignment.model.CustomGlideModule;
import com.example.giffassignment.model.DataClass;
import com.example.giffassignment.model.GifArray;
import com.example.giffassignment.model.GlideApp;
import com.example.giffassignment.model.ImageClass;
import com.example.giffassignment.model.UrlClass;

public class GiffAdapter extends RecyclerView.Adapter<GifHolder> {
    GifArray gifList;
    Context context;

    public GiffAdapter(GifArray gifList, Context context) {
        this.gifList = gifList;
        this.context = context;
    }

//   // public void setGifList(GifArray gifList) {
//        this.gifList = gifList;
//    }


    @NonNull
    @Override
    public GifHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout,parent,false);

        return new GifHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GifHolder holder, int position) {
      DataClass data = gifList.getData().get(position);
      ImageClass image = data.getImages();
      UrlClass url = image.getFixed_height();
      String title = data.getTitle();
     // System.out.println(url.getUrl());
      GlideApp.with(context).asGif().load(url.getUrl()).into(holder.image);
      holder.gif_name.setText(title);


    }

    @Override
    public int getItemCount() {
        return gifList.getData().size();
    }





}
