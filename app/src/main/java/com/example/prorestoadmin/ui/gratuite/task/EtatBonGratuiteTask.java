package com.example.prorestoadmin.ui.gratuite.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prorestoadmin.adapter.BonGratuiteHistRVAdapter;
import com.example.prorestoadmin.adapter.BonLivraisonHistRVAdapter;
import com.example.prorestoadmin.adapter.BonRetourHistRVAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.BonGratuiteVente;
import com.example.prorestoadmin.model.BonLivraisonVente;
import com.example.prorestoadmin.model.BonRetourVente;
import com.example.prorestoadmin.model.LigneBonGratuite;
import com.example.prorestoadmin.model.LigneBonLivraisonVente;
import com.example.prorestoadmin.model.LigneBonRetourVente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EtatBonGratuiteTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity ;


    Date  date_debut   , date_fin  ;
    RecyclerView rv_list_bon_gratuite  ;
    ProgressBar progressBar ;
    SearchView search_bar_client;
    ConnectionClass connectionClass ;
    String user, password, base, ip ;


    ArrayList<BonGratuiteVente> listBonGratuite = new ArrayList<>();
    SimpleDateFormat df  = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat       dtfSQL    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public EtatBonGratuiteTask(Activity activity , Date  date_debut , Date date_fin  , RecyclerView rv_list_bon_gratuite, ProgressBar progressBar ,  SearchView search_bar_client ) {
        this.activity = activity;
        this.rv_list_bon_gratuite = rv_list_bon_gratuite;
        this.progressBar = progressBar;
        this.search_bar_client = search_bar_client;


        this.date_debut= date_debut  ;
        this.date_fin=date_fin ;

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
                        "        NumeroBonGratuiteVente ,\n" +
                        "        HeureCreation as HeureGratuite ,\n" +
                        "        CodeClient ,\n" +
                        "        RaisonSociale ,\n" +
                        "        TotalTTC ,\n" +
                        "        Etat.NumeroEtat,\n" +
                        "        NomUtilisateur ,\n" +
                        "        Etat.Libelle  as etat\n" +
                        "        from BonGratuiteVente \n" +
                        "        INNER  JOIN Etat on  Etat.NumeroEtat = BonGratuiteVente.NumeroEtat\n" +
                        "        where   1 = 1   \n" +
                        "        and    CONVERT (date  ,DateBonGratuiteVente)   between   '" + df.format(date_debut) + "'  and   '" + df.format(date_fin) + "'  \n" +
                        "        order by NumeroBonGratuiteVente  DESC    " +
                        "   ";


                Log.e("query_hist_grtuite", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String NumeroBonGratuiteVente = rs.getString("NumeroBonGratuiteVente");
                    Date HeureGratuite = dtfSQL.parse(rs.getString("HeureGratuite"));
                    String CodeClient = rs.getString("CodeClient");
                    String RaisonSociale = rs.getString("RaisonSociale");
                    String NomUtilisateur = rs.getString("NomUtilisateur");
                    double TotalTTC = rs.getDouble("TotalTTC");
                    String etat = rs.getString("etat");


                    String query_detail_gratuite = "    select  CodeArticle ,  DesignationArticle  , Quantite from LigneBonGratuiteVente    where NumeroBonGratuiteVente = '" + NumeroBonGratuiteVente + "' ";
                    Log.e("query_detail_gratuite", query_detail_gratuite);

                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query_detail_gratuite);

                    ArrayList<LigneBonGratuite> listLigneBonGratuite = new ArrayList<>();

                    while (rs2.next()) {

                        String CodeArticle = rs2.getString("CodeArticle");
                        String DesignationArticle = rs2.getString("DesignationArticle");
                        int Quantite = rs2.getInt("Quantite");

                        LigneBonGratuite ligneBG = new LigneBonGratuite(CodeArticle, DesignationArticle, Quantite);
                        listLigneBonGratuite.add(ligneBG);

                    }


                    BonGratuiteVente bg = new BonGratuiteVente(NumeroBonGratuiteVente, HeureGratuite, CodeClient, RaisonSociale, NomUtilisateur, TotalTTC, etat);
                    bg.setList_ligne_bon_gratuite(listLigneBonGratuite);

                    listBonGratuite.add(bg);


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

        BonGratuiteHistRVAdapter adapter = new BonGratuiteHistRVAdapter(activity, listBonGratuite);
        rv_list_bon_gratuite.setAdapter(adapter);

        search_bar_client.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!search_bar_client.isIconified()) {
                    search_bar_client.setIconified(true);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                final ArrayList<BonGratuiteVente> fitler_list_BG = filter(listBonGratuite, query);
                BonGratuiteHistRVAdapter adapter = new BonGratuiteHistRVAdapter(activity, fitler_list_BG);
                rv_list_bon_gratuite.setAdapter(adapter);

                return false;
            }

        });

    }

    private ArrayList<BonGratuiteVente> filter(ArrayList<BonGratuiteVente> listBG, String term) {

        term = term.toLowerCase();
        final ArrayList<BonGratuiteVente> filetrlistBG = new ArrayList<>();

        for (BonGratuiteVente c : listBG) {
            final String txt_raison = c.getRaisonSociale().toLowerCase();
            final String txt_num_br = c.getNumeroBonGratuiteVente().toLowerCase();
            final String txt_etablie = c.getNomUtilisateur().toLowerCase();
            if (txt_raison.contains(term) ||txt_num_br.contains(term ) ||txt_etablie.contains(term )) {
                filetrlistBG.add(c);
            }
        }
        return filetrlistBG;
    }

}
