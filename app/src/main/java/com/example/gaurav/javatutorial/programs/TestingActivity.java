package com.example.gaurav.javatutorial.programs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gaurav.javatutorial.R;

import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.highlight.ColorTheme;
import io.github.kbiakov.codeview.highlight.ColorThemeData;
import io.github.kbiakov.codeview.highlight.SyntaxColors;

public class TestingActivity extends AppCompatActivity {
CodeView codeView;
    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        codeView= (CodeView) findViewById(R.id.code_view1);
        output= (TextView) findViewById(R.id.output);

       /* codeView.getOptions().setTheme(ColorTheme.MONOKAI);
        ColorThemeData myTheme = ColorTheme.SOLARIZED_LIGHT.theme()
                .withBgContent(android.R.color.black)
                .withNoteColor(android.R.color.white);*/

        String ss="public class Variables { \n" +
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
                "}";



        codeView.setOptions(Options.Default.get(this)
                .withLanguage("java")
                .withCode(ss)
                .withTheme(ColorTheme.MONOKAI));

        ss="C:\\Java\\Variables>javac Variables.java \n" +
                "C:\\Java\\Variables>java Variables\n" +
                "There are 17.702784 kilometers in 11 miles.\n"+
        "Short value=10 Boolean value=true Char value=a Byte value=10";
        output.setText(ss);
        /*output.setOptions(Options.Default.get(this)
                .withLanguage("java")
                .withCode(ss)

                .withTheme(ColorTheme.MONOKAI));*/

    }
}
