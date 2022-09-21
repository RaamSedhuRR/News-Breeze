package com.example.newsbreeze.viewModel.splashScreen;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SplashScreenViewModel extends ViewModel {
    public MutableLiveData<Boolean> booleanMutableLiveData =
            new MutableLiveData<>(false);

    public void initiateSplashTimer() {
        Handler handler = new Handler();
        handler.postDelayed(() ->
                        booleanMutableLiveData.postValue(true),
                1000
        );
    }
}
