package com.example.prorestoadmin.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.prorestoadmin.adapter.ClientRV;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


public class EtatClientNonMouvementeTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;

    Date date_debut, date_fin;
    Date date_debut_1, date_fin_1;
    RecyclerView rv_list_client;
    ProgressBar progressBar;
    SearchView searchViewClient;

    ConnectionClass connectionClass;
    String user, password, base, ip;

    ArrayList<Client> listClient = new ArrayList<>();


    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat dtfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public EtatClientNonMouvementeTask(Activity activity, Date date_debut, Date date_fin, RecyclerView rv_list_client, ProgressBar progressBar, SearchView searchViewClient) {
        this.activity = activity;
        this.rv_list_client = rv_list_client;
        this.progressBar = progressBar;


        this.searchViewClient = searchViewClient;
        this.date_debut = date_debut;
        this.date_fin = date_fin;

        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date_debut);
        cal1.add(Calendar.DAY_OF_MONTH, -2);
        this.date_debut_1 = cal1.getTime();


        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date_fin);
        cal2.add(Calendar.DAY_OF_MONTH, -2);
        this.date_fin_1 = cal2.getTime();

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


                String query = "  select CodeClient ,  RaisonSociale from BonLivraisonVente  where DateBonLivraisonVente\n" +
                        "    between '"+df.format(date_debut_1)+"' and  '"+df.format(date_fin_1)+"'  \n" +
                        "    and  CodeClient  not in (select CodeClient from BonLivraisonVente where\n" +
                        "    DateBonLivraisonVente     between '"+df.format(date_debut)+"' and  '"+df.format(date_fin)+"' group by CodeClient)---  input    \n" +
                        "    group by CodeClient , RaisonSociale       ";


                Log.e("query_client_nn_mvnt", query);

                Statement stmt_solde = con.createStatement();
                ResultSet rs = stmt_solde.executeQuery(query);

                while (rs.next()) {

                    String CodeClient = rs.getString("CodeClient");
                    String RaisonSociale = rs.getString("RaisonSociale");


                    Client client = new Client(CodeClient, RaisonSociale);
                    listClient.add(client);

                }


            }
            con.close();

        } catch (Exception ex) {

            res = ex.getMessage();
            Log.e("ERROR_detail_solde", res.toString());
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

        progressBar.setVisibility(View.GONE);

        Log.e("C_nn_mvnt", "" + listClient.size());

        ClientRV adapter = new ClientRV(activity, listClient);
        rv_list_client.setAdapter(adapter);


        searchViewClient.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!searchViewClient.isIconified()) {

                    searchViewClient.setIconified(true);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                final ArrayList<Client> fitler_list_client = filter(listClient, query);

                ClientRV adapter = new ClientRV(activity, fitler_list_client);
                rv_list_client.setAdapter(adapter);


                return false;
            }

        });


    }


    private ArrayList<Client> filter(ArrayList<Client> listClient, String term) {

        term = term.toLowerCase();
        final ArrayList<Client> filetrListClient = new ArrayList<>();

        for (Client c : listClient) {
            final String txt_raison = c.getRaisonSocial().toLowerCase();

            if (txt_raison.contains(term)) {
                filetrListClient.add(c);
            }
        }
        return filetrListClient;
    }

}
