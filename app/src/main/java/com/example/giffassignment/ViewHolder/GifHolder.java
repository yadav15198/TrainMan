package com.example.giffassignment.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giffassignment.R;

import pl.droidsonroids.gif.GifImageView;

public class GifHolder extends RecyclerView.ViewHolder {
     public ImageView image;
     public TextView  gif_name;

    public GifHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.url_image);
        gif_name = itemView.findViewById(R.id.detail_text);

    }
}
