package com.example.prorestoadmin.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.prorestoadmin.adapter.CaisseAdapterRV;
import com.example.prorestoadmin.adapter.FicheCompteAdapterLV;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Caisse;
import com.example.prorestoadmin.model.FicheArticle;
import com.example.prorestoadmin.model.FicheCompte;
import com.example.prorestoadmin.ui.caisse.CaisseFragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


public class MouvementCaisseRecetteDetailTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;
    ListView lv_list_mvmnt_caisse_recette ;
    ProgressBar pb_chargement;
    String CodeCompte  ;
    Date date_debut  ;
    Date date_fin  ;


    ConnectionClass connectionClass;
    String user, password, base, ip;
    ArrayList<FicheCompte> listFicheCompte = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    DateFormat dtfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    double  solde_en_cours  ;


    public MouvementCaisseRecetteDetailTask(Activity activity, ListView lv_list_mvmnt_caisse_recette, ProgressBar pb_chargement, String codeCompte, Date date_debut, Date date_fin) {
        this.activity = activity;
        this.lv_list_mvmnt_caisse_recette = lv_list_mvmnt_caisse_recette;
        this.pb_chargement = pb_chargement;
        CodeCompte = codeCompte;
        this.date_debut = date_debut;
        this.date_fin = date_fin;


        SharedPreferences pref = activity.getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        user = pref.getString("user", user);
        ip = pref.getString("ip", ip);
        base = pref.getString("base", base);
        password = pref.getString("password", password);

        connectionClass = new ConnectionClass();

    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
         pb_chargement.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
            Log.e("con", "" + con);
            if (con == null) {
                res = "Check Your Internet Access!";
            } else {

                String query = "DECLARE\t@return_value int\n" +
                        "\n" +
                        "EXEC\t@return_value = [dbo].[FicheCompteDetailler]\n" +
                        "\t\t@CodeCompte = N'"+CodeCompte+"',\n" +
                        "\t\t@DateDebut = '"+sdf.format(date_debut)+"',\n" +
                        "\t\t@DateFin = '"+sdf.format(date_fin)+"'\n" +
                        "\n" +
                        "SELECT\t'Return Value' = @return_value  " ;

                Log.e("query_mvmnt_caisse",""+query) ;

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String Compte = rs.getString("Compte");
                    Date DateOperation = dtfSQL.parse(rs.getString("DateOperation"));
                    String NumeroPiece = rs.getString("NumeroPiece");

                    String Libelle = rs.getString("Libelle");
                    String Reference = rs.getString("Reference");
                    Date Echeance = dtfSQL.parse(rs.getString("Echeance"));

                    Double Debit = rs.getDouble("Debit");
                    Double Credit = rs.getDouble("Credit");
                    Date HeureCreation = dtfSQL.parse(rs.getString("HeureCreation"));

                    String CodeModeReglement = rs.getString("CodeModeReglement");
                    Double Solde = rs.getDouble("Solde");

                    solde_en_cours = solde_en_cours + Solde ;

                    FicheCompte ficheCompte = new FicheCompte(Compte, DateOperation, NumeroPiece, Libelle, Reference, Echeance, Debit, Credit, HeureCreation, CodeModeReglement, Solde,solde_en_cours);
                    listFicheCompte.add(ficheCompte);


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


        pb_chargement.setVisibility(View.INVISIBLE);


        FicheCompteAdapterLV ficheCompteAdapterLV = new FicheCompteAdapterLV(activity , listFicheCompte) ;
        lv_list_mvmnt_caisse_recette.setAdapter(ficheCompteAdapterLV);


    }


}
