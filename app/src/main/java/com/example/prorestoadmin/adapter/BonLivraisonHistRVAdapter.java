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
import com.example.prorestoadmin.model.LigneBonLivraisonVente;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class BonLivraisonHistRVAdapter extends RecyclerView.Adapter<BonLivraisonHistRVAdapter.ViewHolder> {


    private Activity activity;
    private final ArrayList<BonLivraisonVente> list_BonLivraison;//= new ArrayList<>();
    DecimalFormat numberFormat = new DecimalFormat("0.000");
    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat heureFormat = new SimpleDateFormat("HH:mm");

    public BonLivraisonHistRVAdapter(Activity activity, ArrayList<BonLivraisonVente> list_BonLivraison) {

        this.activity = activity;
        this.list_BonLivraison = list_BonLivraison;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bon_livraison_rapport, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final BonLivraisonVente bonLivraisonVente = list_BonLivraison.get(position);

        holder.txt_num_bt.setText(bonLivraisonVente.getNumeroBonLivraisonVente());
        holder.txt_date_bon_transfert.setText(df.format(bonLivraisonVente.getHeureCreation()));
        holder.txt_depot_source.setText(bonLivraisonVente.getCodeClient());
        holder.txt_depot_destination.setText(bonLivraisonVente.getRaisonSociale());
        holder.txt_etat_payement.setText(bonLivraisonVente.getLibelleEtatPayement());
        holder.txt_paye_par.setText(bonLivraisonVente.getPayePar());
        holder.txt_draft_par.setText(bonLivraisonVente.getDraftePar());

        holder.txt_ttc .setText(numberFormat.format(bonLivraisonVente.getTotalTTC())+" Dt");

        if (bonLivraisonVente.getLibelleEtatPayement().equals("Payé"))
            holder.txt_etat_payement.setText(bonLivraisonVente.getLibelleEtatPayement() +" par ");

        try {
            if ( bonLivraisonVente.getDraftePar().isEmpty())
                holder.txt_draft.setVisibility(View.INVISIBLE);
            else  holder.txt_draft.setVisibility(View.VISIBLE);
        }
        catch (Exception ex)
        {
            Log.e("error_adapter" , ex.getMessage().toString() );
        }



        holder.txt_etablie_par.setText("Etablie par : "+bonLivraisonVente.getEtabliePar());

        holder.txt_libelle.setText("Livraion pour Client");
        holder.txt_detail.setText("Détail Livraion");


        int nbr_ligne = bonLivraisonVente.getList_ligne_bl().size();
        int height = nbr_ligne * 60;

        holder.ll_reste_a_payer.setVisibility(View.GONE);

        if (bonLivraisonVente.getNumeroEtatPaiment().equals("E30"))
        {
            holder.ll_reste_a_payer.setVisibility(View.VISIBLE);
            holder.txt_reste_a_payer.setText(numberFormat.format(bonLivraisonVente.getResteAPayer())+" Dt");
        }

        if (bonLivraisonVente.getNumeroEtat().equals("E00"))
            holder.item_bl.setCardBackgroundColor(activity.getResources().getColor(R.color.back_rouge_));


        else if (bonLivraisonVente.isDraft())
        {
            holder.ll_draft.setVisibility(View.VISIBLE);
            holder.item_bl.setCardBackgroundColor(activity.getResources().getColor(R.color.back_piece_drafte));
        }

      else    if (bonLivraisonVente.getNumeroEtatPaiment().equals("E08"))  // payé

            holder.item_bl.setCardBackgroundColor(activity.getResources().getColor(R.color.back_card_green));

        else if (bonLivraisonVente.getNumeroEtatPaiment().equals("E30")) // Partiellement Payé

            holder.item_bl.setCardBackgroundColor(activity.getResources().getColor(R.color.back_card_orange)) ;

         else if (!bonLivraisonVente.isDraft())

         {
             holder.ll_draft.setVisibility(View.GONE);
             holder.item_bl.setCardBackgroundColor(activity.getResources().getColor(R.color.white));
         }
        else

            holder.item_bl.setCardBackgroundColor(activity.getResources().getColor(R.color.white));


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);

        params.height = height;
        holder.lv_list_detail_transfert.setLayoutParams(params);

        Detail_BL_Adapter1 detail_bt_adapter1 = new Detail_BL_Adapter1(bonLivraisonVente.getList_ligne_bl());
        holder.lv_list_detail_transfert.setAdapter(detail_bt_adapter1);


        if (bonLivraisonVente.getNumeroEtat().equals("E00"))
            holder.txt_etat_payement.setText("Annulé");



    }


    @Override
    public int getItemCount() {
        Log.e("size", "" + list_BonLivraison.size());
        return list_BonLivraison.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView txt_num_bt;
        public TextView txt_date_bon_transfert;

        public TextView txt_depot_source;
        public TextView txt_depot_destination;

        //public TextView txt_etat   ;
        public ListView lv_list_detail_transfert;

        public CardView item_bl;

        public TextView txt_libelle;
        public TextView txt_detail;

        public LinearLayout  ll_draft ;
        public TextView   txt_draft ;
        public TextView txt_draft_par;
        public TextView txt_etat_payement;
        public TextView txt_paye_par;
        public TextView txt_etablie_par;


        public  TextView  txt_ttc  ;
        public LinearLayout  ll_reste_a_payer ;
        public TextView   txt_reste_a_payer  ;


        public ViewHolder(View itemView) {
            super(itemView);

            item_bl = (CardView) itemView.findViewById(R.id.item_bon_livraison_rapport);

            txt_num_bt = (TextView) itemView.findViewById(R.id.txt_num_bt);
            txt_date_bon_transfert = (TextView) itemView.findViewById(R.id.txt_date_bon_transfert);
            txt_depot_source = (TextView) itemView.findViewById(R.id.txt_depot_source);
            txt_depot_destination = (TextView) itemView.findViewById(R.id.txt_depot_destination);
            //txt_etat = (TextView) itemView.findViewById(R.id.txt_etat);
            lv_list_detail_transfert = (ListView) itemView.findViewById(R.id.lv_list_detail_transfert);
            txt_ttc = (TextView) itemView.findViewById(R.id.txt_ttc) ;


            txt_libelle = (TextView) itemView.findViewById(R.id.txt_libelle);
            txt_detail = (TextView) itemView.findViewById(R.id.txt_detail_libelle);


            ll_draft= (LinearLayout) itemView.findViewById(R.id.ll_draft);
            txt_draft= (TextView) itemView.findViewById(R.id.txt_draft);
            txt_draft_par = (TextView) itemView.findViewById(R.id.txt_drafte_par);
            txt_etat_payement = (TextView) itemView.findViewById(R.id.txt_etat_payement);
            txt_paye_par = (TextView) itemView.findViewById(R.id.txt_paye_par);
            txt_etablie_par = (TextView) itemView.findViewById(R.id.txt_etablie_par);


            ll_reste_a_payer  = (LinearLayout) itemView.findViewById(R.id.ll_reste_a_payer );
            txt_reste_a_payer  = (TextView) itemView.findViewById(R.id.txt_reste_a_payer);


        }

    }

    public class Detail_BL_Adapter1 extends ArrayAdapter<LigneBonLivraisonVente> {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        private final ArrayList<LigneBonLivraisonVente> list_ligne_bon_livraison;

        public Detail_BL_Adapter1(ArrayList<LigneBonLivraisonVente> list_ligne_bon_livraion) {
            super(activity, R.layout.item_detail_bt, list_ligne_bon_livraion);

            this.list_ligne_bon_livraison = list_ligne_bon_livraion;

            Log.e("adapter", "Constructor" + list_ligne_bon_livraison.toString());

        }

        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.item_detail_bt, null, true);

            LigneBonLivraisonVente ligneBonLivraisonVente = list_ligne_bon_livraison.get(position);

            TextView txt_designation = (TextView) rowView.findViewById(R.id.txt_designation_article);
            TextView txt_qt = (TextView) rowView.findViewById(R.id.txt_qt);

            txt_designation.setText(ligneBonLivraisonVente.getDesignationArticle());
            txt_qt.setText(ligneBonLivraisonVente.getQuantite() + "");

            return rowView;

        }

    }


}


