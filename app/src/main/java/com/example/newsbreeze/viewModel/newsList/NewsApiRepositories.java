package com.example.newsbreeze.viewModel.newsList;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.newsbreeze.model.dagger2.component.ApiInterface;
import com.example.newsbreeze.model.dagger2.component.AppComponent;
import com.example.newsbreeze.model.dagger2.component.DaggerAppComponent;
import com.example.newsbreeze.model.dagger2.module.RetrofitModule;
import com.example.newsbreeze.utils.newsList.News;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApiRepositories {
    @Inject
    ApiInterface apiInterface;

    MutableLiveData<News> articlesMutableLiveData = new MutableLiveData<>();

    public void setApiInterface(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .retrofitModule(new RetrofitModule()).build();
        appComponent.inject(NewsApiRepositories.this);
    }

    public MutableLiveData<News> getArticlesMutableLiveData
            (MutableLiveData<Boolean> booleanMutableLiveData){
        Call<News> newsArticleRequestCall = apiInterface
                .getNewsArticles();
        newsArticleRequestCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                articlesMutableLiveData.setValue(response.body());
                assert response.body() != null;
                Log.e("NewsApiRepo", "onResponse: "+response.body().status);
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                Log.e("TES_TLOG_PROFILE ",t.getMessage());
                booleanMutableLiveData.setValue(false);
            }
        });
        return articlesMutableLiveData;
    }
}
