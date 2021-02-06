package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.Client;
import com.example.prorestoadmin.model.Personnel;
import com.example.prorestoadmin.rapportPersonnel.RapportActivity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RemiseClientAdapterRV extends RecyclerView.Adapter<RemiseClientAdapterRV.ViewHolder> {


    private final Activity activity;

    private final ArrayList<Client> listClient ;


    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("0.00");


    public RemiseClientAdapterRV(Activity activity, ArrayList<Client> listClient) {

        this.activity = activity;
        this.listClient = listClient;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client_remise, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Client client = listClient.get(position);

        holder.txt_raison_social.setText(client.getRaisonSocial());


        holder.txt_code_article_001.setText(client.getListRemiseArticleClient().get(0).getCodeArticle());
        holder.txt_designaton_001.setText(client.getListRemiseArticleClient().get(0).getDesignation());
        holder.txt_taux_remise_001.setText(formatter.format(client.getListRemiseArticleClient().get(0).getTauxRemise())+" %");
        holder.txt_ttc_001.setText(formatter.format(client.getListRemiseArticleClient().get(0).getPrixVenteTTCRemise())+"");



        holder.txt_code_article_002.setText(client.getListRemiseArticleClient().get(1).getCodeArticle());
        holder.txt_designaton_002.setText(client.getListRemiseArticleClient().get(1).getDesignation());
        holder.txt_taux_remise_002.setText(formatter.format(client.getListRemiseArticleClient().get(1).getTauxRemise())+" %");
        holder.txt_ttc_002.setText(formatter.format(client.getListRemiseArticleClient().get(1).getPrixVenteTTCRemise())+"");



        holder.txt_code_article_003.setText(client.getListRemiseArticleClient().get(2).getCodeArticle());
        holder.txt_designaton_003.setText(client.getListRemiseArticleClient().get(2).getDesignation());
        holder.txt_taux_remise_003.setText(formatter.format(client.getListRemiseArticleClient().get(2).getTauxRemise())+" %");
        holder.txt_ttc_003.setText(formatter.format(client.getListRemiseArticleClient().get(2).getPrixVenteTTCRemise())+"");



        holder.txt_code_article_004.setText(client.getListRemiseArticleClient().get(3).getCodeArticle());
        holder.txt_designaton_004.setText(client.getListRemiseArticleClient().get(3).getDesignation());
        holder.txt_taux_remise_004.setText(formatter.format(client.getListRemiseArticleClient().get(3).getTauxRemise())+" %");
        holder.txt_ttc_004.setText(formatter.format(client.getListRemiseArticleClient().get(3).getPrixVenteTTCRemise())+"");



        holder.txt_code_article_005.setText(client.getListRemiseArticleClient().get(4).getCodeArticle());
        holder.txt_designaton_005.setText(client.getListRemiseArticleClient().get(4).getDesignation());
        holder.txt_taux_remise_005.setText(formatter.format(client.getListRemiseArticleClient().get(4).getTauxRemise())+" %");
        holder.txt_ttc_005.setText(formatter.format(client.getListRemiseArticleClient().get(4).getPrixVenteTTCRemise())+"");


        holder.txt_code_article_006.setText(client.getListRemiseArticleClient().get(5).getCodeArticle());
        holder.txt_designaton_006.setText(client.getListRemiseArticleClient().get(5).getDesignation());
        holder.txt_taux_remise_006.setText(formatter.format(client.getListRemiseArticleClient().get(5).getTauxRemise())+" %");
        holder.txt_ttc_006.setText(formatter.format(client.getListRemiseArticleClient().get(5).getPrixVenteTTCRemise())+"");



        /* holder.txt_livraison.setText ( personnel.getQTLivraison() +"");
         holder.txt_retour.setText(personnel.getQTRetour()+"");
         holder.txt_gratuit.setText(personnel.getQTGratuite()+"");
         holder.txt_reglement.setText(formatter.format(personnel.getTotalDraftRèglement())+"Dt");
*/



    }


    @Override
    public int getItemCount() {
        Log.e("size", "" + listClient.size());
        return listClient.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public CardView card_item_personnel;

        public TextView txt_raison_social   ;
        //001
        public TextView txt_code_article_001;
        public TextView txt_designaton_001;
        public TextView txt_taux_remise_001;
        public TextView txt_ttc_001;


        //002
        public TextView txt_code_article_002;
        public TextView txt_designaton_002;
        public TextView txt_taux_remise_002;
        public TextView txt_ttc_002;


        //003
        public TextView txt_code_article_003;
        public TextView txt_designaton_003;
        public TextView txt_taux_remise_003;
        public TextView txt_ttc_003;


        //004
        public TextView txt_code_article_004;
        public TextView txt_designaton_004;
        public TextView txt_taux_remise_004;
        public TextView txt_ttc_004;


        //005
        public TextView txt_code_article_005;
        public TextView txt_designaton_005;
        public TextView txt_taux_remise_005;
        public TextView txt_ttc_005;



        //006
        public TextView txt_code_article_006;
        public TextView txt_designaton_006;
        public TextView txt_taux_remise_006;
        public TextView txt_ttc_006;




     /*   public LinearLayout ll_livraison ;
        public LinearLayout ll_retour ;
        public LinearLayout ll_gratuit;
        public LinearLayout ll_caisse ;
        */


        public ViewHolder(View itemView) {
            super(itemView);

            card_item_personnel = (CardView) itemView.findViewById(R.id.card_personel);


            txt_raison_social  = (TextView)  itemView.findViewById(R.id.txt_raison_social)  ;
            txt_code_article_001 = (TextView) itemView.findViewById(R.id.txt_code_article_001);
            txt_designaton_001   = (TextView) itemView.findViewById(R.id.txt_designation_001);
            txt_taux_remise_001  = (TextView) itemView.findViewById(R.id.txt_taux_remise_001);
            txt_ttc_001 = (TextView) itemView.findViewById(R.id.txt_ttc_001);



            txt_code_article_002 = (TextView) itemView.findViewById(R.id.txt_code_article_002);
            txt_designaton_002   = (TextView) itemView.findViewById(R.id.txt_designation_002);
            txt_taux_remise_002  = (TextView) itemView.findViewById(R.id.txt_taux_remise_002);
            txt_ttc_002 = (TextView) itemView.findViewById(R.id.txt_ttc_002);



            txt_code_article_003 = (TextView) itemView.findViewById(R.id.txt_code_article_003);
            txt_designaton_003   = (TextView) itemView.findViewById(R.id.txt_designation_003);
            txt_taux_remise_003 = (TextView) itemView.findViewById(R.id.txt_taux_remise_003);
            txt_ttc_003 = (TextView) itemView.findViewById(R.id.txt_ttc_003);




            txt_code_article_004 = (TextView) itemView.findViewById(R.id.txt_code_article_004);
            txt_designaton_004   = (TextView) itemView.findViewById(R.id.txt_designation_004);
            txt_taux_remise_004 = (TextView) itemView.findViewById(R.id.txt_taux_remise_004);
            txt_ttc_004 = (TextView) itemView.findViewById(R.id.txt_ttc_004);




            txt_code_article_005 = (TextView) itemView.findViewById(R.id.txt_code_article_005);
            txt_designaton_005   = (TextView) itemView.findViewById(R.id.txt_designation_005);
            txt_taux_remise_005 = (TextView) itemView.findViewById(R.id.txt_taux_remise_005);
            txt_ttc_005 = (TextView) itemView.findViewById(R.id.txt_ttc_005);





            txt_code_article_006 = (TextView) itemView.findViewById(R.id.txt_code_article_006);
            txt_designaton_006   = (TextView) itemView.findViewById(R.id.txt_designation_006);
            txt_taux_remise_006 = (TextView) itemView.findViewById(R.id.txt_taux_remise_006);
            txt_ttc_006 = (TextView) itemView.findViewById(R.id.txt_ttc_006);



            /*ll_livraison = (LinearLayout) itemView.findViewById(R.id.ll_livraison);
            ll_retour = (LinearLayout) itemView.findViewById(R.id.ll_retour);
            ll_gratuit = (LinearLayout) itemView.findViewById(R.id.ll_gratuit);
            ll_caisse = (LinearLayout) itemView.findViewById(R.id.ll_caisse);*/




        }

    }

}
