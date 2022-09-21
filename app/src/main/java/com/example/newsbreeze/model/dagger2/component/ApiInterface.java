package com.example.newsbreeze.model.dagger2.component;

import com.example.newsbreeze.utils.newsList.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("everything?q=apple&from=2022-09-18&to=2022-09-18&sortBy=popularity&apiKey=b05669ef2ff14ebd8c5d7640bc44722c")
    Call<News> getNewsArticles();
}
