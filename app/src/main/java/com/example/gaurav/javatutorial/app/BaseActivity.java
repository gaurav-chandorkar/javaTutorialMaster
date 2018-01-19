package com.example.gaurav.javatutorial.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gaurav.javatutorial.R;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {
    @Inject
    public Context context;

    @Inject
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ((App) getApplication()).getComponent().inject(BaseActivity.this);
    }
}
