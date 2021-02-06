package com.example.prorestoadmin.ui.gratuite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.ui.gratuite.task.MaquetteGratuiteTask;

public class MaquetteGratuiteActivity extends AppCompatActivity {


    ExpandableListView elv_list_maquette_gratuite;
    ProgressBar progressBar;
    SearchView searchViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquette_gratuite);

        searchViewClient = findViewById(R.id.search_bar_client);
        elv_list_maquette_gratuite = findViewById(R.id.elv_list_maquette_gratuite) ;
        progressBar = findViewById(R.id.progress_bar)  ;

        MaquetteGratuiteTask  maquetteGratuiteTask = new MaquetteGratuiteTask(MaquetteGratuiteActivity.this ,3 ,elv_list_maquette_gratuite ,searchViewClient,progressBar);
        maquetteGratuiteTask.execute() ;


    }
}