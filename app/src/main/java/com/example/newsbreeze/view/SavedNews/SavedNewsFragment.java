package com.example.newsbreeze.view.SavedNews;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.newsbreeze.R;
import com.example.newsbreeze.databinding.FragmentSavedNewsBinding;
import com.example.newsbreeze.model.adapter.savedNews.SavedNewsAdapter;
import com.example.newsbreeze.utils.newsList.Articles;
import com.example.newsbreeze.view.CurrentNews.CurrentNewsFragment;
import com.example.newsbreeze.view.Home.HomeFragment;
import com.example.newsbreeze.viewModel.savedNewsList.SavedNewsViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class SavedNewsFragment extends Fragment implements SavedNewsAdapter.OnNewsListener{

    FragmentSavedNewsBinding binding;

    ArrayList<Articles> saveNewsList;

    SavedNewsAdapter adapter;

    SavedNewsViewModel savedNewsViewModel;

    private final String TAG = "Saved Fragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSavedNewsBinding.inflate(getLayoutInflater());

        binding.backBtn.setOnClickListener(view -> replaceFragment(new HomeFragment()));

        savedNewsViewModel = new ViewModelProvider(this).get(SavedNewsViewModel.class);

        if(getSavedNewsFromSharedPreference().size() != 0 ){
            adapter = new SavedNewsAdapter(requireContext(),
                    getSavedNewsFromSharedPreference(),this);
        }else{
            Toast.makeText(requireContext(), "No Saved News", Toast.LENGTH_SHORT).show();
        }


        saveNewsList = new ArrayList<>();

        swipeToRefresh();

        getSavedData();

        setSearchView();
        return binding.getRoot();
    }

    private void setSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (binding.searchView.getQuery().toString().isEmpty()) {
                    adapter = new SavedNewsAdapter(requireContext(),
                            getSavedNewsFromSharedPreference(),
                            SavedNewsFragment.this);
                    if (binding.savedRecyclerView != null) {
                        binding.savedRecyclerView.setLayoutManager
                                (new LinearLayoutManager(requireContext()));
                    }
                }else{
                    ArrayList<Articles> filteredList = new ArrayList<>();
                    for(Articles article: getSavedNewsFromSharedPreference()){
                        if(article.title.toLowerCase()
                                .contains(s.toLowerCase())){
                            filteredList.add(article);
                        }
                    }
                    Log.e(TAG, "onQueryTextChange: "+filteredList.size() );
                    adapter = new SavedNewsAdapter(requireContext(),filteredList
                            , SavedNewsFragment.this);
                }
                if (binding.savedRecyclerView != null) {
                    binding.savedRecyclerView.setAdapter(adapter);
                }

                return false;
            }
        });
    }

    private void getSavedData() {
        Objects.requireNonNull(binding.savedRecyclerView).
                setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.savedRecyclerView.setAdapter(adapter);
        Log.e(TAG,"Fetch Success...");
        if (binding.layoutBlur != null) {
            binding.layoutBlur.setVisibility(View.INVISIBLE);
        }
        if (binding.progressBar != null) {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void swipeToRefresh() {
        if (binding.swipeToRefresh != null) {
            binding.swipeToRefresh.setOnRefreshListener(() ->
                    binding.swipeToRefresh.setRefreshing(false));
        }
    }

    private ArrayList<Articles> getSavedNewsFromSharedPreference() {
        SharedPreferences sharedPreferences = requireActivity().
                getSharedPreferences("shared preferences", MODE_PRIVATE);

        Gson gson = new Gson();

        String json = sharedPreferences.getString("saved", null);

        Type type = new TypeToken<ArrayList<Articles>>() {}.getType();

        saveNewsList = gson.fromJson(json, type);

        Log.e(TAG, "getSavedNewsFromSharedPreference: "+ saveNewsList.size());

        return saveNewsList;
    }

    private void replaceFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .addToBackStack("Saved -> Home")
                .commit();
    }


    @Override
    public void OnNewsClick(int position) {
        saveNewsList = getSavedNewsFromSharedPreference();
        replaceFragment(new CurrentNewsFragment(saveNewsList.get(position)));
    }
}