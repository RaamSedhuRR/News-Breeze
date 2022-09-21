package com.example.newsbreeze.view.Home;


import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.newsbreeze.R;
import com.example.newsbreeze.databinding.FragmentHomeBinding;
import com.example.newsbreeze.model.adapter.news.NewsAdapter;
import com.example.newsbreeze.utils.newsList.Articles;
import com.example.newsbreeze.utils.newsList.News;
import com.example.newsbreeze.view.CurrentNews.CurrentNewsFragment;
import com.example.newsbreeze.view.SavedNews.SavedNewsFragment;
import com.example.newsbreeze.viewModel.newsList.NewsViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment implements NewsAdapter.OnNewsListener{
    FragmentHomeBinding binding;

    NewsViewModel newsViewModel;

    NewsAdapter newsAdapter;

    MutableLiveData<News> articlesMutableLiveData;

    ArrayList<Articles> saveNewsList;

    private final String TAG = "Home Fragment";

    private ArrayList<Articles> articles = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        binding.savedItemsBtn.setOnClickListener(view -> replaceFragment(new SavedNewsFragment()));
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        saveNewsList = new ArrayList<>();
        if (binding.swipeToRefresh != null) {
            binding.swipeToRefresh.setOnRefreshListener(() ->
                    binding.swipeToRefresh.setRefreshing(false));
        }
        getAllNews();

        setSearchView();

        return binding.getRoot();
    }

    private void setSavedNews(ArrayList<Articles> saveNewsList) {

        for(int i = 0 ; i < saveNewsList.size(); i++){
            if(i%2 == 0){
                saveNewsList.get(i).isSaved = true;
            }
        }
        storeInSharedPreference(saveNewsList);
    }

    private void storeInSharedPreference(ArrayList<Articles> saveNewsList) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences
                ("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(saveNewsList);
        editor.putString("saved", json);
        editor.apply();
    }


    private void setSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (binding.searchView.getQuery().toString().isEmpty()) {
                    newsAdapter = new NewsAdapter(requireContext(),articles,
                            HomeFragment.this);
                    binding.homeRecyclerView.setLayoutManager
                            (new LinearLayoutManager(requireContext()));
                }else{
                    ArrayList<Articles> filteredList = new ArrayList<>();
                    for(Articles article: articles){
                        if(article.title.toLowerCase()
                                .contains(s.toLowerCase())){
                            filteredList.add(article);
                        }
                    }
                    Objects.requireNonNull(articlesMutableLiveData.getValue())
                            .setArticles(filteredList);
                    newsAdapter = new NewsAdapter(requireContext(),filteredList
                            , HomeFragment.this);
                }
                binding.homeRecyclerView.setAdapter(newsAdapter);
                return false;
            }
        });

        binding.searchView.setOnCloseListener(() -> {
            getAllNews();
            return false;
        });
    }

    private void getAllNews() {
        articlesMutableLiveData = newsCall();
        articlesMutableLiveData.observe(getViewLifecycleOwner(),news -> {
            articles.clear();
            articles = news.articles;
            setSavedNews(articles);
            newsAdapter = new NewsAdapter(requireContext(),articles,this);
            binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.homeRecyclerView.setAdapter(newsAdapter);
            Log.e(TAG,"Fetch Success...");
            if (binding.layoutBlur != null) {
                binding.layoutBlur.setVisibility(View.INVISIBLE);
            }
            if (binding.progressBar != null) {
                binding.progressBar.setVisibility(View.GONE);
            }

        });

    }

    private MutableLiveData<News> newsCall() {
        articlesMutableLiveData = newsViewModel.initialNewsArticleFetch();
        return articlesMutableLiveData;
    }

    private void replaceFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .addToBackStack("Home->Current")
                .commit();
    }

    @Override
    public void OnNewsClick(int position) {
        final Articles article = Objects.requireNonNull(articlesMutableLiveData
                .getValue()).articles.get(position);
        replaceFragment(new CurrentNewsFragment(article));
    }
}