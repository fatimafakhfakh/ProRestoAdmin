package com.example.prorestoadmin.adapter;

import android.app.Activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.BonEntre;
import com.example.prorestoadmin.model.LigneBonEntre;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class BonEntreeRVAdapter extends RecyclerView.Adapter<BonEntreeRVAdapter.ViewHolder> {


    private  Activity activity;
    private final ArrayList<BonEntre> list_BonEntre  ;//= new ArrayList<>();
    DecimalFormat  numberFormat  = new DecimalFormat("0.000") ;
    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public BonEntreeRVAdapter(Activity activity, ArrayList<BonEntre> list_BonEntre ) {

        this.activity = activity;
        this.list_BonEntre = list_BonEntre;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bon_entree, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final BonEntre bonEntre = list_BonEntre.get(position);
        holder.txt_libelle.setText("Entrée");

        holder.txt_num_bt.setText(bonEntre.getNumeroBonEntrer());
        holder.txt_date_bon_transfert.setText(df.format(bonEntre.getDateBonEntrer()));
        holder.txt_depot_source.setText("Production");
        holder. txt_detail_libelle.setText("Détail Entrée");
        holder.txt_depot_destination.setText( bonEntre.getLibelleDepot());
        holder.txt_etat.setText(bonEntre.getLibeleEtat());
        holder.txt_etablie_par.setText("Etablie par : "+bonEntre.getNomUtilisateur());


        int nbr_ligne = bonEntre.getListLigneBonEntree().size();
        int height    = nbr_ligne * 60;


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  height );


        params.height = height  ;
        holder.lv_list_detail_transfert.setLayoutParams(params) ;


        Detail_bt_Adapter1 detail_bt_adapter1 = new Detail_bt_Adapter1(bonEntre.getListLigneBonEntree());
        holder.lv_list_detail_transfert.setAdapter(detail_bt_adapter1);



    }



    @Override
    public int getItemCount() {
        Log.e("size" ,""+list_BonEntre.size()) ;
        return list_BonEntre.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {


        public TextView   txt_libelle ;

        public TextView txt_num_bt  ;
        public TextView txt_date_bon_transfert  ;

        public TextView txt_depot_source  ;
        public TextView txt_depot_destination  ;

        public TextView   txt_detail_libelle ;
        public TextView txt_etat   ;
        public ListView lv_list_detail_transfert   ;

        public TextView  txt_etablie_par ;

        public CardView item_bon_transfert_en_attente ;

        public ViewHolder(View itemView) {
            super(itemView);

              item_bon_transfert_en_attente = (CardView) itemView.findViewById(R.id.item_bon_transfert_en_attente);

              txt_libelle = (TextView) itemView.findViewById(R.id.txt_libelle);
              txt_num_bt = (TextView) itemView.findViewById(R.id.txt_num_bt);
              txt_date_bon_transfert  = (TextView) itemView.findViewById(R.id.txt_date_bon_transfert);
              txt_depot_source       = (TextView) itemView.findViewById(R.id.txt_depot_source);
              txt_depot_destination     = (TextView) itemView.findViewById(R.id.txt_depot_destination);
              txt_detail_libelle = (TextView) itemView.findViewById ( R.id.txt_detail_libelle );
              txt_etat = (TextView) itemView.findViewById(R.id.txt_etat);
              txt_etablie_par= (TextView) itemView.findViewById(R.id.txt_etablie_par);
              lv_list_detail_transfert = (ListView) itemView.findViewById(R.id.lv_list_detail_transfert);



        }

    }

    public class Detail_bt_Adapter1 extends ArrayAdapter<LigneBonEntre> {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        private final ArrayList<LigneBonEntre> list_ligne_bon_entree;

        public Detail_bt_Adapter1(ArrayList<LigneBonEntre> list_ligne_bon_entree) {
            super(activity, R.layout.item_detail_bt, list_ligne_bon_entree);

            this.list_ligne_bon_entree = list_ligne_bon_entree;

            Log.e("adapter", "Constructor" + list_ligne_bon_entree.toString());

        }

        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.item_detail_bt, null, true);

            LigneBonEntre ligneBonEntree = list_ligne_bon_entree.get(position);

            TextView txt_designation = (TextView) rowView.findViewById(R.id.txt_designation_article);
            TextView txt_qt = (TextView) rowView.findViewById(R.id.txt_qt);

            txt_designation.setText(ligneBonEntree.getDesignationArticle());
            txt_qt.setText(ligneBonEntree.getQuantite() + "");

            return rowView;

        }

    }

}
