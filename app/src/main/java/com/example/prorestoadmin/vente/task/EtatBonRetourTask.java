package com.example.prorestoadmin.vente.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.prorestoadmin.adapter.BonLivraisonHistRVAdapter;
import com.example.prorestoadmin.adapter.BonRetourHistRVAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.BonLivraisonVente;
import com.example.prorestoadmin.model.BonRetourVente;
import com.example.prorestoadmin.model.LigneBonRetourVente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


public class EtatBonRetourTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity ;


    Date  date_debut   , date_fin  ;
    RecyclerView rv_list_bt_en_attente ;
    ProgressBar progressBar ;
    SearchView search_bar_client;
    ConnectionClass connectionClass ;
    String user, password, base, ip ;


    ArrayList<BonRetourVente> listBonRetour = new ArrayList<>();
    SimpleDateFormat df  = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat       dtfSQL    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public EtatBonRetourTask(Activity activity , Date  date_debut , Date date_fin  , RecyclerView rv_list_bt_en_attente, ProgressBar progressBar ,  SearchView search_bar_client ) {
        this.activity = activity;
        this.rv_list_bt_en_attente = rv_list_bt_en_attente;
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

                String query = "select  \n" +
                        "    NumeroBonRetourVente ,\n" +
                        "    HeureCreation as HeureRetour ,\n" +
                        "    CodeClient ,\n" +
                        "    RaisonSociale ,\n" +
                        "    TotalTTC ,\n" +
                        "    Etat.NumeroEtat,\n" +
                        "    NomUtilisateur,\n" +
                        "    Etat.Libelle  as etat\n" +
                        "    from BonRetourVente \n" +
                        "    INNER  JOIN Etat on  Etat.NumeroEtat = BonRetourVente.NumeroEtat\n" +
                        //"    where  NomUtilisateur  = '"+NomUtilisateur+"'  \n" +
                        "    where    CONVERT (date  ,DateBonRetourVente)   between   '"+df.format(date_debut)+"'  and   '"+df.format(date_fin)+"'  \n" +
                        "    order by HeureCreation  " +
                        "   ";




                Log.e("query_bon_retour", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String NumeroBonRetourVente = rs.getString("NumeroBonRetourVente");
                    Date HeureRetour = dtfSQL.parse(rs.getString("HeureRetour"));
                    String CodeClient = rs.getString("CodeClient");
                    String RaisonSociale = rs.getString("RaisonSociale");
                    String NomUtilisateur = rs.getString("NomUtilisateur");
                    //
                    double TotalTTC = rs.getDouble("TotalTTC");
                    String etat = rs.getString("etat");



                    String query_detail_retour = "    select  CodeArticle ,  DesignationArticle  , Quantite from LigneBonRetourVente   where NumeroBonRetourVente = '"+NumeroBonRetourVente+"' "  ;
                    Log.e("query_detail_retour", query_detail_retour);

                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query_detail_retour);
                    ArrayList<LigneBonRetourVente>  listLigneBonRetourVentes = new ArrayList<>() ;

                    while (rs2.next()) {

                        String CodeArticle = rs2.getString("CodeArticle");
                        String DesignationArticle = rs2.getString("DesignationArticle");
                        int Quantite = rs2.getInt("Quantite");

                        LigneBonRetourVente  ligneBR  = new LigneBonRetourVente(CodeArticle ,DesignationArticle ,Quantite ) ;
                        listLigneBonRetourVentes.add(ligneBR) ;

                    }


                    BonRetourVente   br  = new BonRetourVente(NumeroBonRetourVente ,HeureRetour ,CodeClient ,RaisonSociale ,NomUtilisateur ,TotalTTC ,etat  );

                    br.setList_ligne_br(listLigneBonRetourVentes);
                    listBonRetour.add(br) ;




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

        BonRetourHistRVAdapter adapter  = new BonRetourHistRVAdapter(activity , listBonRetour) ;
        rv_list_bt_en_attente.setAdapter(adapter);

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

                final ArrayList<BonRetourVente> fitler_list_BR = filter(listBonRetour, query);
                BonRetourHistRVAdapter adapter  = new BonRetourHistRVAdapter(activity , fitler_list_BR) ;
                rv_list_bt_en_attente.setAdapter(adapter);

                return false;
            }

        });

    }

    private ArrayList<BonRetourVente> filter(ArrayList<BonRetourVente> listBR, String term) {

        term = term.toLowerCase();
        final ArrayList<BonRetourVente> filetrlistBR = new ArrayList<>();

        for (BonRetourVente c : listBR) {
            final String txt_raison = c.getRaisonSociale().toLowerCase();
            final String txt_num_br = c.getNumeroBonRetourVente().toLowerCase();
            final String txt_etablie = c.getNomUtilisateur().toLowerCase();
            if (txt_raison.contains(term) ||txt_num_br.contains(term ) ||txt_etablie.contains(term )) {
                filetrlistBR.add(c);
            }
        }
        return filetrlistBR;
    }

}
