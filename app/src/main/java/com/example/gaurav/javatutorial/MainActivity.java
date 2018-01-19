package com.example.gaurav.javatutorial;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gaurav.javatutorial.app.BaseActivity;
import com.example.gaurav.javatutorial.asynctask.ProgramIntoDBAsync;
import com.example.gaurav.javatutorial.database.ProgramTable;
import com.example.gaurav.javatutorial.programs.MainProgramActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.gaurav.javatutorial.utility.IntentKeys.KEY_FIRST_LAUNCH;
import static com.example.gaurav.javatutorial.utility.IntentKeys.KEY_FIRST_TIME_PROGRAM_CLICK;

public class MainActivity extends BaseActivity {

    private static final String TAG = "grv MainActivity";
    @BindView(R.id.youtube_card)
    CardView cardViewYoutube;

    @BindView(R.id.ll_program_main)
    RelativeLayout llProgramMain;

    @BindView(R.id.tv_getstarted)
    TextView tvGetStarted;

    @BindView(R.id.ll_program_detail)
    LinearLayout layoutProgramDetail;

    @BindView(R.id.tv_program_total)
    TextView tvTotal;

    @BindView(R.id.tv_program_attempted)
    TextView tvAttempted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("DashBoard");
        Log.e(TAG, "onCreate: " );
        cardViewYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(getApplicationContext(), ProgramActivity.class));
                String videoId = "4k3jbsEO3xU";
                watchYoutubeVideo(videoId);
            }
        });

        storeProgram();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setProgramDashboard();
        Log.e(TAG, "onStart: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    private void setProgramDashboard() {
        ProgramTable table = new ProgramTable(MainActivity.this);
        int count[] = table.getProgramAttempted();
        if (!sharedPreferences.getBoolean(KEY_FIRST_TIME_PROGRAM_CLICK, false)) {
            sharedPreferences.edit().putBoolean(KEY_FIRST_TIME_PROGRAM_CLICK, true).apply();
            tvGetStarted.setVisibility(View.VISIBLE);
            layoutProgramDetail.setVisibility(View.GONE);
        } else {
            tvGetStarted.setVisibility(View.GONE);
            layoutProgramDetail.setVisibility(View.VISIBLE);
            tvAttempted.setText(getResources().getString(R.string.have_attempted) +
                    count[0] + getResources().getString(R.string.str_program));
            tvTotal.setText(getResources().getString(R.string.str_total) +
                    count[1] + getString(R.string.str_program));
        }


    }

    @OnClick(R.id.ll_program_main)
    void callProgram() {

        startActivity(new Intent(getApplicationContext(), MainProgramActivity.class));

    }

    public void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));

        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            startActivity(webIntent);
        }
    }

    public void storeProgram() {
        boolean isFirstLaunch = sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true);
        if (isFirstLaunch) {

            new ProgramIntoDBAsync(MainActivity.this, new ProgramIntoDBAsync.OnTaskComplete() {
                @Override
                public void taskComplete(int a) {
                    Log.d(TAG, "taskComplete: ");
                    sharedPreferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply();
                }
            }).execute();

        } else {
            Log.e(TAG, "onCreate: not first launch");
        }
    }

}
