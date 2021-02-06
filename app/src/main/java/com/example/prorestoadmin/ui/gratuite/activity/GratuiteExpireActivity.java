package com.example.prorestoadmin.ui.gratuite.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.ui.gratuite.task.ListGratuiteExpiredTask;

import java.util.Date;


public class GratuiteExpireActivity extends AppCompatActivity {


    ExpandableListView elv_list_gratuite_expired;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratuite_expire);

        elv_list_gratuite_expired = findViewById(R.id.elv_list_gratuite_expire);
        progressBar = findViewById(R.id.progress_bar);

        ListGratuiteExpiredTask  listGratuiteExpiredTask = new ListGratuiteExpiredTask(GratuiteExpireActivity.this,new Date() ,0 ,elv_list_gratuite_expired , progressBar) ;
        listGratuiteExpiredTask.execute() ;

    }


}