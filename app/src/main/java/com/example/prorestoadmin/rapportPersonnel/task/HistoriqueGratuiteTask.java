package com.example.prorestoadmin.rapportPersonnel.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prorestoadmin.adapter.BonGratuiteHistRVAdapter;
import com.example.prorestoadmin.adapter.BonLivraisonHistRVAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.BonGratuiteVente;
import com.example.prorestoadmin.model.BonLivraisonVente;
import com.example.prorestoadmin.model.LigneBonGratuite;
import com.example.prorestoadmin.model.LigneBonLivraisonVente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HistoriqueGratuiteTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;


    Date date_debut, date_fin;
    RecyclerView rv_list_bg;
    ProgressBar progressBar;
    ConnectionClass connectionClass;
    String user, password, base, ip;


    String NomUtilisateur;

    ArrayList<BonGratuiteVente> listBonGratuite = new ArrayList<>();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat dtfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public HistoriqueGratuiteTask(Activity activity, String NomUtilisateur, Date date_debut, Date date_fin, RecyclerView rv_list_bg, ProgressBar progressBar) {
        this.activity = activity;
        this.rv_list_bg = rv_list_bg;
        this.progressBar = progressBar;

        this.NomUtilisateur = NomUtilisateur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;

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
                        "        where  NomUtilisateur  = '" + NomUtilisateur + "'  \n" +
                        "        and    CONVERT (date  ,DateBonGratuiteVente)   between   '" + df.format(date_debut) + "'  and   '" + df.format(date_fin) + "'  \n" +
                        "        order by HeureCreation   " +
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
        rv_list_bg.setAdapter(adapter);

    }

}
