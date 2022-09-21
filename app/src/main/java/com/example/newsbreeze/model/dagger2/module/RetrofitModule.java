package com.example.newsbreeze.model.dagger2.module;

import com.example.newsbreeze.BuildConfig;
import com.example.newsbreeze.model.dagger2.component.ApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class RetrofitModule {
    private static Retrofit retrofit = null;

    @Singleton
    @Provides
    public static ApiInterface getApiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

    @Singleton
    @Provides
    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
