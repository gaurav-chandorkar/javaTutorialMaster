package com.example.gaurav.javatutorial;

import android.content.Intent;
import android.os.Bundle;

import com.example.gaurav.javatutorial.app.BaseActivity;

public class SplashScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
