package com.example.gaurav.javatutorial.programs;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.gaurav.javatutorial.R;
import com.example.gaurav.javatutorial.app.BaseActivity;
import com.example.gaurav.javatutorial.database.ProgramTable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.highlight.ColorTheme;

import static com.example.gaurav.javatutorial.utility.IntentKeys.KEY_PROGRAM_ID;
import static com.example.gaurav.javatutorial.utility.IntentKeys.KEY_PROGRAM_NAME;

public class ProgramActivity extends BaseActivity {
    TextView textViewSyntax;
    String programName, programString;
    int programID;
    ProgramTable programTable;
    @BindView(R.id.code_view)
    CodeView codeView;
    @BindView(R.id.output)
    TextView tvOutput;
    @BindView(R.id.chk_complete)
    CheckBox checkBoxComplete;
    private static final String TAG = "ProgramActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        ButterKnife.bind(this);
        /*textViewSyntax= (TextView) findViewById(R.id.tv_sample);
        String code="public class Hello { <br/>  public static void main(String[] args){<br/>}<br>}";
        PrettifyHighlighter highlighter = new PrettifyHighlighter();
        String highlighted = highlighter.highlight("java", code);
        textViewSyntax.setText(Html.fromHtml(highlighted));*/
        initializeObjects();
        Log.e(TAG, "onCreate: " );

    }

    @Override
    protected void onResume() {
        super.onResume();
        programString = programTable.getProgramString(String.valueOf(programID));
        codeView(codeView);
        tvOutput.setText(programTable.getProgramOutput(String.valueOf(programID)));
        Log.e(TAG, "onResume: " );
    }



    public void codeView(CodeView codeView) {
        //codeView.setCode(getString(R.string.listing_py), "py");

        codeView.setOptions(Options.Default.get(this)
                .withLanguage("java")
                .withCode(programString)
                .withTheme(ColorTheme.MONOKAI));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnCheckedChanged(R.id.chk_complete)
    public void onCheckedComplete() {
        programTable.markProgramComplete(programID, 1);
        Log.d(TAG, "onCheckedComplete: ");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

    public void initializeObjects() {
        context = ProgramActivity.this;
        programName = getIntent().getStringExtra(KEY_PROGRAM_NAME);
        programID = getIntent().getIntExtra(KEY_PROGRAM_ID, 0);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        setTitle(programName);
        programTable = new ProgramTable(context);
        if (programTable.isCurrentProgramCompleted(programID) == 1)
            checkBoxComplete.setChecked(true);
        else
            checkBoxComplete.setChecked(false
            );
        checkBoxComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                      Log.e(TAG, "onCheckedChangedgrv: "+b );
                if (b)
                programTable.markProgramComplete(programID,1);
                else
                    programTable.markProgramComplete(programID,0);

            }
        });

    }
}
