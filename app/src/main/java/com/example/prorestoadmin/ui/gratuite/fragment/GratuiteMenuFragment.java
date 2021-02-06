package com.example.prorestoadmin.ui.gratuite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.ui.gratuite.activity.BonGratuiteActivity;
import com.example.prorestoadmin.ui.gratuite.activity.GratuiteEnCoursActivity;
import com.example.prorestoadmin.ui.gratuite.activity.GratuiteExpireActivity;
import com.example.prorestoadmin.ui.gratuite.activity.MaquetteGratuiteActivity;
import com.example.prorestoadmin.ui.gratuite.activity.TracabiliteGratuiteClientActivity;
import com.example.prorestoadmin.ui.stock.StockViewModel;

public class GratuiteMenuFragment extends Fragment {

    private StockViewModel stockViewModel ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stockViewModel =
                ViewModelProviders.of(this).get(StockViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gratuite_menu, container, false);

        stockViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });




      final CardView btn_gratuite_en_cours =  root.findViewById(R.id.btn_gratuite_en_cours);
        btn_gratuite_en_cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity()  , GratuiteEnCoursActivity.class) );
            }
        });



        final CardView btn_gratuite_expire =  root.findViewById(R.id.btn_gratuite_expire);
        btn_gratuite_expire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity()  , GratuiteExpireActivity.class) );
            }
        });


        final CardView btn_maquette_gratuite =  root.findViewById(R.id.btn_maquette_gratuite);
        btn_maquette_gratuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity()  , MaquetteGratuiteActivity.class) );
            }
        });



        final CardView btn_bon_gratuite =  root.findViewById(R.id.btn_bon_gratuite);
        btn_bon_gratuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity()  , BonGratuiteActivity.class) );
            }
        });



        final CardView btn_tracabilite_gratuite_client =  root.findViewById(R.id.btn_tracabilite_gratuite_client);
        btn_tracabilite_gratuite_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity()  , TracabiliteGratuiteClientActivity.class) );
            }
        });


        //

        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Article");
    }

}