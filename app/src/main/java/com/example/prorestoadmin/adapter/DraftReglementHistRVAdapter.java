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
import com.example.prorestoadmin.model.BonRetourVente;
import com.example.prorestoadmin.model.LigneBonRetourVente;
import com.example.prorestoadmin.model.draft.DetailDraftReglementClient;
import com.example.prorestoadmin.model.draft.DraftReglement;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class DraftReglementHistRVAdapter extends RecyclerView.Adapter<DraftReglementHistRVAdapter.ViewHolder> {


    private  Activity activity;
    private final ArrayList<DraftReglement> list_DraftReg  ;//= new ArrayList<>();
    DecimalFormat  numberFormat  = new DecimalFormat("0.000") ;
    public static String login;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat heureFormat = new SimpleDateFormat("HH:mm");

    public DraftReglementHistRVAdapter(Activity activity, ArrayList<DraftReglement> list_DraftReg) {

        this.activity = activity;
        this.list_DraftReg = list_DraftReg;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_draft_ref_hist, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final DraftReglement draftReglement = list_DraftReg.get(position);

        holder.txt_num_darft.setText(draftReglement.getNumeroDraftClient());
        holder.txt_date_darft.setText(df.format(draftReglement.getHeureCreation()));
        holder.txt_depot_source.setText(draftReglement.getCodeClient());
        holder.txt_depot_destination.setText(draftReglement.getRaisonSociale());
        holder.txt_etat.setText(draftReglement.getEtat());

      /*  holder.txt_libelle.setText("Retour pour Client");
        holder.txt_detail.setText("Détail Retour");*/


        int nbr_ligne = draftReglement.getDetailDraftReg().size();
        int height    = nbr_ligne * 60;


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  height );

        params.height = height  ;
        holder.lv_list_detail_draft_reg.setLayoutParams(params) ;

        Detail_Draft_Adapter1 detail_bt_adapter1 = new Detail_Draft_Adapter1(draftReglement.getDetailDraftReg());
        holder.lv_list_detail_draft_reg.setAdapter(detail_bt_adapter1);


        //holder.txt_date_darft .setText(heureFormat.format(draftReglement.getHeureCreation()));

        holder.txt_montant_draft .setText(numberFormat.format(draftReglement.getMontant())+" DT");

    }



    @Override
    public int getItemCount() {
        Log.e("size" ,""+list_DraftReg.size()) ;
        return list_DraftReg.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {


        public TextView txt_num_darft  ;
        public TextView txt_date_darft  ;

        public TextView txt_depot_source       ;
        public TextView txt_depot_destination  ;

        public TextView txt_etat   ;
        public ListView lv_list_detail_draft_reg    ;

        public CardView item_darft ;

        public   TextView  txt_libelle  ;
        public   TextView  txt_detail  ;

        public   TextView   txt_montant_draft ;

        public ViewHolder(View itemView) {
            super(itemView);

              item_darft = (CardView) itemView.findViewById(R.id.item_darft);

              txt_num_darft = (TextView) itemView.findViewById(R.id.txt_num_draft);
              txt_date_darft  = (TextView) itemView.findViewById(R.id.txt_date_draft);
              txt_depot_source       = (TextView) itemView.findViewById(R.id.txt_depot_source);
              txt_depot_destination     = (TextView) itemView.findViewById(R.id.txt_depot_destination);
              txt_etat = (TextView) itemView.findViewById(R.id.txt_etat);
              lv_list_detail_draft_reg = (ListView) itemView.findViewById(R.id.lv_list_detail_draft);


            txt_libelle  = (TextView) itemView.findViewById(R.id.txt_libelle);
            txt_detail  = (TextView) itemView.findViewById(R.id.txt_detail_libelle);

            txt_montant_draft  = (TextView)itemView.findViewById(R.id.txt_montant_draft);


        }

    }

    public class Detail_Draft_Adapter1 extends ArrayAdapter<DetailDraftReglementClient> {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        DecimalFormat  df  = new DecimalFormat("0.000") ;

        private final ArrayList<DetailDraftReglementClient> list_detail_darft_reg_client;

        public Detail_Draft_Adapter1(ArrayList<DetailDraftReglementClient> list_detail_darft_reg_client) {
            super(activity, R.layout.item_detail_darft, list_detail_darft_reg_client);

            this.list_detail_darft_reg_client = list_detail_darft_reg_client;

            Log.e("adapter", "Constructor" + list_detail_darft_reg_client.toString());

        }

        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.item_detail_darft, null, true);

            DetailDraftReglementClient detailDraftReglementClient = list_detail_darft_reg_client.get(position);

            TextView txt_num_piece = (TextView) rowView.findViewById(R.id.txt_num_piece);
            TextView txt_montant_piece = (TextView) rowView.findViewById(R.id.txt_montant_piece);
            TextView txt_montant_recu = (TextView) rowView.findViewById(R.id.txt_recu_draft);



            txt_num_piece.setText(detailDraftReglementClient.getNumeroPiece());
            txt_montant_piece.setText(  df .format(detailDraftReglementClient.getMontantPieceTTC()) + " DT");
            txt_montant_recu.setText(df .format(detailDraftReglementClient.getTotalRecuDraft()) + " DT");
            return rowView;

        }

    }

}
