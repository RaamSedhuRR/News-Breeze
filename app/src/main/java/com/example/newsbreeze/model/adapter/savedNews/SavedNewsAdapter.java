package com.example.newsbreeze.model.adapter.savedNews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsbreeze.databinding.SavedNewsItemBinding;
import com.example.newsbreeze.utils.newsList.Articles;

import java.util.ArrayList;

public class SavedNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;

    ArrayList<Articles> savedNewsList;

    OnNewsListener onNewsListener;

    public SavedNewsAdapter(Context context, ArrayList<Articles> savedNewsList,
                            SavedNewsAdapter.OnNewsListener onNewsListener){
        this.context = context;
        this.savedNewsList = savedNewsList;
        this.onNewsListener = onNewsListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new SavedNewsHolder(SavedNewsItemBinding.inflate(layoutInflater).getRoot());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        SavedNewsHolder newsViewHolder = (SavedNewsHolder) holder;


        StringBuilder dateText = new StringBuilder();
        for(int i =0; i<10 ;i++){
            dateText.append(savedNewsList.get(position).publishedAt.charAt(i));
        }
        Glide.with(context)
                .load(savedNewsList.get(position).urlToImage)
                .into(newsViewHolder.binding.newsMiniImage);
        newsViewHolder.binding.newsTitle.setText(savedNewsList.get(position).title);
        newsViewHolder.binding.newsHashtag.setText("#"+savedNewsList.get(position).source.name);
        newsViewHolder.binding.authorName.setText(savedNewsList.get(position).author);
        newsViewHolder.binding.newsDate.setText(dateText.toString());
        newsViewHolder.binding.mainLayout.setOnClickListener(view ->
                onNewsListener.OnNewsClick(position));
    }

    @Override
    public int getItemCount() {
        return savedNewsList.size();
    }

    public interface OnNewsListener{
        void OnNewsClick(int position);
    }
}
