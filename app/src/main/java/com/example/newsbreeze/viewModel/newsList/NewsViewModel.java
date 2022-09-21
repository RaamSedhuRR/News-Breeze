package com.example.newsbreeze.viewModel.newsList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsbreeze.model.dagger2.component.AppComponent;
import com.example.newsbreeze.model.dagger2.component.DaggerAppComponent;
import com.example.newsbreeze.model.dagger2.module.RetrofitModule;
import com.example.newsbreeze.utils.newsList.News;

public class NewsViewModel extends ViewModel {
    NewsApiRepositories newsApiRepositories;
    public MutableLiveData<Boolean> booleanMutableLiveData =
            new MutableLiveData<>(true);

    public NewsViewModel(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .retrofitModule(new RetrofitModule()).build();
        appComponent.inject(NewsViewModel.this);
        newsApiRepositories = new NewsApiRepositories();
        newsApiRepositories.setApiInterface();
    }
    public MutableLiveData<News> initialNewsArticleFetch(){
        return newsApiRepositories.getArticlesMutableLiveData(booleanMutableLiveData);
    }

}
