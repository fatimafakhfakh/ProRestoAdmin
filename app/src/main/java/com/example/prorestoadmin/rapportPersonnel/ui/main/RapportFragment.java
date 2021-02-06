package com.example.prorestoadmin.rapportPersonnel.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.rapportPersonnel.task.HistoriqueGratuiteTask;
import com.example.prorestoadmin.rapportPersonnel.task.HistoriqueLivraisonTask;
import com.example.prorestoadmin.rapportPersonnel.task.HistoriqueReglementTask;
import com.example.prorestoadmin.rapportPersonnel.task.HistoriqueRetourTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RapportFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_NOM_UTILISATEUR = "NomUtilisateur";
    private static final String ARG_DATE = "date";
    private PageViewModel pageViewModel;
    String NomUtilisateur;

    Date date_rapport  ;
    int index = 1;

    private static final SimpleDateFormat  sdf  = new SimpleDateFormat("dd/MM/yyyy") ;

    public static RapportFragment newInstance(int index, String NomUtilisateur, Date  date_etat) {
        RapportFragment fragment = new RapportFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putString(ARG_NOM_UTILISATEUR, NomUtilisateur);
        bundle.putString(ARG_DATE, sdf.format(date_etat));

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            NomUtilisateur = getArguments().getString(ARG_NOM_UTILISATEUR);

            try {
                date_rapport = sdf.parse(getArguments().getString(ARG_DATE)) ;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rapport, container, false);

        final TextView textView = root.findViewById(R.id.section_label);
        final RecyclerView rv_list_bl = root.findViewById(R.id.rv_list_bl_br_);
        final ProgressBar progressBar = root.findViewById(R.id.pb);


        rv_list_bl.setHasFixedSize(true);
        rv_list_bl.setLayoutManager(new LinearLayoutManager(getActivity()));


        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //    textView.setText(s);

                //Toast.makeText(getActivity() ," tab fragment "+index,Toast.LENGTH_LONG).show(); ;

                if (index == 1) {  //   livraison

                    HistoriqueLivraisonTask historiqueLivraisonTask = new HistoriqueLivraisonTask(getActivity(), "", NomUtilisateur, date_rapport, date_rapport, rv_list_bl, progressBar);
                    historiqueLivraisonTask.execute();

                }

                else   if (index == 2) { //  retour

                    HistoriqueRetourTask historiqueRetourTask = new HistoriqueRetourTask(getActivity(), "", NomUtilisateur, date_rapport, date_rapport, rv_list_bl, progressBar);
                    historiqueRetourTask.execute();

                }

                else   if (index == 3) {  //  gratuit

                    HistoriqueGratuiteTask historiqueGratuiteTask = new HistoriqueGratuiteTask(getActivity(), NomUtilisateur,   date_rapport, date_rapport, rv_list_bl, progressBar);
                    historiqueGratuiteTask.execute();

                }

                else   if (index == 4) {  //  caisse

                    HistoriqueReglementTask historiqueRegTask  = new HistoriqueReglementTask(getActivity() , "",NomUtilisateur , date_rapport, date_rapport , rv_list_bl, progressBar) ;
                    historiqueRegTask.execute();

                }

            }
        });
        return root;
    }
}