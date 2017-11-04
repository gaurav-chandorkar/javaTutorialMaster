package com.example.gaurav.javatutorial.asynctask;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;

import com.example.gaurav.javatutorial.R;
import com.example.gaurav.javatutorial.app.App;
import com.example.gaurav.javatutorial.app.BaseActivity;
import com.example.gaurav.javatutorial.database.ProgramTable;
import com.example.gaurav.javatutorial.pojo.Program;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav on 8/30/17.
 */

public class ProgramIntoDBAsync extends AsyncTask<String, Integer, Integer> {

    Context context;
    AlertDialog b;
    AlertDialog.Builder dialogBuilder;
    OnTaskComplete onTaskComplete;

    public ProgramIntoDBAsync(Context context, OnTaskComplete onTaskComplete) {
        this.context = context;
        this.onTaskComplete = onTaskComplete;
        ShowProgressDialog();

    }

    public interface OnTaskComplete {

        void taskComplete(int a);
    }


    public void ShowProgressDialog() {
        dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.progress_dialog_layout, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        b = dialogBuilder.create();
        b.show();
    }

    public void HideProgressDialog() {

        b.dismiss();
    }

    @Override
    protected Integer doInBackground(String... strings) {
        List<Program> programList = new ArrayList<>();

        Program p = new Program(1, "Hello World", "public class Hello { \n\n" +
                "       \t public static void main(String args[]) {\n" +
                "         \t \t System.out.println(\"Hello World \")\n" +
                "        \t \n}\n" +
                "        }",0,"C:\\Java\\Variables javac Hello.java \n" +
                "C:\\Java\\Hello java Hello\n" +
                "Hello World");

        Program program = new Program(2, "Variables & Primitive Data Types", "public class Variables { \n" +
                "       \t public static void main(String args[]) {\n" +
                "         \t \t int miles = 0;\t // (data type int) (variable miles) \n" +
                "         \t \t double kilometers = 0.0d; \t// (data type double) (variable kilometers)  \n" +
                "         \t \t double distance; \t// (data type double) (variable distance) default value is 0.0d;\t// \n" +
                "         \t \t  miles = 11; // change the value of the miles variable \n" +
                "        \t \tkilometers = 1.609344d; // change the value of the kilometers variable\n" +
                "        \t \tdistance = miles * kilometers; // multiply variable values using the multiplication operator (*) and store the value to the distance variable\n" +
                "\n" +
                "        \t \tSystem.out.println(\"There are \" + distance + \" kilometers in \" + miles +\" miles.\" );\n\t// " +
                "         \t \t float flt=0.0f;\t//(data type float) (variable flt) default value is 0.0f;\n" +
                "        \t\t short s=10;  //(data type short) (variable s)(initialize to value 10\n"+
                "       \t\t boolean flag=true; //(boolean value are set either true or false)\n"+
                "       \t\t char c='a';  //(data type char) (variable c)\n"+
                "       \t\t byte b=10;  // (data type byte) (variable b)\n"+
                "       \t\t System.out.println(\" Short value =\"+s+\" Boolean value=\"+flag+\n"+
                "       \t\t\" Char value=\"+c+ \" Byte value=\"+b)\n"+

                "        \t}\n" +
                "}",0,"C:\\Java\\Variables>javac Variables.java \n"
                +"C:\\Java\\Variables>java Variables\n" +
                " \"There are 17.702784 kilometers in 11 miles.\n" +
                " \"Short value=10 Boolean value=true Char value=a Byte value=10");
        programList.add(p);
        programList.add(program);



        new ProgramTable(context).insert(programList);
        return 1;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        HideProgressDialog();
        onTaskComplete.taskComplete(1);
    }
}
