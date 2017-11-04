package com.example.gaurav.javatutorial.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gaurav.javatutorial.database.DbHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gaurav on 8/30/17.
 */
@Module
public class ApplicationModule {

    App app;

    public ApplicationModule(App app) {
        this.app = app;
    }



    @Provides
    public Context provideContext() {
        return app;
    }

    @Provides
    public SharedPreferences provideSharedPreferences() {

        return app.getSharedPreferences("Tutorial_Pref", Context.MODE_PRIVATE);
    }
}
