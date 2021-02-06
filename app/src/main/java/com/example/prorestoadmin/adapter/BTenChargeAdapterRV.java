package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.BonTransfert;
import com.example.prorestoadmin.model.FicheArticle;
import com.example.prorestoadmin.model.LigneBonTransfert;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BTenChargeAdapterRV extends RecyclerView.Adapter<BTenChargeAdapterRV.ViewHolder> {


    private final Activity activity;

    private final ArrayList<BonTransfert> listBonTransfert;


    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("0.000");


    public BTenChargeAdapterRV(Activity activity, ArrayList<BonTransfert> listBonTransfert) {

        this.activity = activity;
        this.listBonTransfert = listBonTransfert;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transfert_en_charge, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final BonTransfert bonTransfert = listBonTransfert.get(position);

        holder.card_item_bt_en_charge.setVisibility(View.VISIBLE);
        holder.txt_num_transfert.setText(bonTransfert.getNumeroBonTransfert());

        Detail_bt_Adapter1 detail_bt_adapter1 = new Detail_bt_Adapter1(bonTransfert.getListLigneBonTransfert());
        holder.lv_list_detail_transfert.setAdapter(detail_bt_adapter1);


    }


    @Override
    public int getItemCount() {
        Log.e("size", "" + listBonTransfert.size());
        return listBonTransfert.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public CardView card_item_bt_en_charge;
        public TextView txt_num_transfert;
        public ListView lv_list_detail_transfert;


        public ViewHolder(View itemView) {
            super(itemView);

            card_item_bt_en_charge = (CardView) itemView.findViewById(R.id.card_transfert_charge);
            txt_num_transfert = (TextView) itemView.findViewById(R.id.txt_num_bt);
            lv_list_detail_transfert = (ListView) itemView.findViewById(R.id.lv_list_detail_transfert);


        }

    }


    public class Detail_bt_Adapter1 extends ArrayAdapter<LigneBonTransfert> {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        private final ArrayList<LigneBonTransfert> list_ligne_bon_transfert;

        public Detail_bt_Adapter1(ArrayList<LigneBonTransfert> list_ligne_bon_transfert) {
            super(activity, R.layout.item_detail_bt, list_ligne_bon_transfert);

            this.list_ligne_bon_transfert = list_ligne_bon_transfert;

            Log.e("adapter", "Constructor" + list_ligne_bon_transfert.toString());

        }

        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.item_detail_bt, null, true);

            LigneBonTransfert ligneBonTransfert = list_ligne_bon_transfert.get(position);

            TextView txt_designation = (TextView) rowView.findViewById(R.id.txt_designation_article);
            TextView txt_qt = (TextView) rowView.findViewById(R.id.txt_qt);

            txt_designation.setText(ligneBonTransfert.getDesignationArticle());
            txt_qt.setText(ligneBonTransfert.getQuantite() + "");

            return rowView;

        }

    }

}
