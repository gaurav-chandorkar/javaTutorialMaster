package com.example.gaurav.javatutorial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gaurav on 9/15/17.
 */
@Module
public class DatabaseModule {

    Context context;
    DbHelper dbHelper;

    public DatabaseModule(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    @Provides
    public DbHelper provideDBHelper() {

        return dbHelper;
    }

    @Provides
    public SQLiteDatabase provideWritableDatabase() {
        return dbHelper.getWritableDatabase();
    }
}
