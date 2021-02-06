package com.example.prorestoadmin.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.task.EtatPersonnelTask;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    FloatingActionButton fab_arrow;
    RelativeLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;

    CardView  btn_date_du_jour  ;
    TextView txt_date_du_jour  ,txt_mois_du_jour  , txt_annee_du_jour  ;

    int id_DatePickerDialog = 0;
    Date currentDate = new Date();
    public static Date  date_selected ;
    public static int year_x1, month_x1, day_x1;

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("00");

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
    SimpleDateFormat moisFormat = new SimpleDateFormat("MMM");
    SimpleDateFormat anneeormat = new SimpleDateFormat("yyyy");

    RadioGroup rg_fonction ;
    RadioButton rb_tout ,rb_liv_rec  ,rb_agent_bureau ;

    public  static String  CodeFonctionSlected ="" ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        homeViewModel =   ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //final TextView textView = root.findViewById(R.id.text_home);

        final RecyclerView rv_list_personnel = root.findViewById(R.id.rv_etat_pesonnel);
        final ProgressBar progressBar = root.findViewById(R.id.progress_bar);
        rv_list_personnel.setHasFixedSize(true);
        rv_list_personnel.setLayoutManager( new LinearLayoutManager( getActivity() ) );


        final   TextView  txt_tot_livraison  = root.findViewById(R.id.txt_tot_livraison) ;
        final   TextView  txt_tot_retour = root.findViewById(R.id.txt_tot_retour);
        final   TextView  txt_tot_gratuit = root.findViewById(R.id.txt_tot_gratuit);
        final   TextView  txt_tot_draft_reg = root.findViewById(R.id.txt_tot_draft_reg);
        final   TextView  txt_tot_livraison_ttc = root.findViewById(R.id.txt_tot_livraison_ttc);
        final   TextView  txt_tot_retour_ttc = root.findViewById(R.id.txt_tot_retour_ttc);
        final   TextView   txt_prix_moy_pondere= root.findViewById(R.id.txt_prix_moy_pndere);


        btn_date_du_jour = root.findViewById(R.id.btn_date_du_jour);
        txt_date_du_jour  = root.findViewById(R.id.txt_date_du_jour) ;
        txt_mois_du_jour  = root.findViewById(R.id.txt_mois_du_jour);
        txt_annee_du_jour = root.findViewById(R.id.txt_annee_du_jour) ;


        rg_fonction= root.findViewById(R.id.rg_fonction) ;
        rb_tout = root.findViewById(R.id.rb_tout) ;
        rb_liv_rec = root.findViewById(R.id.rb_liv_recouv) ;
        rb_agent_bureau = root.findViewById(R.id.rb_agent_bureau) ;


        CodeFonctionSlected  = "  ('FN006','FN007','FN008') " ;
        date_selected =currentDate ;

        rb_tout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodeFonctionSlected  = "  ('FN006','FN007','FN008' ,'FN002','FN003' )  " ;
                EtatPersonnelTask  etatPersonnelTask = new EtatPersonnelTask(getActivity()  ,date_selected ,CodeFonctionSlected , rv_list_personnel  ,progressBar,txt_tot_livraison,txt_tot_retour,txt_tot_gratuit,txt_tot_draft_reg,txt_tot_livraison_ttc,txt_tot_retour_ttc,txt_prix_moy_pondere) ;
                etatPersonnelTask.execute();
            }
        });


        rb_liv_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodeFonctionSlected  = "  ('FN006','FN007','FN008'  )  " ;
                EtatPersonnelTask  etatPersonnelTask = new EtatPersonnelTask(getActivity()  ,date_selected ,CodeFonctionSlected , rv_list_personnel  ,progressBar,txt_tot_livraison,txt_tot_retour,txt_tot_gratuit,txt_tot_draft_reg,txt_tot_livraison_ttc,txt_tot_retour_ttc,txt_prix_moy_pondere) ;
                etatPersonnelTask.execute();
            }
        });

        rb_agent_bureau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodeFonctionSlected  = "  ( 'FN002','FN003' )  " ;
                EtatPersonnelTask  etatPersonnelTask = new EtatPersonnelTask(getActivity()  ,date_selected ,CodeFonctionSlected , rv_list_personnel  ,progressBar,txt_tot_livraison,txt_tot_retour,txt_tot_gratuit,txt_tot_draft_reg,txt_tot_livraison_ttc ,txt_tot_retour_ttc,txt_prix_moy_pondere) ;
                etatPersonnelTask.execute();
            }
        });


        txt_date_du_jour.setText(dateFormat.format(currentDate));
        txt_mois_du_jour.setText(moisFormat.format(currentDate));
        txt_annee_du_jour.setText(anneeormat.format(currentDate));


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);

                EtatPersonnelTask  etatPersonnelTask = new EtatPersonnelTask(getActivity()  ,date_selected ,CodeFonctionSlected , rv_list_personnel  ,progressBar,txt_tot_livraison,txt_tot_retour,txt_tot_gratuit,txt_tot_draft_reg,txt_tot_livraison_ttc ,txt_tot_retour_ttc,txt_prix_moy_pondere) ;
                etatPersonnelTask.execute();



            }
        });


        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(currentDate);
        //cal1.add(Calendar.DAY_OF_YEAR, -7);
        year_x1  = cal1.get(Calendar.YEAR);
        month_x1 = cal1.get(Calendar.MONTH);
        day_x1   = cal1.get(Calendar.DAY_OF_MONTH);


        btn_date_du_jour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id_DatePickerDialog = 1;

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (id_DatePickerDialog == 1) {

                            year_x1 = year;
                            month_x1 = monthOfYear;
                            day_x1= dayOfMonth;


                            String _date_du_jour = "" + formatter.format(day_x1) + "/" + formatter.format(month_x1 + 1) + "/" + year_x1;

                            try {
                                Date  date_seleted = df.parse(_date_du_jour) ;
                                txt_date_du_jour.setText(dateFormat.format(date_seleted));
                                txt_mois_du_jour.setText(moisFormat.format(date_seleted));
                                txt_annee_du_jour.setText(anneeormat.format(date_seleted));

                                date_selected =date_seleted;

                                EtatPersonnelTask  etatPersonnelTask = new EtatPersonnelTask(getActivity()  ,date_selected,CodeFonctionSlected , rv_list_personnel  ,progressBar,txt_tot_livraison,txt_tot_retour,txt_tot_gratuit,txt_tot_draft_reg,txt_tot_livraison_ttc ,txt_tot_retour_ttc,txt_prix_moy_pondere) ;
                                etatPersonnelTask.execute();

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }





                        }
                    }
                }, year_x1, month_x1, day_x1);
                datePickerDialog.show();



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







        return root;
    }
}