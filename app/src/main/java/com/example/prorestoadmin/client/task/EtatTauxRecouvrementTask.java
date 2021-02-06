package com.example.prorestoadmin.client.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.prorestoadmin.adapter.RemiseClientAdapterRV;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Client;
import com.example.prorestoadmin.model.RecouvrementParJour;
import com.example.prorestoadmin.model.RemiseArticle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class EtatTauxRecouvrementTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;

    RecyclerView rv_list_taux_recouvrement;

    ProgressBar pb_chargement;



    ConnectionClass connectionClass;
    String user, password, base, ip;

    ArrayList<RecouvrementParJour> listRecouvrementParJour = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public EtatTauxRecouvrementTask(Activity activity, RecyclerView rv_list_taux_recouvrement, ProgressBar pb_chargement ) {
        this.activity = activity;

        this.pb_chargement = pb_chargement;
        this.rv_list_taux_recouvrement = rv_list_taux_recouvrement;


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

                String query = "  ";


                Log.e("query_list_article", query);


                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);


                while (rs.next()) {






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



    }

}
