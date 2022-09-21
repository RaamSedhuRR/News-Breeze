package com.example.newsbreeze.view.CurrentNews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.newsbreeze.R;
import com.example.newsbreeze.databinding.FragmentCurrentNewsBinding;
import com.example.newsbreeze.utils.newsList.Articles;
import com.example.newsbreeze.view.Home.HomeFragment;


public class CurrentNewsFragment extends Fragment {

    FragmentCurrentNewsBinding binding;

    Articles articles;

    public CurrentNewsFragment(Articles articles) {
        this.articles = articles;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrentNewsBinding.inflate(getLayoutInflater());
        setData();
        binding.backBtn.setOnClickListener(view -> replaceFragment(new HomeFragment()));
        return binding.getRoot();
    }



    private void replaceFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .addToBackStack("Current -> HomeOrSave")
                .commit();

    }

    private void setData() {
                    binding.authorName.setText(articles.author);
                    binding.authorRole.setText(articles.source.name);
                    binding.currentNewsDate.setText(getDate(articles.publishedAt));
                    binding.newsDescription.setText(articles.content);
                    binding.currentNewsTitleText.setText(articles.title);
                    Glide.with(requireContext())
                            .load(articles.url)
                            .into(binding.authorImage);
                    Glide.with(requireContext())
                            .load(articles.urlToImage)
                            .into(binding.currentNewsImage);
    }

    private StringBuilder getDate(String date) {
        StringBuilder dateText = new StringBuilder();
        for(int i =0; i<10 ;i++){
            dateText.append(date.charAt(i));
        }
        return dateText;
    }
}