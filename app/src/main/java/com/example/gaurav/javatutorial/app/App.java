package com.example.gaurav.javatutorial.app;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by gaurav on 8/6/17.
 */

public class App extends Application {
    private ApplicationComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
        component=DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
