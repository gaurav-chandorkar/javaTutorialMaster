package com.example.gaurav.javatutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gaurav.javatutorial.pojo.Program;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.gaurav.javatutorial.database.DbConstants.PROGRAM_ID;
import static com.example.gaurav.javatutorial.database.DbConstants.PROGRAM_NAME;
import static com.example.gaurav.javatutorial.database.DbConstants.PROGRAM_OUTPUT;
import static com.example.gaurav.javatutorial.database.DbConstants.PROGRAM_STATUS;
import static com.example.gaurav.javatutorial.database.DbConstants.PROGRAM_STRING;
import static com.example.gaurav.javatutorial.database.DbConstants.PROGRAM_TABLE;

/**
 * Created by gaurav on 9/15/17.
 */

public class ProgramTable {
    Context context;
    DatabaseComponent databaseComponent;
    @Inject
    DbHelper dbHelper;
    @Inject
    SQLiteDatabase sqLiteDatabase;
    private static final String TAG = "ProgramTable";

    public ProgramTable(Context context) {
        this.context = context;
        databaseComponent = DaggerDatabaseComponent.builder().databaseModule(new DatabaseModule(context)).build();
        databaseComponent.inject(this);
        // sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void insert(List<Program> programList) {

        for (int i = 0; i < programList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(PROGRAM_ID, programList.get(i).getProgramID());
            contentValues.put(PROGRAM_NAME, programList.get(i).getProgramName());
            contentValues.put(PROGRAM_STRING, programList.get(i).getProgramStr());
            contentValues.put(PROGRAM_STATUS, 0);
            contentValues.put(PROGRAM_OUTPUT, programList.get(i).getOutput());
            sqLiteDatabase.insert(PROGRAM_TABLE, null, contentValues);
            Log.e(TAG, "grv inserted: ");
        }
    }

    public List<Program> getProgramList() {
        List<Program> programList = null;
        String[] column = {PROGRAM_ID, PROGRAM_NAME, PROGRAM_STRING, PROGRAM_STATUS};

        Cursor cursor = sqLiteDatabase.query(PROGRAM_TABLE, column, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            programList = new ArrayList<>();
            do {
                Program program = new Program();
                program.setProgramID(cursor.getInt(cursor.getColumnIndex(PROGRAM_ID)));
                program.setProgramName(cursor.getString(cursor.getColumnIndex(PROGRAM_NAME)));
                //   program.setProgramStr(cursor.getString(cursor.getColumnIndex(PROGRAM_STRING)));
                program.setStatus(cursor.getInt(cursor.getColumnIndex(PROGRAM_STATUS)));
                programList.add(program);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return programList;
    }

    public String getProgramString(String programID) {
        String string = null;
        String column[] = {PROGRAM_STRING};
        Cursor cursor =
                sqLiteDatabase.query(PROGRAM_TABLE, column, PROGRAM_ID + "=?", new String[]{programID}, null, null, null);
        if (cursor.moveToFirst()) {
            string = cursor.getString(cursor.getColumnIndex(PROGRAM_STRING));
        }
        cursor.close();
        return string;
    }

    public String getProgramOutput(String programID) {
        Log.e(TAG, "getProgramOutput: " + programID);
        String string = null;
        String column[] = {PROGRAM_OUTPUT};
        Cursor cursor =
                sqLiteDatabase.query(PROGRAM_TABLE, column, PROGRAM_ID + "=?", new String[]{programID}, null, null, null);
        if (cursor.moveToFirst()) {
            string = cursor.getString(cursor.getColumnIndex(PROGRAM_OUTPUT));
        }
        cursor.close();
        return string;
    }

    public int[] getProgramAttempted() {
        String[] column = {PROGRAM_ID, PROGRAM_NAME, PROGRAM_STRING, PROGRAM_STATUS};
        int count[] = new int[]{0, 0};
        Cursor cursor =
                sqLiteDatabase.query(PROGRAM_TABLE, column, PROGRAM_STATUS + "=?", new String[]{"1"}, null, null, null);
        if (cursor.moveToFirst()) {
            count[0] = cursor.getCount();
        }

        cursor =
                sqLiteDatabase.query(PROGRAM_TABLE, column, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            count[1] = cursor.getCount();
        }
        cursor.close();

        return count;

    }

    public void markProgramComplete(int programID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROGRAM_STATUS, 1);

        int update = sqLiteDatabase.update(PROGRAM_TABLE, contentValues, PROGRAM_ID + "=?", new String[]{"" + programID});
        Log.d(TAG, "markProgramComplete: " + update);
    }
}
