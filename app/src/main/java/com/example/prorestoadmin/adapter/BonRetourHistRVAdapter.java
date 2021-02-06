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
import com.example.prorestoadmin.model.BonLivraisonVente;
import com.example.prorestoadmin.model.BonRetourVente;
import com.example.prorestoadmin.model.LigneBonLivraisonVente;
import com.example.prorestoadmin.model.LigneBonRetourVente;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class BonRetourHistRVAdapter extends RecyclerView.Adapter<BonRetourHistRVAdapter.ViewHolder> {


    private  Activity activity;
    private final ArrayList<BonRetourVente> list_BonRetour  ;//= new ArrayList<>();
    DecimalFormat  numberFormat  = new DecimalFormat("0.000") ;
    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat heureFormat = new SimpleDateFormat("HH:mm");

    public BonRetourHistRVAdapter(Activity activity, ArrayList<BonRetourVente> list_BonRetour) {

        this.activity = activity;
        this.list_BonRetour = list_BonRetour;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bon_retour, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final BonRetourVente bonRetourVente = list_BonRetour.get(position);

        holder.txt_num_bt.setText(bonRetourVente.getNumeroBonRetourVente());
        holder.txt_date_bon_transfert.setText(df.format(bonRetourVente.getHeureCreation()));
        holder.txt_depot_source.setText(bonRetourVente.getCodeClient());
        holder.txt_depot_destination.setText(bonRetourVente.getRaisonSociale());
        holder.txt_etat.setText(bonRetourVente.getNumeroEtat());

        holder.txt_libelle.setText("Retour pour Client");
        holder.txt_detail.setText("Détail Retour");


      holder.  txt_etablie_par.setText("Etablie par : "+bonRetourVente.getNomUtilisateur());
        int nbr_ligne = bonRetourVente.getList_ligne_br().size();
        int height    = nbr_ligne * 60;


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  height );

        params.height = height  ;
        holder.lv_list_detail_transfert.setLayoutParams(params) ;

        Detail_BR_Adapter1 detail_bt_adapter1 = new Detail_BR_Adapter1(bonRetourVente.getList_ligne_br());
        holder.lv_list_detail_transfert.setAdapter(detail_bt_adapter1);





    }



    @Override
    public int getItemCount() {
        Log.e("size" ,""+list_BonRetour.size()) ;
        return list_BonRetour.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {


        public TextView txt_num_bt  ;
        public TextView txt_date_bon_transfert  ;

        public TextView txt_depot_source  ;
        public TextView txt_depot_destination  ;

        public TextView txt_etat   ;
        public ListView lv_list_detail_transfert   ;

        public CardView item_bon_transfert_en_attente ;

        public   TextView  txt_libelle  ;
        public   TextView  txt_detail  ;

        public TextView  txt_etablie_par ;

        public ViewHolder(View itemView) {
            super(itemView);

            item_bon_transfert_en_attente = (CardView) itemView.findViewById(R.id.item_bon_transfert_en_attente);

              txt_num_bt = (TextView) itemView.findViewById(R.id.txt_num_bt);
              txt_date_bon_transfert  = (TextView) itemView.findViewById(R.id.txt_date_bon_transfert);
              txt_depot_source       = (TextView) itemView.findViewById(R.id.txt_depot_source);
              txt_depot_destination     = (TextView) itemView.findViewById(R.id.txt_depot_destination);
              txt_etat = (TextView) itemView.findViewById(R.id.txt_etat);
              lv_list_detail_transfert = (ListView) itemView.findViewById(R.id.lv_list_detail_transfert);
            txt_etablie_par= (TextView) itemView.findViewById(R.id.txt_etablie_par);

            txt_libelle  = (TextView) itemView.findViewById(R.id.txt_libelle);
            txt_detail  = (TextView) itemView.findViewById(R.id.txt_detail_libelle);


        }

    }

    public class Detail_BR_Adapter1 extends ArrayAdapter<LigneBonRetourVente> {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        private final ArrayList<LigneBonRetourVente> list_ligne_bon_retour ;

        public Detail_BR_Adapter1(ArrayList<LigneBonRetourVente> list_ligne_bon_retour) {
            super(activity, R.layout.item_detail_bt, list_ligne_bon_retour);

            this.list_ligne_bon_retour = list_ligne_bon_retour;

            Log.e("adapter", "Constructor" + list_ligne_bon_retour.toString());

        }

        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.item_detail_bt, null, true);

            LigneBonRetourVente ligneBonRetourVente = list_ligne_bon_retour.get(position);

            TextView txt_designation = (TextView) rowView.findViewById(R.id.txt_designation_article);
            TextView txt_qt = (TextView) rowView.findViewById(R.id.txt_qt);

            txt_designation.setText(ligneBonRetourVente.getDesignationArticle());
            txt_qt.setText(ligneBonRetourVente.getQuantite() + "");

            return rowView;

        }

    }

}
