package com.example.prorestoadmin.rapportPersonnel;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import  com.example.prorestoadmin.R ;

import com.example.prorestoadmin.rapportPersonnel.ui.main.RapportPagerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RapportActivity extends AppCompatActivity {

    SimpleDateFormat  sdf  = new SimpleDateFormat("dd/MM/yyyy")   ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport);




        TextView  title  = findViewById(R.id.title);

        String  _nom_utilisateur  = getIntent().getStringExtra("cle_nom_utilisateur") ;
        String  _nom_responsable  = getIntent().getStringExtra("cle_nom_responsable") ;
        int     _qt_livraison  = getIntent().getIntExtra("cle_qt_livraison",0) ;


        String  _date_etat      = getIntent().getStringExtra("cle_date_etat") ;
        Date date_etat = new Date() ;
        try {
              date_etat  = sdf.parse(_date_etat) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        title.setText(_nom_responsable);

        RapportPagerAdapter sectionsPagerAdapter = new RapportPagerAdapter(this, getSupportFragmentManager(),_nom_utilisateur,date_etat);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);



    }
}