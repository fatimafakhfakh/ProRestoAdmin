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
import com.example.prorestoadmin.model.draft.DetailDraftReglementClient;
import com.example.prorestoadmin.model.draft.DraftReglement;
import com.example.prorestoadmin.model.ClientRappelPaiment;
import com.example.prorestoadmin.model.PieceRappelPaiment;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class DetailSoldeClientRVAdapter extends RecyclerView.Adapter<DetailSoldeClientRVAdapter.ViewHolder> {


    private  Activity activity;
    private final ArrayList<ClientRappelPaiment> list_client_rappel_paiement ;//= new ArrayList<>();
    DecimalFormat  numberFormat  = new DecimalFormat("0.000") ;
    public static String login;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat heureFormat = new SimpleDateFormat("HH:mm");

    public DetailSoldeClientRVAdapter(Activity activity, ArrayList<ClientRappelPaiment> list_client_rappel_paiement) {

        this.activity = activity;
        this.list_client_rappel_paiement = list_client_rappel_paiement;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_solde_client , parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final ClientRappelPaiment clientRappelPaiment = list_client_rappel_paiement.get(position);

        holder.txt_raison_social.setText(clientRappelPaiment.getRaisonSocial());
        holder.txt_solde_client.setText(numberFormat.format(clientRappelPaiment.getSoldeClient())+" DT");



        holder.txt_total_montant.setText(numberFormat.format(clientRappelPaiment.getTotalMontant())+" DT");
        holder.txt_total_reglement.setText(numberFormat.format(clientRappelPaiment.getTotalReglement())+" DT");
        holder.txt_total_reste_a_payer.setText(numberFormat.format(clientRappelPaiment.getTotalResteAPayer())+" DT");



      /*  holder.txt_libelle.setText("Retour pour Client");
          holder.txt_detail.setText("Détail Retour");*/


        int nbr_ligne = clientRappelPaiment.getListRappelPaiment().size();
        int height    = nbr_ligne * 70;


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  height );

        params.height = height  ;
        holder.lv_list_piece.setLayoutParams(params) ;

        Detail_solde_Adapter1 adapter1 = new Detail_solde_Adapter1(clientRappelPaiment.getListRappelPaiment());
        holder.lv_list_piece.setAdapter(adapter1);



    }



    @Override
    public int getItemCount() {
        Log.e("size" ,""+list_client_rappel_paiement.size()) ;
        return list_client_rappel_paiement.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {


        public TextView txt_raison_social  ;
        public   TextView   txt_solde_client;

        public ListView lv_list_piece    ;


        public   TextView   txt_total_montant ;
        public   TextView   txt_total_reglement  ;
        public   TextView   txt_total_reste_a_payer ;


        public ViewHolder(View itemView) {
            super(itemView);

            //  item_darft = (CardView) itemView.findViewById(R.id.item_darft);

            txt_raison_social = (TextView) itemView.findViewById(R.id.txt_raison_social);
            txt_solde_client  = (TextView) itemView.findViewById(R.id.txt_tt_solde_client);

            lv_list_piece = (ListView) itemView.findViewById(R.id.lv_list_piece);


            txt_total_montant       = (TextView) itemView.findViewById(R.id.txt_tot_montant);
            txt_total_reglement     = (TextView) itemView.findViewById(R.id.txt_tot_reg);
            txt_total_reste_a_payer = (TextView) itemView.findViewById(R.id.txt_tot_reste_a_payer);


        }

    }

    public class Detail_solde_Adapter1 extends ArrayAdapter<PieceRappelPaiment> {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        DecimalFormat  df  = new DecimalFormat("0.000") ;

        private final ArrayList<PieceRappelPaiment> list_PieceRappelPaiment;

        public Detail_solde_Adapter1(ArrayList<PieceRappelPaiment> list_PieceRappelPaiment) {
            super(activity, R.layout.item_detail_piece, list_PieceRappelPaiment);

            this.list_PieceRappelPaiment = list_PieceRappelPaiment;

            Log.e("adapter", "Constructor" + list_PieceRappelPaiment.toString());

        }

        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.item_detail_piece, null, true);

            PieceRappelPaiment pieceRappelPaiment = list_PieceRappelPaiment.get(position);

           LinearLayout  item_piece= (LinearLayout) rowView.findViewById(R.id.item_piece);


            TextView txt_date_piece = (TextView) rowView.findViewById(R.id.txt_date_piece);
            TextView txt_num_piece = (TextView) rowView.findViewById(R.id.txt_num_piece);
            TextView txt_montant_piece = (TextView) rowView.findViewById(R.id.txt_montant);

            TextView txt_reglement_piece = (TextView) rowView.findViewById(R.id.txt_reglement);
            TextView txt_reste_a_payer_piece = (TextView) rowView.findViewById(R.id.txt_reste_a_payer);
            TextView txt_recu_draft = (TextView) rowView.findViewById(R.id.txt_recu_draft);

            //txt_recu_draft


            txt_date_piece.setText(sdf.format(pieceRappelPaiment.getDatePiece()));
            txt_num_piece.setText(pieceRappelPaiment.getNumeroPiece());
            txt_montant_piece.setText(  df .format(pieceRappelPaiment.getMontant())  );
            txt_reglement_piece.setText(df .format(pieceRappelPaiment.getReglement())  );
            txt_reste_a_payer_piece.setText(df .format(pieceRappelPaiment.getResteAPayer())  );
            txt_recu_draft.setText(df .format(pieceRappelPaiment.getRecuDraft())  );

            if (pieceRappelPaiment.getRecuDraft()!=0)
            {
                item_piece.setBackgroundColor(activity.getResources().getColor(R.color.back_piece_drafte));
            }


            return rowView;

        }

    }

}
