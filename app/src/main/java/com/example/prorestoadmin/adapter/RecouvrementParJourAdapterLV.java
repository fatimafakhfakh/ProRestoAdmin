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
import com.example.prorestoadmin.model.RecouvrementParJour;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.core.content.ContextCompat;


public class RecouvrementParJourAdapterLV extends ArrayAdapter<RecouvrementParJour> {


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    DecimalFormat formatter = new DecimalFormat("0.000");
    private final Activity activity;
    private final ArrayList<RecouvrementParJour> listRecouvrementParJour;

    public RecouvrementParJourAdapterLV(Activity activity, ArrayList<RecouvrementParJour> listRecouvrementParJour) {
        super(activity, R.layout.item_recouvrement_du_jour  , listRecouvrementParJour);
        // TODO Auto-generated constructor stub
        this.activity     = activity;
        this.listRecouvrementParJour = listRecouvrementParJour;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        Context context = parent.getContext();
        View rowView = inflater.inflate (R.layout.item_recouvrement_du_jour  , null, true);

        RecouvrementParJour recouvrementParJour = listRecouvrementParJour.get(position);



        CircularProgressBar cpb_taux_recouvrement = rowView.findViewById(R.id.cpb_taux_recouvrement_du_jour);
        TextView txt_taux_recouvrement = rowView.findViewById(R.id.txt_taux_recouvrement);
        TextView txt_tot_vente = rowView.findViewById(R.id.txt_total_vente_ttc);
        TextView txt_tot_retour = rowView.findViewById(R.id.txt_total_retour_ttc);
        TextView txt_total_reg_j = rowView.findViewById(R.id.txt_total_reg_j);
          TextView txt_tot_recouv = rowView.findViewById(R.id.txt_total_recouvrement);
        TextView txt_tot_credit = rowView.findViewById(R.id.txt_total_credit);

        TextView  txt_date_du_jour = rowView.findViewById(R.id.txt_date_du_jour);




        txt_date_du_jour.setText(sdf.format(recouvrementParJour.getDatevente()));
      //  double taux_recouvrement  = totalReg_j /(TotalVente-TotalRetour)   *100  ;

        DecimalFormat  decF  = new DecimalFormat("0.000") ;
        txt_taux_recouvrement.setText(decF.format(recouvrementParJour.getTaux_recouvrement()) +" %");


        cpb_taux_recouvrement.setBackgroundColor(ContextCompat.getColor(activity, R.color.tab_background_unselected));
        cpb_taux_recouvrement.setProgressBarWidth(activity.getResources().getDimension(R.dimen.progressBarWidth));
        cpb_taux_recouvrement.setBackgroundProgressBarWidth(activity.getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500; // 2500ms = 2,5s

        cpb_taux_recouvrement.setProgressWithAnimation((float)(recouvrementParJour.getTaux_recouvrement()) , animationDuration); // Default duration = 1500ms
        cpb_taux_recouvrement.setColor(ContextCompat.getColor(activity, R.color.color_g_7));
        // circularProgressBar.setCol


        txt_tot_vente.setText(decF.format(recouvrementParJour.getTotalVente())+ "");
        txt_tot_retour.setText(decF.format(recouvrementParJour.getTotalRetour())+ "");
        txt_total_reg_j.setText(decF.format(recouvrementParJour.getToatlRegJ())+ "");

        txt_tot_recouv.setText(decF.format(recouvrementParJour.getTotalRecouvrement())+ "");



        txt_tot_credit.setText(decF.format(recouvrementParJour.getResteACredit())+ "");

        return rowView;

    }




}


