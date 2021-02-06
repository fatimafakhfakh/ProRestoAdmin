package com.example.prorestoadmin.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.prorestoadmin.EtatDeStock.main.EtatDeStockPagerAdapter;
import com.example.prorestoadmin.FicheArticle.FicheArticleActivity;
import com.example.prorestoadmin.FicheArticle.ui.main.FicheArticlePagerAdapter;
import com.example.prorestoadmin.adapter.TotaliteStockAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Depot;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class ListTotStockTask extends AsyncTask<String, String, String> {

    String res;

    AppCompatActivity activity;

    ListView lv_list_tot_stock;
    String Origine;
    ConnectionClass connectionClass;
    String user, password, base, ip;


    ArrayList<Depot> listDepot = new ArrayList<>();


    public ListTotStockTask(AppCompatActivity activity, ListView lv_list_tot_stock, String Origine) {
        this.activity = activity;
        this.lv_list_tot_stock = lv_list_tot_stock;
        this.Origine = Origine;


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


    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
            Log.e("con", "" + con);
            if (con == null) {
                res = "Check Your Internet Access!";
            } else {

                String query = "select    Depot.CodeDepot, Depot.Libelle as Depot  , \n" +
                        "( select  SUM(Quantite)  from  stock \n" +
                        "   INNER JOIN Article on Article.CodeArticle = Stock.CodeArticle\n" +
                        "   where CodeDepot =  Depot.CodeDepot \n" +
                        "   and    Article. CodeNature = '1'  and Stockable  = '1'   and Article.Actif  = '1'  ) as   TotQuanite  \n" +
                        "   \n" +
                        "    , (select  ISNULL  ( SUM(Quantite)  , 0   )   from LigneBonTransfert \n" +
                        "     INNER  JOIN BonTransfert on  BonTransfert.NumeroBonTransfert = LigneBonTransfert.NumeroBonTransfert  \n" +
                        "     INNER  JOIN Etat on  Etat.NumeroEtat = BonTransfert.NumeroEtat  \n" +
                        "    where  Etat.NumeroEtat  = 'E55'   and CodeDepotDestination =  Depot.CodeDepot )  as qtTransfert \n" +
                        "  \n" +
                        "    from  Stock \n" +
                        "    \n" +
                        "    INNER  JOIN Depot  on Depot.CodeDepot  = Stock.CodeDepot\n" +
                        " \n" +
                        "    inner join  UtilisateurDepot on Depot.CodeDepot  = UtilisateurDepot.CodeDepot \n" +
                        "    inner join Utilisateur  on Utilisateur.NomUtilisateur = UtilisateurDepot.NomUtilisateur \n" +
                        "                          \n" +
                        "    \n" +
                        "    where  Utilisateur.Actif = 1 and CodeFonction in ('FN006','FN007','FN008')    \n" +
                        "    Group BY Depot.CodeDepot  ,Depot.Libelle\n  ";


                Log.e("query_tot_stock", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);


                int  tot_stock  = 0  ;
                int  tot_transfert  = 0 ;
                while (rs.next()) {

                    String CodeDepot = rs.getString("CodeDepot");
                    String Libelle = rs.getString("Depot");
                    int   QtStock  = rs.getInt("TotQuanite") ;
                    int   qtTransfert  = rs.getInt("qtTransfert") ;



                    Depot depot = new Depot(CodeDepot, Libelle,QtStock,qtTransfert);

                    tot_stock = tot_stock + QtStock ;
                    tot_transfert =tot_transfert +qtTransfert ;

                        listDepot.add(depot);
                }

                listDepot.add(new Depot("","Total",tot_stock,tot_transfert)) ;


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


        TotaliteStockAdapter  totaliteStockAdapter  = new TotaliteStockAdapter(activity , listDepot) ;
        lv_list_tot_stock.setAdapter(totaliteStockAdapter);


    }


}
