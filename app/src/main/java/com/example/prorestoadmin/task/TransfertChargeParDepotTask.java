package com.example.prorestoadmin.task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.adapter.BTenChargeAdapterRV;
import com.example.prorestoadmin.adapter.EtatDeStockAdapterLV;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.ArticleStock;
import com.example.prorestoadmin.model.BonTransfert;
import com.example.prorestoadmin.model.LigneBonTransfert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class TransfertChargeParDepotTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;

    String CodeDepot;

    ConnectionClass connectionClass;
    String user, password, base, ip;

    ArrayList<ArticleStock> listArticle = new ArrayList<>();

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat dtfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    int transfert_existe = 0;

    BonTransfert bonTransfert;
    ArrayList<BonTransfert> listBonTransfert = new ArrayList<>();


    @SuppressLint("StaticFieldLeak")
    RecyclerView rv_list_bt_en_charge;
  /*  CardView card_transfert_charge;
    TextView txt_num_bt;
    ListView lv_list_article_transfert;*/

    public TransfertChargeParDepotTask(Activity activity, String CodeDepot,

                                       RecyclerView rv_list_bt_en_charge) {


        this.activity = activity;
        this.CodeDepot = CodeDepot;
        this.rv_list_bt_en_charge=rv_list_bt_en_charge;


        /*this.card_transfert_charge = card_transfert_charge;
        this.txt_num_bt = txt_num_bt;
        this.lv_list_article_transfert = lv_list_article_transfert;*/


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

        transfert_existe = 0;

    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
            Log.e("con", "" + con);
            if (con == null) {
                res = "Check Your Internet Access!";
            } else {

                String query = "  select  \n" +
                        "                         NumeroBonTransfert , \n" +
                        "                         HeureCreation as DateBonTransfert , \n" +
                        "                         CodeDepotSource , \n" +
                        "                         (select  libelle from Depot where CodeDepot = CodeDepotSource) as DepotSource , \n" +
                        "                         CodeDepotDestination , \n" +
                        "                         (select  libelle from Depot where CodeDepot = CodeDepotDestination) as DepotDestination , \n" +
                        "                         BonTransfert.NumeroEtat ,Etat.Libelle \n" +
                        "                     \n" +
                        "                         from BonTransfert  \n" +
                        "                         INNER  JOIN Etat on  Etat.NumeroEtat = BonTransfert.NumeroEtat \n" +
                        "   where  Etat.NumeroEtat  = 'E55'   and CodeDepotDestination = '" + CodeDepot + "'";


                Log.e("query_list_article", query);
                listArticle.clear();

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String NumeroBonTransfert = rs.getString("NumeroBonTransfert");
                    Date DateBonTransfert = dtfSQL.parse(rs.getString("DateBonTransfert"));
                    String CodeDepotSource = rs.getString("CodeDepotSource");
                    String DepotSource = rs.getString("DepotSource");
                    String CodeDepotDestination = rs.getString("CodeDepotDestination");
                    String DepotDestination = rs.getString("DepotDestination");
                    String NumeroEtat = rs.getString("NumeroEtat");
                    String Libelle = rs.getString("Libelle");


                    String query_detail_transfert = " select  CodeArticle ,  DesignationArticle  , Quantite from LigneBonTransfert    where NumeroBonTransfert  = '" + NumeroBonTransfert + "' ";
                    Log.e("query_detail_transfert", query_detail_transfert);

                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query_detail_transfert);
                    ArrayList<LigneBonTransfert> listLigneBonTransfert = new ArrayList<>();

                    while (rs2.next()) {

                        String CodeArticle = rs2.getString("CodeArticle");
                        String DesignationArticle = rs2.getString("DesignationArticle");
                        int Quantite = rs2.getInt("Quantite");

                        LigneBonTransfert ligneBonTransfert = new LigneBonTransfert(CodeArticle, DesignationArticle, Quantite);
                        listLigneBonTransfert.add(ligneBonTransfert);

                    }


                    bonTransfert = new BonTransfert(NumeroBonTransfert, DateBonTransfert, CodeDepotSource, DepotSource, CodeDepotDestination, DepotDestination, NumeroEtat, Libelle);
                    bonTransfert.setListLigneBonTransfert(listLigneBonTransfert);

                    listBonTransfert.add(bonTransfert) ;

                    transfert_existe = 1;
                }
            }
            con.close();

        } catch (Exception ex) {

            res = ex.getMessage();
            Log.e("ERROR_", res.toString());

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


        BTenChargeAdapterRV  bTenChargeAdapterRV  = new BTenChargeAdapterRV(activity ,listBonTransfert);
        rv_list_bt_en_charge.setAdapter(bTenChargeAdapterRV);


      /*  if (transfert_existe == 1) {

            card_transfert_charge.setVisibility(View.VISIBLE);
            txt_num_bt.setText(bonTransfert.getNumeroBonTransfert());

            Detail_bt_Adapter1 detail_bt_adapter1 = new Detail_bt_Adapter1(bonTransfert.getListLigneBonTransfert());
            lv_list_article_transfert.setAdapter(detail_bt_adapter1);

        } else if (transfert_existe == 0) {

            card_transfert_charge.setVisibility(View.GONE);

        }*/


    }




}








