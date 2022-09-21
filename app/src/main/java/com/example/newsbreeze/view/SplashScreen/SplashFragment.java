package com.example.newsbreeze.view.SplashScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsbreeze.R;
import com.example.newsbreeze.databinding.FragmentSplashBinding;
import com.example.newsbreeze.view.Home.HomeFragment;
import com.example.newsbreeze.viewModel.splashScreen.SplashScreenViewModel;

public class SplashFragment extends Fragment {

    FragmentSplashBinding binding;

    SplashScreenViewModel splashScreenViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater);
        splashScreenViewModel = new ViewModelProvider(this)
                .get(SplashScreenViewModel.class);
        initiateSplashTimer();
        return binding.getRoot();
    }

    private void initiateSplashTimer() {
        splashScreenViewModel.initiateSplashTimer();
        splashScreenViewModel.booleanMutableLiveData.observe(requireActivity(),aBoolean -> {
            if(aBoolean){
                    navigateToHomeScreen();
            }
        });
    }

    private void navigateToHomeScreen() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout,new HomeFragment()).commit();

    }
}