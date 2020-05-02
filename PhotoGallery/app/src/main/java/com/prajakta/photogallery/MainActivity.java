package com.prajakta.photogallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentImages mFragmentImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentImages = (FragmentImages) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentImages);


    }
}
