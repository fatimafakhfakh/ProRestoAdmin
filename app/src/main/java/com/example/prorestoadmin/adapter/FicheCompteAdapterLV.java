package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.ArticleStock;
import com.example.prorestoadmin.model.FicheCompte;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;


public class FicheCompteAdapterLV extends ArrayAdapter<FicheCompte> {


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    DecimalFormat formatter = new DecimalFormat("0.000");
    private final Activity activity;
    private final ArrayList<FicheCompte> listFicheCompte;

    public FicheCompteAdapterLV(Activity activity, ArrayList<FicheCompte> listFicheCompte) {
        super(activity, R.layout.item_fiche_compte  , listFicheCompte);
        // TODO Auto-generated constructor stub
        this.activity     = activity;
        this.listFicheCompte = listFicheCompte;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        Context context = parent.getContext();
        View rowView = inflater.inflate (R.layout.item_fiche_compte  , null, true);

        FicheCompte ficheCompte = listFicheCompte.get(position);

        CardView  card_item_fiche_article = (CardView) rowView.findViewById(R.id.card_personel);
        TextView  txt_operation = (TextView) rowView.findViewById(R.id.txt_operation);
        TextView  txt_numero_piece = (TextView) rowView.findViewById(R.id.txt_num_piece);
        TextView   txt_date_piece = (TextView) rowView.findViewById(R.id.txt_date_piece);
        TextView  txt_entee = (TextView) rowView.findViewById(R.id.txt_entree);
        TextView    txt_sortie = (TextView) rowView.findViewById(R.id.txt_sortie);
        TextView   txt_solde = (TextView) rowView.findViewById(R.id.txt_solde);

        
        
        
        txt_operation.setText(ficheCompte.getLibelle());
        txt_numero_piece.setText(ficheCompte.getNumeroPiece() + "");
        txt_date_piece.setText(sdf.format(ficheCompte.getDateOperation()) + "");


        txt_entee.setText(formatter.format(ficheCompte.getDebit() )+ "");
        txt_sortie.setText(formatter.format(ficheCompte.getCredit()) + "");

        txt_solde.setText(formatter.format(ficheCompte.getSolde_en_cours())+"");



        return rowView;

    }




}


