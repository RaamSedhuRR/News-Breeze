package com.example.newsbreeze.model.dagger2.component;

import com.example.newsbreeze.model.dagger2.module.RetrofitModule;
import com.example.newsbreeze.viewModel.newsList.NewsApiRepositories;
import com.example.newsbreeze.viewModel.newsList.NewsViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {RetrofitModule.class})
public interface AppComponent {
    void inject(NewsViewModel newsViewModel);
    void inject(NewsApiRepositories newsApiRepositories);
}
