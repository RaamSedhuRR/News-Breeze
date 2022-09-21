package com.example.newsbreeze.model.adapter.news;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsbreeze.R;
import com.example.newsbreeze.databinding.NewsListItemBinding;
import com.example.newsbreeze.utils.newsList.Articles;
import com.google.gson.Gson;

import java.util.ArrayList;

public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    NewsListItemBinding binding;

    NewsAdapter.OnNewsListener onNewsListener;

    int position;

    Context context;

    public NewsViewHolder(@NonNull NewsListItemBinding binding,
                          NewsAdapter.OnNewsListener onNewsListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.onNewsListener = onNewsListener;
    }
    @SuppressLint("SetTextI18n")
    public void bindView(Articles articles, Context context,
                         int position, ArrayList<Articles> savedNewsList){

        StringBuilder dateText = new StringBuilder();
        for(int i =0; i<10 ;i++){
            dateText.append(articles.publishedAt.charAt(i));
        }
        this.position = position;
        this.context = context;
        Glide.with(context).load(articles.urlToImage).into(binding.newsImage);
        binding.newsTitle.setText(articles.title);
        binding.newsDescription.setText(articles.description);
        binding.newsDate.setText(dateText.toString());
        binding.getRoot().setOnClickListener(this);
        binding.saveBtn.setOnClickListener(view -> {
            binding.savedTopBtn.setBackgroundColor(ContextCompat
                    .getColor(context, R.color.app_green));
            binding.savedItemsBtn.setBackgroundColor(ContextCompat
                    .getColor(context, R.color.app_green));
            savedNewsList.add(articles);
            binding.saveBtn.setText("Saved");
        });
        storeInSharedPreference(savedNewsList);
    }

    @Override
    public void onClick(View view) {
        onNewsListener.OnNewsClick(position);
    }

    private void storeInSharedPreference(ArrayList<Articles> saveNewsList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences
                ("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(saveNewsList);
        editor.putString("saved", json);
        editor.apply();
    }

}
