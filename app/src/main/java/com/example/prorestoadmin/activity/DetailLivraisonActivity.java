package com.example.prorestoadmin.activity;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;

import com.example.prorestoadmin.R;

import java.util.Date;

public class DetailLivraisonActivity extends AppCompatActivity {

    RecyclerView  rv_list_livraison  ;
    ProgressBar  progressBar  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_livraison);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        rv_list_livraison = (RecyclerView) findViewById(R.id.rv_list_bl) ;
        progressBar  = (ProgressBar) findViewById(R.id.progress_bar) ;

        String  _nom_utilisateur  = getIntent().getStringExtra("cle_nom_utilisateur") ;
        String  _nom_responsable  = getIntent().getStringExtra("cle_nom_responsable") ;
        int     _qt_livraison  = getIntent().getIntExtra("cle_qt_livraison",0) ;

        rv_list_livraison.setHasFixedSize(true);
        rv_list_livraison.setLayoutManager( new LinearLayoutManager( this ) );
        toolBarLayout.setTitle("Livraison de \n"+_nom_responsable);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


       /* LivraisonDetailTask livraisonDetailTask  = new LivraisonDetailTask(this  ,_nom_utilisateur ,new Date() , new Date() , rv_list_livraison, progressBar  ) ;
        livraisonDetailTask.execute() ;*/


    }
}