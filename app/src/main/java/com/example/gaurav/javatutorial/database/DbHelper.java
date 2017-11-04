package com.example.gaurav.javatutorial.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gaurav.javatutorial.app.App;

import static com.example.gaurav.javatutorial.database.DbConstants.CREATE_TABLE_PROGRAM;
import static com.example.gaurav.javatutorial.database.DbConstants.DB_VERSION;
import static com.example.gaurav.javatutorial.database.DbConstants.JAVA_TUTORIAL_DB;

/**
 * Created by gaurav on 8/30/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "grv tuto-Helper";

    public DbHelper(Context app) {
        super(app.getApplicationContext(), JAVA_TUTORIAL_DB, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_PROGRAM);
            Log.d(TAG, "onCreate: SQLiteOpenHelper ");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
