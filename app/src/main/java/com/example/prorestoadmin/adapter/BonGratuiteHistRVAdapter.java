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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.BonGratuiteVente;
import com.example.prorestoadmin.model.BonRetourVente;
import com.example.prorestoadmin.model.LigneBonGratuite;
import com.example.prorestoadmin.model.LigneBonRetourVente;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class BonGratuiteHistRVAdapter extends RecyclerView.Adapter<BonGratuiteHistRVAdapter.ViewHolder> {


    private  Activity activity;
    private final ArrayList<BonGratuiteVente> list_BonGratuite   ;//= new ArrayList<>();
    DecimalFormat  numberFormat  = new DecimalFormat("0.000") ;
    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat heureFormat = new SimpleDateFormat("HH:mm");

    public BonGratuiteHistRVAdapter(Activity activity, ArrayList<BonGratuiteVente> list_BonGratuite) {

        this.activity = activity;
        this.list_BonGratuite = list_BonGratuite;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bon_gratuite, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final BonGratuiteVente bonGratuiteVente = list_BonGratuite.get(position);

        holder.txt_num_bg.setText(bonGratuiteVente.getNumeroBonGratuiteVente());
        holder.txt_date_bon_gratuite.setText(df.format(bonGratuiteVente.getHeureCreation()));
        holder.txt_code_client.setText(bonGratuiteVente.getCodeClient());
        holder.txt_raison.setText(bonGratuiteVente.getRaisonSociale());
        holder.txt_etat.setText(bonGratuiteVente.getNumeroEtat());



       holder.  txt_etablie_par.setText("Etablie par : "+bonGratuiteVente.getNomUtilisateur());
        int nbr_ligne = bonGratuiteVente.getList_ligne_bon_gratuite().size();
        int height    = nbr_ligne * 60;


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  height );

        params.height = height  ;
        holder.lv_list_detail_gratuite.setLayoutParams(params) ;

        Detail_BR_Adapter1 detail_bt_adapter1 = new Detail_BR_Adapter1(bonGratuiteVente.getList_ligne_bon_gratuite());
        holder.lv_list_detail_gratuite.setAdapter(detail_bt_adapter1);





    }



    @Override
    public int getItemCount() {
        Log.e("size" ,""+list_BonGratuite.size()) ;
        return list_BonGratuite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {


        public TextView txt_num_bg  ;
        public TextView txt_date_bon_gratuite  ;

        public TextView txt_code_client  ;
        public TextView txt_raison ;

        public TextView txt_etat   ;
        public ListView lv_list_detail_gratuite   ;

        public CardView item_bon_gratuite   ;



        public TextView  txt_etablie_par ;

        public ViewHolder(View itemView) {
            super(itemView);

            item_bon_gratuite = (CardView) itemView.findViewById(R.id.item_bon_gratuite);

            txt_num_bg = (TextView) itemView.findViewById(R.id.txt_num_bg);
            txt_date_bon_gratuite  = (TextView) itemView.findViewById(R.id.txt_date_bon_gratuit);
            txt_code_client       = (TextView) itemView.findViewById(R.id.txt_code_client);
            txt_raison     = (TextView) itemView.findViewById(R.id.txt_raison_social);

              txt_etat = (TextView) itemView.findViewById(R.id.txt_etat);
            lv_list_detail_gratuite = (ListView) itemView.findViewById(R.id.lv_list_detail_gratuite);
            txt_etablie_par= (TextView) itemView.findViewById(R.id.txt_etablie_par);




        }

    }

    public class Detail_BR_Adapter1 extends ArrayAdapter<LigneBonGratuite> {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        private final ArrayList<LigneBonGratuite> list_ligne_bon_gratuite ;

        public Detail_BR_Adapter1(ArrayList<LigneBonGratuite> list_ligne_bon_gratuite) {
            super(activity, R.layout.item_detail_bt, list_ligne_bon_gratuite);

            this.list_ligne_bon_gratuite = list_ligne_bon_gratuite;

            Log.e("adapter", "Constructor" + list_ligne_bon_gratuite.toString());

        }

        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.item_detail_bt, null, true);

            LigneBonGratuite ligneBonGratuite  = list_ligne_bon_gratuite.get(position);

            TextView txt_designation = (TextView) rowView.findViewById(R.id.txt_designation_article);
            TextView txt_qt = (TextView) rowView.findViewById(R.id.txt_qt);

            txt_designation.setText(ligneBonGratuite.getDesignationArticle());
            txt_qt.setText(ligneBonGratuite.getQuantite() + "");

            return rowView;

        }

    }

}
