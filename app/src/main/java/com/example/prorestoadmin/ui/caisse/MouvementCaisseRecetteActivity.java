package com.example.prorestoadmin.ui.caisse;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.task.MouvementCaisseRecetteDetailTask;
import com.example.prorestoadmin.task.MouvementCaisseRecetteGlobalTask;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class MouvementCaisseRecetteActivity extends AppCompatActivity {

    RadioButton rb_global, rb_detail;
    public TextView txt_date_debut, txt_date_fin;
    ListView lv_mvmnt_caisse_recette;
    ProgressBar pb;

    int id_DatePickerDialog = 0;
    Date currentDate = new Date();
    public static int year_x1, month_x1, day_x1;
    public static int year_x2, month_x2, day_x2;

    public static Date date_debut = null;
    public static Date date_fin = null;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("00");

   public  static int global = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouvement_caisse_recette);

        rb_global = (RadioButton) findViewById(R.id.rb_global);
        rb_detail = (RadioButton) findViewById(R.id.rb_detail);
        txt_date_debut = findViewById(R.id.txt_date_debut);
        txt_date_fin = findViewById(R.id.txt_date_fin);

        lv_mvmnt_caisse_recette = (ListView)   findViewById(R.id.lv_mvmnt_caisse_recette);
        pb = (ProgressBar)  findViewById(R.id.progress_bar)  ;


        final String code_compte = getIntent().getStringExtra("cle_code_compte");
        String libelle_compte = getIntent().getStringExtra("cle_libelle_compte");

        setTitle(libelle_compte);


        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(currentDate);
        //cal1.add(Calendar.DAY_OF_YEAR, -7);
        year_x1 = cal1.get(Calendar.YEAR);
        month_x1 = cal1.get(Calendar.MONTH);
        day_x1 = cal1.get(Calendar.DAY_OF_MONTH);


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



        MouvementCaisseRecetteGlobalTask mouvementCaisseRecetteGlobalTask = new MouvementCaisseRecetteGlobalTask(MouvementCaisseRecetteActivity.this, lv_mvmnt_caisse_recette , pb ,code_compte , date_debut , date_fin) ;
        mouvementCaisseRecetteGlobalTask.execute() ;

        txt_date_debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_DatePickerDialog = 0;
                Log.e("month_x1", "On picker  : " + month_x1);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MouvementCaisseRecetteActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                                if(global ==1)
                                {
                                    MouvementCaisseRecetteGlobalTask mouvementCaisseRecetteGlobalTask = new MouvementCaisseRecetteGlobalTask(MouvementCaisseRecetteActivity.this, lv_mvmnt_caisse_recette , pb ,code_compte , date_debut , date_fin) ;
                                    mouvementCaisseRecetteGlobalTask.execute() ;
                                }
                                else if (global ==0)
                                {
                                    MouvementCaisseRecetteDetailTask mouvementCaisseRecetteDetailTask = new MouvementCaisseRecetteDetailTask(MouvementCaisseRecetteActivity.this, lv_mvmnt_caisse_recette , pb ,code_compte , date_debut , date_fin) ;
                                    mouvementCaisseRecetteDetailTask.execute() ;

                                }

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

                DatePickerDialog datePickerDialog = new DatePickerDialog(MouvementCaisseRecetteActivity.this, new DatePickerDialog.OnDateSetListener() {
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


                                if(global ==1)
                                {
                                    MouvementCaisseRecetteGlobalTask mouvementCaisseRecetteGlobalTask = new MouvementCaisseRecetteGlobalTask(MouvementCaisseRecetteActivity.this, lv_mvmnt_caisse_recette , pb ,code_compte , date_debut , date_fin) ;
                                    mouvementCaisseRecetteGlobalTask.execute() ;
                                }
                                else if (global ==0)
                                {
                                    MouvementCaisseRecetteDetailTask mouvementCaisseRecetteDetailTask = new MouvementCaisseRecetteDetailTask(MouvementCaisseRecetteActivity.this, lv_mvmnt_caisse_recette , pb ,code_compte , date_debut , date_fin) ;
                                    mouvementCaisseRecetteDetailTask.execute() ;

                                }

                            } catch (Exception e) {
                                Log.e("Exception --", " " + e.getMessage());
                            }

                        }
                    }
                }, year_x2, month_x2, day_x2);
                datePickerDialog.show();
            }
        });


        rb_global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                global = 1 ;
                MouvementCaisseRecetteGlobalTask mouvementCaisseRecetteGlobalTask = new MouvementCaisseRecetteGlobalTask(MouvementCaisseRecetteActivity.this, lv_mvmnt_caisse_recette , pb ,code_compte , date_debut , date_fin) ;
                mouvementCaisseRecetteGlobalTask.execute() ;
            }
        });


        rb_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global = 0 ;

                MouvementCaisseRecetteDetailTask mouvementCaisseRecetteDetailTask = new MouvementCaisseRecetteDetailTask(MouvementCaisseRecetteActivity.this, lv_mvmnt_caisse_recette , pb ,code_compte , date_debut , date_fin) ;
                mouvementCaisseRecetteDetailTask.execute() ;


            }
        });


    }
}