package com.example.newsbreeze.model.adapter.savedNews;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsbreeze.databinding.SavedNewsItemBinding;

public class SavedNewsHolder extends RecyclerView.ViewHolder {

    SavedNewsItemBinding binding;

    public SavedNewsHolder(@NonNull View itemView) {
        super(itemView);
        binding = SavedNewsItemBinding.bind(itemView);
    }

}
