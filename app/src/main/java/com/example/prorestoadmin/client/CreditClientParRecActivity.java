package com.example.prorestoadmin.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import  com.example.prorestoadmin.R ;
import com.example.prorestoadmin.task.EtatCreditClientParRecTask;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditClientParRecActivity extends AppCompatActivity {

    SearchView searchViewClient  ;
    public static ProgressBar pb;
    public static RecyclerView rv_list_bl_credit_par_rec ;
    public TextView txt_date_debut, txt_date_fin;

    int id_DatePickerDialog = 0;
    Date currentDate = new Date();

    public static int year_x1, month_x1, day_x1;
    public static int year_x2, month_x2, day_x2;


    public static Date date_debut = null;
    public static Date date_fin = null;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("00");

    FloatingActionButton fab_arrow;
    RelativeLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;

    public   static    TextView  txt_tot_credit  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_client_par_rec);

        String  Code_Recouv     = getIntent().getStringExtra( "cle_code_recouv") ;
        String  Nom_Recouv     = getIntent().getStringExtra( "cle_Nom_recouv") ;
        String  Date_Debut     = getIntent().getStringExtra( "cle_date_debut") ;
        String  Date_Fin     = getIntent().getStringExtra( "cle_date_fin") ;


        txt_tot_credit = findViewById(R.id.txt_tot_credit) ;
        TextView   txt_nom_rec =  findViewById(R.id.txt_nom_rec) ;

        txt_date_debut =  findViewById(R.id.txt_date_debut);
        txt_date_fin =  findViewById(R.id.txt_date_fin);

        txt_date_debut.setText(Date_Debut);
        txt_date_fin.setText(Date_Fin);
        txt_nom_rec.setText(Nom_Recouv);

        searchViewClient =  findViewById(R.id.search_bar_client);

        rv_list_bl_credit_par_rec = (RecyclerView)  findViewById(R.id.rv_list_bl_credit_par_rec);
        pb = (ProgressBar)  findViewById(R.id.pb);

        rv_list_bl_credit_par_rec.setHasFixedSize(true);
        rv_list_bl_credit_par_rec.setLayoutManager(new LinearLayoutManager(this));

        try {
            date_debut = df.parse(Date_Debut);
            date_fin = df.parse(Date_Fin);
        }
        catch (Exception e) {

            Log.e("Exception--", " " + e.getMessage());

        }

        EtatCreditClientParRecTask etatCreditClientParRecTask  = new EtatCreditClientParRecTask(this ,Code_Recouv ,date_debut,date_fin ,rv_list_bl_credit_par_rec ,pb ,searchViewClient);
        etatCreditClientParRecTask.execute();

        layoutBottomSheet = (RelativeLayout)  findViewById(R.id.bottom_sheet);
        fab_arrow = (FloatingActionButton)  findViewById(R.id.fab_arrow);

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setHideable(false);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                        // Toast.makeText(getActivity() , "Close Sheet" ,Toast.LENGTH_LONG).show();
                        fab_arrow.setImageResource(R.drawable.ic_arrow_down);

                      /* ReptureStockClientTaskTask reptureStockClientTaskTask = new ReptureStockClientTaskTask(getActivity() ,"1" , exptens_lv_stock_en_repture) ;
                        reptureStockClientTaskTask.execute() ;*/

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        // Toast.makeText(getActivity() , "Expand Sheet" ,Toast.LENGTH_LONG).show();
                        fab_arrow.setImageResource(R.drawable.ic_arrow_up);
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });




    }
}