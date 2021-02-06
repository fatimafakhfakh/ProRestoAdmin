package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.MouvementArticle;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class TotaliteProductionStockRVAdapter extends RecyclerView.Adapter<TotaliteProductionStockRVAdapter.ViewHolder> {


    private  Activity activity;
    private final ArrayList<MouvementArticle> listMouvement ;//= new ArrayList<>();
    DecimalFormat  numberFormat  = new DecimalFormat("0.000") ;
    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public TotaliteProductionStockRVAdapter(Activity activity, ArrayList<MouvementArticle> listMouvement) {

        this.activity      = activity;
        this.listMouvement = listMouvement ;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_production_mouvement, parent, false);

        return new ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    final     MouvementArticle  mouvementArticle  = listMouvement.get(position)  ;


        holder.txt_qt_entree .setText(mouvementArticle.getQtEntree()+"");
        holder.txt_qt_sortie .setText(mouvementArticle.getQtSortie()+"");


        holder.txt_article .setText(mouvementArticle.getCodeArticle()) ;
        holder.txt_qt_prod .setText( mouvementArticle.getQTProd() +"") ;



        holder.btn_detail_mouvement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              final   Dialog myDialog = new Dialog(activity);
                TextView txtclose;

                TextView  txt_code_article  ,  txt_designation_article  ;
                TextView txt_qt_transfert_sortie  , txt_qt_livraison , txt_qt_gratuit ,  txt_qt_transert_entre  , txt_qt_retour  ;

                Button btnFollow;
                myDialog.setContentView(R.layout.pop_up_detail_mouvement_article);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);


                txt_code_article =(TextView) myDialog.findViewById(R.id.txt_code_article);
                txt_designation_article =(TextView) myDialog.findViewById(R.id.txt_designation_article);

                txt_qt_transfert_sortie =(TextView) myDialog.findViewById(R.id.txt_qt_sortie_transfert);
                txt_qt_livraison =(TextView) myDialog.findViewById(R.id.txt_qt_livraison);
                txt_qt_gratuit =(TextView) myDialog.findViewById(R.id.txt_qt_gratuit);


                txt_qt_transert_entre =(TextView) myDialog.findViewById(R.id.txt_qt_entre_transfert  );
                txt_qt_retour =(TextView) myDialog.findViewById(R.id.txt_qt_retour);


                txt_code_article.setText(mouvementArticle.getCodeArticle());
                txt_designation_article.setText(mouvementArticle.getDesignationArticle());

                txt_qt_transfert_sortie.setText(mouvementArticle.getQTSortieTransfert()+"");
                txt_qt_transert_entre.setText(mouvementArticle.getQTEntreeTransfert()+"");

                txt_qt_livraison.setText(mouvementArticle.getQTLivraison()+"");
                txt_qt_retour.setText(mouvementArticle.getQTRetour()+"");

                txt_qt_gratuit.setText(mouvementArticle.getQTGratuit()+"");

                txtclose.setText("X");
                btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);



                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                }    );


                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

            }
        });


        if (position % 2 ==0 )  // paire
        {
            holder.card_article_prod.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }

        if( position  ==  (listMouvement.size()-1)  )
        {
            holder.card_article_prod.setBackgroundColor(Color.parseColor("#EDFCF0F0"));
            holder.btn_detail_mouvement.setVisibility(View.INVISIBLE);
        }


    }


    @Override
    public int getItemCount() {
        Log.e("size" ,""+listMouvement.size()) ;
        return listMouvement.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {

        public   CardView   card_article_prod ;
        public TextView  txt_article ;
        public TextView  txt_qt_prod  ;

        public TextView  txt_qt_entree  ;
        public TextView  txt_qt_sortie  ;

        public ImageView btn_detail_mouvement ;

        public ViewHolder(View itemView) {
            super(itemView);

            card_article_prod = (CardView)  itemView.findViewById(R.id.card_article_prod) ;
            txt_article     = (TextView) itemView.findViewById(R.id.txt_article);
            txt_qt_prod = (TextView) itemView.findViewById(R.id.txt_qt_prod);

            txt_qt_entree= (TextView) itemView.findViewById(R.id.txt_qt_entree);
            txt_qt_sortie= (TextView) itemView.findViewById(R.id.txt_qt_sortie);

            btn_detail_mouvement = (ImageView) itemView.findViewById(R.id.btn_detail_mouvement);

        }

    }




}
