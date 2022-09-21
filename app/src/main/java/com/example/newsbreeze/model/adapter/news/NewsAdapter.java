package com.example.newsbreeze.model.adapter.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsbreeze.databinding.NewsListItemBinding;
import com.example.newsbreeze.utils.newsList.Articles;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;

    ArrayList<Articles> newsList,savedNewsList;

    OnNewsListener onNewsListener;

    public NewsAdapter(Context context, ArrayList<Articles> newsList,
                       OnNewsListener onNewsListener){
        this.context = context;
        this.newsList = newsList;
        this.onNewsListener = onNewsListener;
        savedNewsList = new ArrayList<>();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new NewsViewHolder(NewsListItemBinding.inflate(layoutInflater
                ,parent,false), onNewsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NewsViewHolder)holder).bindView(newsList.get(position), context, position,savedNewsList);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public interface OnNewsListener{
        void OnNewsClick(int position);
    }

}
