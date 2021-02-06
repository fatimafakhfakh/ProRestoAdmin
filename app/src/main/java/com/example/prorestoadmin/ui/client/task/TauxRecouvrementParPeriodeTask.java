package com.example.prorestoadmin.ui.client.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.adapter.RecouvrementParJourAdapterLV;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.RecouvrementParJour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


public class TauxRecouvrementParPeriodeTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;


    ConnectionClass connectionClass;
    String user, password, base, ip;


    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dfSQL = new SimpleDateFormat("yyyy-MM-dd");
    Date  date_debut  , date_fin  ;
    ListView lv_list_taux_recouv;
    ProgressBar pb;


    ArrayList<RecouvrementParJour> listRecouvrement = new ArrayList<>();


    public TauxRecouvrementParPeriodeTask (Activity activity,  Date  date_debut  ,Date date_fin , ListView lv_list_taux_recouv , ProgressBar pb)  {
        this.activity = activity;
        this.lv_list_taux_recouv = lv_list_taux_recouv;
        this.pb = pb;
        this.date_debut = date_debut  ;
        this.date_fin= date_fin  ;

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

pb.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
            Log.e("con", "" + con);
            if (con == null) {
                res = "Check Your Internet Access!";
            } else {

                String query = " DECLARE\t@return_value int\n" +
                        "\n" +
                        "EXEC\t@return_value = [dbo].[EtatTauxRecouvrement]\n" +
                        "\t\t@DateDebut = N'"+df.format(date_debut)+"',\n" +
                        "\t\t@DateFin = N'"+df.format(date_fin)+"'\n" +
                        "\n" +
                        "SELECT\t'Return Value' = @return_value\n  ";


                Log.e("query_Taux_recouvr", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String  _date = rs.getString("DateBonLivraisonVente");

                    Date date_vente  = dfSQL.parse(_date) ;
                    double   totalVente  = rs.getDouble("TotalVente") ;
                    double totalRetour   = rs.getDouble("TotalRetour") ;
                    double totalReglement   = rs.getDouble("TotalReglement") ;
                    double totalRecouvrement   = rs.getDouble("TotalRecouvrement") ;

                    RecouvrementParJour  recouvrementParJour  = new RecouvrementParJour(date_vente  ,totalVente  ,totalRetour ,totalReglement ,totalRecouvrement) ;
                    listRecouvrement.add(recouvrementParJour) ;
                }
            }
            con.close();

        } catch (Exception ex) {

            res = ex.getMessage();
            Log.e("ERROR_listrecouv", res.toString());
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

        pb.setVisibility(View.INVISIBLE);

        RecouvrementParJourAdapterLV  adapterLV  = new RecouvrementParJourAdapterLV(activity   , listRecouvrement)  ;
        lv_list_taux_recouv.setAdapter(adapterLV);


    }


}
