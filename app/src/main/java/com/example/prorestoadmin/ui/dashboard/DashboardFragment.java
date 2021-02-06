package com.example.prorestoadmin.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.ui.Article.ArticleMenuFragment;
import com.example.prorestoadmin.ui.caisse.MouvementCaisseDepenseDetailActivity;
import com.example.prorestoadmin.ui.client.ClientMenuFragment;
import com.example.prorestoadmin.ui.gratuite.fragment.GratuiteMenuFragment;
import com.example.prorestoadmin.ui.stock.StockMenuFragment;
import com.example.prorestoadmin.ui.vente.VenteMenuFragment;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        // final TextView textView = root.findViewById(R.id.text_dashboard);






        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });


        final CardView btn_stock = (CardView) root.findViewById(R.id.btn_stock);
       btn_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new StockMenuFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                if (fragment != null) {
                    ft.replace(R.id.nav_host_fragment, fragment);
                     ft.addToBackStack(null);
                    ft.commit();
                }

            }
        });

        final CardView btn_article = root.findViewById(R.id.btn_article);
        btn_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ArticleMenuFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                if (fragment != null) {
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }

            }
        });




        final CardView btn_vente = root.findViewById(R.id.btn_vente);
        btn_vente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new VenteMenuFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                if (fragment != null) {
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }

            }
        });



        final CardView btn_client = root.findViewById(R.id.btn_client);
        btn_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ClientMenuFragment() ;
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                if (fragment != null) {
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });



        final CardView   btn_mouvement_caisse_depense= root.findViewById(R.id.btn_mouvement_caisse_depense);
        btn_mouvement_caisse_depense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  toMouvemntCaisseDep  = new Intent(getActivity() , MouvementCaisseDepenseDetailActivity.class) ;
                startActivity(toMouvemntCaisseDep);

            }
        });


        final CardView btn_gratuite = root.findViewById(R.id.btn_gratuite);
        btn_gratuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new GratuiteMenuFragment() ;
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                if (fragment != null) {
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });




        return root;
    }
}