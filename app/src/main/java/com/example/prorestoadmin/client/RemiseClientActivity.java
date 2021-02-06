package com.example.prorestoadmin.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prorestoadmin.R ;
import com.example.prorestoadmin.client.task.EtatRemiseClientExtensibleTask;
import com.example.prorestoadmin.client.task.EtatRemiseClientTask;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

public class RemiseClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remise_client);

        SearchView searchViewClient  = findViewById(R.id.search_bar_client);
        ExpandableListView elv_list_cleint_remise  = findViewById(R.id.elv_list_remise_client);
      //  final RecyclerView rv_list_remise_client =  findViewById(R.id.rv_list_remise_client);
        final ProgressBar progressBar =  findViewById(R.id.progress_bar);
      //  rv_list_remise_client.setHasFixedSize(true);
       // rv_list_remise_client.setLayoutManager( new LinearLayoutManager(this ) );


        EtatRemiseClientExtensibleTask etatRemiseClientTask  = new EtatRemiseClientExtensibleTask(this ,elv_list_cleint_remise ,progressBar ,searchViewClient) ;
        etatRemiseClientTask.execute() ;


    }
}