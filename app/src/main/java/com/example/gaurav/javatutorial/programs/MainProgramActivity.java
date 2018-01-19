package com.example.gaurav.javatutorial.programs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gaurav.javatutorial.R;
import com.example.gaurav.javatutorial.database.ProgramTable;
import com.example.gaurav.javatutorial.pojo.Program;
import com.example.gaurav.javatutorial.utility.RecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.gaurav.javatutorial.utility.IntentKeys.KEY_PROGRAM_ID;
import static com.example.gaurav.javatutorial.utility.IntentKeys.KEY_PROGRAM_NAME;

public class MainProgramActivity extends AppCompatActivity {

    @BindView(R.id.program_recyclerview)
    RecyclerView recyclerViewProgram;

    public static final String TAG = "MainProgramActivity";
    Context context;
    ProgramRecyclerAdapter programRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_program);
        ButterKnife.bind(this);
        context = MainProgramActivity.this;
        recyclerViewProgram.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProgram.setAdapter(programRecyclerAdapter = new ProgramRecyclerAdapter());

        recyclerViewProgram.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "onItemClick: " + position);
                ProgramRecyclerAdapter.ProgramViewHolder holder = (ProgramRecyclerAdapter.ProgramViewHolder) recyclerViewProgram.findViewHolderForAdapterPosition(position);
                Log.e(TAG, "onItemClick: " + holder.btnIndex.getText());
                Program program = programRecyclerAdapter.getItem(position);
                startActivity(new Intent(context, ProgramActivity.class)
                        .putExtra(KEY_PROGRAM_ID, program.getProgramID())
                        .putExtra(KEY_PROGRAM_NAME, program.getProgramName()));

            }
        }));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        setTitle("Programs");
        Log.e(TAG, "onCreate: " );

    }

    @Override
    protected void onStart() {
        super.onStart();
        //recyclerViewProgram.addOnItemTouchListener();
        Log.e(TAG, "onStart: " );

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ProgramRecyclerAdapter extends RecyclerView.Adapter<ProgramRecyclerAdapter.ProgramViewHolder> {
        List<Program> programList;

        public ProgramRecyclerAdapter() {
            programList = new ProgramTable(context).getProgramList();
        }

        @Override
        public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_main_list_item, parent, false);

            return new ProgramViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProgramViewHolder holder, int position) {
            Program program = programList.get(position);
            holder.textViewProName.setText(program.getProgramName());
            holder.btnIndex.setText("" + program.getProgramID());

        }

        public Program getItem(int position) {
            return programList.get(position);
        }

        @Override
        public int getItemCount() {
            return programList.size();
        }

        class ProgramViewHolder extends RecyclerView.ViewHolder {
            TextView textViewProName;
            Button btnIndex;

            public ProgramViewHolder(View itemView) {
                super(itemView);
                textViewProName = itemView.findViewById(R.id.tv_program_name);
                btnIndex = itemView.findViewById(R.id.btn_index);
            }
        }
    }
}
