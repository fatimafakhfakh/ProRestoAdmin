package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Personnel;
import com.example.prorestoadmin.rapportPersonnel.RapportActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PersonnelAdapterRV extends RecyclerView.Adapter<PersonnelAdapterRV.ViewHolder> {


    private final Activity activity;

    private final ArrayList<Personnel> listPersonnel;
    private final Date date_etat;


    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("0.000");


    public PersonnelAdapterRV(Activity activity, ArrayList<Personnel> listPersonnel, Date date_etat) {

        this.activity = activity;
        this.listPersonnel = listPersonnel;
        this.date_etat = date_etat;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_etat_personnel, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Personnel personnel = listPersonnel.get(position);

        holder.txt_personnel.setText(personnel.getNomResponsable());  //+
        holder.txt_fonction.setText("(" + personnel.getFonction() + ")");

        holder.txt_livraison.setText(personnel.getQTLivraison() + "");
        holder.txt_ttc_livraison.setText(formatter.format(personnel.getTTCLivraison()) + " DT");
        holder.txt_retour.setText(personnel.getQTRetour() + "");
        holder.txt_ttc_retour.setText(formatter.format(personnel.getTTCRetour()) + " DT");
        holder.txt_gratuit.setText(personnel.getQTGratuite() + "");


        holder.txt_reglement.setText(formatter.format(personnel.getTotalDraftRèglement()) + "Dt");

        holder.btn_detail_qt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                PopupMenu pw = new PopupMenu(activity, holder.btn_detail_qt);
                pw.inflate(R.menu.menu_detail_article);


                ListQtArticleDetailTask listQtArticleDetailTask = new ListQtArticleDetailTask( activity  ,pw ,date_etat,personnel.getNomUtilisateur()  ) ;
                listQtArticleDetailTask.execute() ;


            }
        });




        holder.card_item_personnel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetailLivraison = new Intent(activity, RapportActivity.class);
                // Intent  toDetailLivraison  = new Intent(activity , DetailLivraisonActivity.class) ;
                toDetailLivraison.putExtra("cle_nom_utilisateur", personnel.getNomUtilisateur());
                toDetailLivraison.putExtra("cle_nom_responsable", personnel.getNomResponsable());
                toDetailLivraison.putExtra("cle_qt_livraison", personnel.getQTLivraison());
                toDetailLivraison.putExtra("cle_date_etat", df.format(date_etat));
                activity.startActivity(toDetailLivraison);

            }
        });


    }


    @Override
    public int getItemCount() {
        Log.e("size", "" + listPersonnel.size());
        return listPersonnel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView card_item_personnel;
        public TextView txt_livraison;
        public TextView txt_ttc_livraison;
        public TextView txt_retour;
        public TextView txt_ttc_retour;
        public TextView txt_gratuit;

        public TextView txt_personnel;
        public TextView txt_fonction;

        public ImageView img_caisse;
        public TextView txt_caisse_libelle;
        public TextView txt_reglement;

        public ImageView btn_detail_qt;

     /*   public LinearLayout ll_livraison ;
        public LinearLayout ll_retour ;
        public LinearLayout ll_gratuit;
        public LinearLayout ll_caisse ;*/


        public ViewHolder(View itemView) {
            super(itemView);

            card_item_personnel = (CardView) itemView.findViewById(R.id.card_personel);
            txt_livraison = (TextView) itemView.findViewById(R.id.txt_livraison);
            txt_ttc_livraison = (TextView) itemView.findViewById(R.id.txt_livraison_ttc);
            txt_retour = (TextView) itemView.findViewById(R.id.txt_retour);
            txt_ttc_retour = (TextView) itemView.findViewById(R.id.txt_retour_ttc);
            txt_gratuit = (TextView) itemView.findViewById(R.id.txt_gratuit);

            img_caisse = (ImageView) itemView.findViewById(R.id.img_caisse);
            txt_caisse_libelle = (TextView) itemView.findViewById(R.id.txt_caisse_libelle);
            txt_reglement = (TextView) itemView.findViewById(R.id.txt_caisse);
            txt_personnel = (TextView) itemView.findViewById(R.id.txt_personnel);
            txt_fonction = (TextView) itemView.findViewById(R.id.txt_fonction);


            btn_detail_qt = (ImageView) itemView.findViewById(R.id.btn_detail_qt);

            /*ll_livraison = (LinearLayout) itemView.findViewById(R.id.ll_livraison);
            ll_retour = (LinearLayout) itemView.findViewById(R.id.ll_retour);
            ll_gratuit = (LinearLayout) itemView.findViewById(R.id.ll_gratuit);
            ll_caisse = (LinearLayout) itemView.findViewById(R.id.ll_caisse);*/


        }

    }


    public class ListQtArticleDetailTask extends AsyncTask<String, String, String> {

        String res;

        Activity activity ;
        PopupMenu pw  ;
        Date date_livraison ;
        String  NomUtilisateur;

        ConnectionClass connectionClass ;
        String user, password, base, ip ;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy") ;

        ArrayList <String>   listArticleEnDetail  = new ArrayList<>() ;


        public ListQtArticleDetailTask (Activity  activity, PopupMenu pw  , Date date_livraison , String  NomUtilisateur  ) {
            this.activity = activity;
            this.pw=pw ;
            this.date_livraison=date_livraison  ;
            this.NomUtilisateur=NomUtilisateur  ;

            SharedPreferences pref = activity.getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
            SharedPreferences.Editor edt = pref.edit();
            user = pref.getString("user", user);
            ip = pref.getString("ip", ip);
            password = pref.getString("password", password);
            base = pref.getString("base", base);
            connectionClass = new ConnectionClass();



        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pw.getMenu(). add("chargement  ...") ;
            pw.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
                Log.e("con", "" + con);
                if (con == null) {
                    res = "Check Your Internet Access!";
                } else {

                    String query = " \n" +
                            " select distinct CodeArticle ,SUM(Quantite)as  qtParAricle  from LigneBonLivraisonVente    \n" +
                            " inner join BonLivraisonVente  on BonLivraisonVente.NumeroBonLivraisonVente  = LigneBonLivraisonVente.NumeroBonLivraisonVente\n" +
                            " where BonLivraisonVente.UtilisateurIntervenant  =  '"+NomUtilisateur+"'\n" +
                            " and    DateBonLivraisonVente  =  '"+sdf.format(date_livraison)+"'\n" +
                            " and NumeroEtat <> 'E00' \n" +
                            " GROUP bY  CodeArticle "  ;


                    Log.e("query_depot", query );

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {

                        String CodeArticle = rs.getString("CodeArticle");
                        int qtParAricle = rs.getInt("qtParAricle");


                        listArticleEnDetail.add(CodeArticle+"  qt : "+qtParAricle) ;

                    }
                }
                con.close();

            } catch (Exception ex) {

                res = ex.getMessage();
                Log.e("ERROR_depot", res.toString());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pw.getMenu().clear();
            for (String  label  : listArticleEnDetail)
            {
                pw.getMenu(). add(label) ;
            }



            pw.show();


        }


    }
}
