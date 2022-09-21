package com.example.newsbreeze.viewModel.savedNewsList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsbreeze.utils.newsList.Articles;

import java.util.ArrayList;

public class SavedNewsViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Articles>> savedNewsMutableLiveData;

    public void setSavedNewsMutableLiveData(MutableLiveData<ArrayList<Articles>>
                                                    savedNewsMutableLiveData){
        this.savedNewsMutableLiveData = savedNewsMutableLiveData;
    }

}
