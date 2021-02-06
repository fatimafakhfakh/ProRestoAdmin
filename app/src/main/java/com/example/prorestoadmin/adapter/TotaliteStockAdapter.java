package com.example.prorestoadmin.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prorestoadmin.R ;
import com.example.prorestoadmin.model.Depot;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;


/**
 **  Created by fatima on 20/01/2017.
 **/
public class TotaliteStockAdapter extends ArrayAdapter<Depot> {




    private final Activity activity;
    private final ArrayList<Depot> listDepot;

    public TotaliteStockAdapter(Activity activity  , ArrayList<Depot> listDepot ) {
        super(activity, R.layout.item_depot , listDepot);

        this.activity=activity;
        this.listDepot=listDepot ;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        Context context = parent.getContext();
        View rowView=inflater.inflate(R.layout.item_depot, null, true);

        CardView backgroundItem =  (CardView)   rowView.findViewById(R.id.card_depot);


        TextView txt_libelle_depot = (TextView)   rowView.findViewById(R.id.txt_libelle_depot);
        TextView txt_quantite_tot = (TextView)   rowView.findViewById(R.id.txt_quantite);
        TextView txt_qt_transfert = (TextView)   rowView.findViewById(R.id.txt_qt_transfert);


         Depot depot  = listDepot.get(position)  ;


        txt_libelle_depot.setText(depot.getLibelle());
        txt_quantite_tot.setText( depot.getTotalQuantiteStock() +"");

         if (depot.getTotalTransfertStaock() ==0)
        {
            txt_qt_transfert.setVisibility(View.INVISIBLE);

        }
      else  if (depot.getTotalTransfertStaock() !=0)
        {

            txt_qt_transfert.setVisibility(View.VISIBLE);
            txt_qt_transfert.setText("(+"+depot.getTotalTransfertStaock()+")");
        }


        if (position % 2 ==0 ) // paire
        {
            backgroundItem.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }

        if(position  ==  (listDepot.size()-1))
        {
            backgroundItem.setBackgroundColor(Color.parseColor("#EDFCF0F0"));
        }
        return rowView;

    }


}
