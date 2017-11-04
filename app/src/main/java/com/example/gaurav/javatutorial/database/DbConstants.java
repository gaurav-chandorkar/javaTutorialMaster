package com.example.gaurav.javatutorial.database;

/**
 * Created by gaurav on 8/30/17.
 */

public interface DbConstants {

    String JAVA_TUTORIAL_DB = "tutorial";
    int DB_VERSION = 1;

    String PROGRAM_TABLE = "PROGRAM";
    String PROGRAM_ID = "PROGRAM_ID";
    String PROGRAM_NAME = "PROGRAM_NAME";
    String PROGRAM_STRING = "PROGRAM_STRING";
    String PROGRAM_STATUS="PROGRAM_STATUS";
    String PROGRAM_OUTPUT="PROGRAM_OUTPUT";

    String CREATE_TABLE_PROGRAM =
            "CREATE TABLE  IF NOT EXISTS " + PROGRAM_TABLE +
                    " (" + PROGRAM_ID + " INTEGER PRIMARY KEY , " +
                     PROGRAM_NAME+" TEXT, " +
                    PROGRAM_STRING +" TEXT," +
                    PROGRAM_STATUS +" INTEGER," +
                    PROGRAM_OUTPUT+ " TEXT)";

}
