package com.example.prorestoadmin.ui.caisse;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.task.EtatDeCaisseDepenseTask;
import com.example.prorestoadmin.task.EtatDeCaisseTask;
import com.example.prorestoadmin.task.EtatPersonnelTask;
import com.example.prorestoadmin.ui.home.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CaisseFragment extends Fragment {

    private HomeViewModel homeViewModel ;

    FloatingActionButton fab_arrow;
    RelativeLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;

    RadioGroup rg_type_caisse ;
    RadioButton rb_tout ,rb_compte  ,rb_utilisateur ;

    public  static String CodeFonctionSlected ="" ;

    public static double tot_caisse_compte =0  , tot_caisse_user  =0 ;
    public static  TextView  txt_tot_caisse_compte  , txt_tot_caisse_user  ;


    RecyclerView rv_list_caisse_depense  ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =   ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_caisse, container, false);

        // final TextView textView = root.findViewById(R.id.text_home);

        final RecyclerView rv_caisse = root.findViewById(R.id.rv_caisse);
        final ProgressBar progressBar = root.findViewById(R.id.progress_bar);
        rv_list_caisse_depense = root.findViewById(R.id.rv_list_caisse_depense)  ;


        rv_caisse.setHasFixedSize(true);
        rv_caisse.setLayoutManager( new LinearLayoutManager( getActivity() ));


        rv_list_caisse_depense.setHasFixedSize(true);
        rv_list_caisse_depense.setLayoutManager( new LinearLayoutManager(getActivity() ));


        rg_type_caisse= root.findViewById(R.id.rg_type_caisse) ;
        rb_tout = root.findViewById(R.id.rb_tout) ;
        rb_compte = root.findViewById(R.id.rb_compte) ;
        rb_utilisateur = root.findViewById(R.id.rb_utilisateur) ;


        txt_tot_caisse_compte = root.findViewById(R.id.txt_tot_compte) ;
        txt_tot_caisse_user = root.findViewById(R.id.txt_tot_utilisateur) ;


        rb_tout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EtatDeCaisseTask  etatDeCaisseTask  = new EtatDeCaisseTask(getActivity()  , rv_caisse , progressBar, "Tout") ;
                etatDeCaisseTask.execute() ;

            }
        });

        rb_compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EtatDeCaisseTask  etatDeCaisseTask  = new EtatDeCaisseTask(getActivity()  , rv_caisse , progressBar ,"Compte") ;
                etatDeCaisseTask.execute() ;
            }
        });

        rb_utilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EtatDeCaisseTask  etatDeCaisseTask  = new EtatDeCaisseTask(getActivity()  , rv_caisse , progressBar ,"User") ;
                etatDeCaisseTask.execute() ;
            }
        });



        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);

                EtatDeCaisseTask  etatDeCaisseTask  = new EtatDeCaisseTask(getActivity()  , rv_caisse , progressBar ,"Tout") ;
                etatDeCaisseTask.execute() ;


                EtatDeCaisseDepenseTask etatDeCaisseDepenseTask  = new EtatDeCaisseDepenseTask(getActivity() , rv_list_caisse_depense , progressBar   ) ;
                etatDeCaisseDepenseTask.execute() ;

            }
        });



        layoutBottomSheet = (RelativeLayout) root.findViewById(R.id.bottom_sheet);

        fab_arrow = (FloatingActionButton) root.findViewById(R.id.fab_arrow);

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




        return root;
    }
}