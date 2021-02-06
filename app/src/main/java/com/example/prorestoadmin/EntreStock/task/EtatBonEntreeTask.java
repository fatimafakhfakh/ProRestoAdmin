package com.example.prorestoadmin.EntreStock.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.prorestoadmin.EntreStock.EtatEntreStockActivity;
import com.example.prorestoadmin.adapter.BonEntreeRVAdapter;
import com.example.prorestoadmin.adapter.TotaliteProductionStockAdapter;
import com.example.prorestoadmin.adapter.TotaliteProductionStockRVAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.ArticleStock;
import com.example.prorestoadmin.model.BonEntre;
import com.example.prorestoadmin.model.LigneBonEntre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


public class EtatBonEntreeTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;

    Date date_debut, date_fin;
    RecyclerView rv_list_bon_entree;
    ProgressBar progressBar;
    ConnectionClass connectionClass;
    String user, password, base, ip;

    ArrayList<BonEntre> listBonEntree = new ArrayList<>();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat dtfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    ArrayList<ArticleStock> list_tot_article;
    ArrayList<String> listCodeArticleDistinct  ;
    int  tot_qt  ;

    public EtatBonEntreeTask(Activity activity, Date date_debut, Date date_fin, RecyclerView rv_list_bon_entree, ProgressBar progressBar) {
        this.activity = activity;
        this.rv_list_bon_entree = rv_list_bon_entree;
        this.progressBar = progressBar;

        this.date_debut = date_debut;
        this.date_fin = date_fin;

        SharedPreferences pref = activity.getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        user = pref.getString("user", user);
        ip = pref.getString("ip", ip);
        password = pref.getString("password", password);
        base = pref.getString("base", base);
        connectionClass = new ConnectionClass();

        list_tot_article = new ArrayList<>();
        listCodeArticleDistinct = new ArrayList<>();
        tot_qt = 0  ;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
            Log.e("con", "" + con);
            if (con == null) {
                res = "Check Your Internet Access!";
            } else {

                String query = " select  \n" +
                        "     NumeroBonEntrer ,\n" +
                        "     HeureCreation as DateBonEntrer ,\n" +
                        "     BonEntrer.CodeDepot ,\n" +
                        "     (select  libelle from Depot where CodeDepot = BonEntrer.CodeDepot) as Depot  ,\n" +
                        "     BonEntrer.NumeroEtat ,Etat.Libelle , NomUtilisateur\n" +
                        "    \n" +
                        "    from BonEntrer   \n" +
                        "    \n" +
                        "    INNER  JOIN Etat on  Etat.NumeroEtat = BonEntrer.NumeroEtat\n" +
                        "    INNER  JOIN Depot on  Depot.CodeDepot = BonEntrer.CodeDepot\n";


                String CONDITION_DATE = "  where CONVERT (date  , DateBonEntrer )   between   '" + df.format(date_debut) + "'  and   '" + df.format(date_fin) + "'  \n";


                String CONDITION_ORDER = "  order by NumeroBonEntrer  DESC   ";
                query = query + CONDITION_DATE + CONDITION_ORDER;


                Log.e("query_bon_entrer", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {


                    String NumeroBonEntrer = rs.getString("NumeroBonEntrer");
                    Date DateBonEntrer = dtfSQL.parse(rs.getString("DateBonEntrer"));
                    String CodeDepot = rs.getString("CodeDepot");
                    String Depot = rs.getString("Depot");

                    String NumeroEtat = rs.getString("NumeroEtat");
                    String Libelle = rs.getString("Libelle");
                    String NomUtilisateur = rs.getString("NomUtilisateur");


                    String query_detail_transfert = " select  CodeArticle ,  DesignationArticle  , Quantite from LigneBonEntrer    where NumeroBonEntrer  = '" + NumeroBonEntrer + "' ";
                    Log.e("query_detail_entrer", query_detail_transfert);

                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query_detail_transfert);
                    ArrayList<LigneBonEntre> listLigneBonEntree = new ArrayList<>();

                    while (rs2.next()) {

                        String CodeArticle = rs2.getString("CodeArticle");
                        String DesignationArticle = rs2.getString("DesignationArticle");
                        int Quantite = rs2.getInt("Quantite");

                        LigneBonEntre ligneBonEntree = new LigneBonEntre(CodeArticle, DesignationArticle, Quantite);
                        listLigneBonEntree.add(ligneBonEntree);

                        tot_qt=tot_qt+Quantite ;

                        if (!listCodeArticleDistinct.contains(CodeArticle)) {
                            listCodeArticleDistinct.add(CodeArticle);
                            list_tot_article.add(new ArticleStock(CodeArticle, DesignationArticle, Quantite));

                        } else {

                            int index_art = listCodeArticleDistinct.indexOf(CodeArticle);
                            int last_qt = list_tot_article.get(index_art).getQuantiteStock();

                            int nv_qt = last_qt + Quantite;

                            list_tot_article.set(index_art, new ArticleStock(CodeArticle, DesignationArticle, nv_qt));

                        }

                    }

                    BonEntre bonEntre = new BonEntre(NumeroBonEntrer, DateBonEntrer, CodeDepot, Depot, NumeroEtat, Libelle, NomUtilisateur);

                    bonEntre.setListLigneBonEntree(listLigneBonEntree);

                    listBonEntree.add(bonEntre);


                }
            }
            con.close();

        } catch (Exception ex) {

            res = ex.getMessage();
            Log.e("ERROR_listRDV", res.toString());
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

        progressBar.setVisibility(View.INVISIBLE);

        BonEntreeRVAdapter adapter = new BonEntreeRVAdapter(activity, listBonEntree);
        rv_list_bon_entree.setAdapter(adapter);

     //   list_tot_article.add(new ArticleStock("Total","Total",tot_qt)) ;





    }


}
