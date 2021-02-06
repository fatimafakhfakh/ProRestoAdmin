package com.example.prorestoadmin.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import  com.example.prorestoadmin.R ;
import com.example.prorestoadmin.task.EtatSoldeDetailleClientParRecParClientTask;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailSoldeClientActivity extends AppCompatActivity {


    SearchView searchViewClient  ;
    public static ProgressBar pb;
    public static RecyclerView rv_list_detail_solde_client ;
    public TextView txt_date_debut, txt_date_fin;



    int id_DatePickerDialog = 0;
    Date currentDate = new Date();
    public static int year_x1, month_x1, day_x1;
    public static int year_x2, month_x2, day_x2;

    public static Date date_debut = null;
    public static Date date_fin = null;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_solde_client);


        String  Code_Client     = getIntent().getStringExtra( "cle_code_client") ;
        String  Raison_Client    = getIntent().getStringExtra( "cle_raison_client") ;

        String  Code_Recouv     = getIntent().getStringExtra( "cle_code_recouv") ;
        String  Nom_Recouv     = getIntent().getStringExtra( "cle_Nom_recouv") ;
        String  Date_Debut     = getIntent().getStringExtra( "cle_date_debut") ;
        String  Date_Fin     = getIntent().getStringExtra( "cle_date_fin") ;


        TextView txt_raison_social   =  findViewById(R.id.txt_raison_social) ;
        txt_raison_social.setText(Raison_Client);
        TextView   txt_nom_rec =  findViewById(R.id.txt_nom_rec) ;

        txt_date_debut =  findViewById(R.id.txt_date_debut);
        txt_date_fin =  findViewById(R.id.txt_date_fin);


        txt_date_debut.setText(Date_Debut);
        txt_date_fin.setText(Date_Fin);
        txt_nom_rec.setText(Nom_Recouv);


        searchViewClient =  findViewById(R.id.search_bar_client);

        rv_list_detail_solde_client = (RecyclerView)  findViewById(R.id.rv_detail_solde_client);
        pb = (ProgressBar)  findViewById(R.id.pb);

        rv_list_detail_solde_client.setHasFixedSize(true);
        rv_list_detail_solde_client.setLayoutManager(new LinearLayoutManager(this));


        try {
            date_debut = df.parse(Date_Debut);
            date_fin = df.parse(Date_Fin);


        } catch (Exception e) {
            Log.e("Exception--", " " + e.getMessage());
        }


        EtatSoldeDetailleClientParRecParClientTask  etatSoldeDetailleClientParRecParClientTask  = new  EtatSoldeDetailleClientParRecParClientTask(this  ,Code_Client  ,Code_Recouv  ,date_debut ,date_fin ,rv_list_detail_solde_client ,pb,searchViewClient);
        etatSoldeDetailleClientParRecParClientTask.execute();


    }


}