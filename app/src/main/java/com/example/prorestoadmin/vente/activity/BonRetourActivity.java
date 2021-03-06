package com.example.prorestoadmin.vente.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import  com.example.prorestoadmin.R ;
import com.example.prorestoadmin.vente.task.EtatBonLivraisonTask;
import com.example.prorestoadmin.vente.task.EtatBonRetourTask;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BonRetourActivity extends AppCompatActivity {


    public static ProgressBar pb;
    public static RecyclerView rv_list_bon_retour;
    SearchView search_bar_client ;
    public static String CodeRepresentant, CodeDepot;

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
        setContentView(R.layout.activity_bon_retour);

        txt_date_debut = findViewById(R.id.txt_date_debut);
        txt_date_fin = findViewById(R.id.txt_date_fin);

        rv_list_bon_retour   = (RecyclerView) findViewById(R.id.rv_list_bon_transfert);
        pb = (ProgressBar) findViewById(R.id.pb);
        search_bar_client = (SearchView) findViewById(R.id.search_bar_client);


        rv_list_bon_retour.setHasFixedSize(true);
        rv_list_bon_retour.setLayoutManager(new LinearLayoutManager(this));


        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(currentDate);
        //cal1.add(Calendar.DAY_OF_YEAR, -7);
        year_x1  = cal1.get(Calendar.YEAR);
        month_x1 = cal1.get(Calendar.MONTH);
        day_x1   = cal1.get(Calendar.DAY_OF_MONTH);


        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(currentDate);
        //  cal2.add(Calendar.DAY_OF_YEAR, +7);
        year_x2 = cal2.get(Calendar.YEAR);
        month_x2 = cal2.get(Calendar.MONTH);
        day_x2 = cal2.get(Calendar.DAY_OF_MONTH);

        date_debut = cal1.getTime();
        String _date_du = df.format(cal1.getTime());
        txt_date_debut.setText(_date_du);

        date_fin = cal2.getTime();
        String _date_au = df.format(cal2.getTime());
        txt_date_fin.setText(_date_au);


        EtatBonRetourTask etatBonRetourTask = new EtatBonRetourTask(BonRetourActivity.this , date_debut , date_fin , rv_list_bon_retour  ,pb,search_bar_client) ;
        etatBonRetourTask.execute() ;


        txt_date_debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_DatePickerDialog = 0;
                Log.e("month_x1", "On picker  : " + month_x1);
                DatePickerDialog datePickerDialog = new DatePickerDialog(BonRetourActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (id_DatePickerDialog == 0) {
                            year_x1 = year;
                            month_x1 = monthOfYear;
                            day_x1 = dayOfMonth;

                            txt_date_debut.setText("" + formatter.format(day_x1) + "/" + formatter.format(month_x1 + 1) + "/" + year_x1);

                            String _date_du = formatter.format(day_x1) + "/" + formatter.format(month_x1 + 1) + "/" + year_x1 + " ";
                            String _date_au = txt_date_fin.getText().toString();


                            try {
                                date_debut = df.parse(_date_du);
                                date_fin = df.parse(_date_au);

                                EtatBonRetourTask etatBonRetourTask = new EtatBonRetourTask(BonRetourActivity.this , date_debut , date_fin , rv_list_bon_retour  ,pb,search_bar_client) ;
                                etatBonRetourTask.execute() ;


                            } catch (Exception e) {
                                Log.e("Exception--", " " + e.getMessage());
                            }
                        }
                    }
                }, year_x1, month_x1, day_x1);
                datePickerDialog.show();
            }
        });

        txt_date_fin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                id_DatePickerDialog = 1;

                DatePickerDialog datePickerDialog = new DatePickerDialog(BonRetourActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (id_DatePickerDialog == 1) {

                            year_x2 = year;
                            month_x2 = monthOfYear;
                            day_x2 = dayOfMonth;

                            txt_date_fin.setText("" + formatter.format(day_x2) + "/" + formatter.format(month_x2 + 1) + "/" + year_x2);

                            String _date_au = "" + formatter.format(day_x2) + "/" + formatter.format(month_x2 + 1) + "/" + year_x2;
                            String _date_du = txt_date_debut.getText().toString();

                            try {
                                date_debut = df.parse(_date_du);
                                date_fin = df.parse(_date_au);

                                EtatBonRetourTask etatBonRetourTask = new EtatBonRetourTask(BonRetourActivity.this , date_debut , date_fin , rv_list_bon_retour  ,pb , search_bar_client) ;
                                etatBonRetourTask.execute() ;

                            } catch (Exception e) {
                                Log.e("Exception --", " " + e.getMessage());
                            }

                        }
                    }
                }, year_x2, month_x2, day_x2);
                datePickerDialog.show();
            }
        });






    }
}