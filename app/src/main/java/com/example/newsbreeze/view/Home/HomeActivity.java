package com.example.newsbreeze.view.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.newsbreeze.databinding.ActivityHomeBinding;
import com.example.newsbreeze.view.SplashScreen.SplashFragment;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new SplashFragment());
    }

    private void replaceFragment(Fragment fragment) {
        assert binding.frameLayout != null;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.frameLayout.getId(),fragment)
                .commit();
    }
}