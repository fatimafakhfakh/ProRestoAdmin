package com.example.prorestoadmin.client.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.prorestoadmin.adapter.DetailSoldeClientRVAdapter;
import com.example.prorestoadmin.adapter.PersonnelAdapterRV;
import com.example.prorestoadmin.adapter.RemiseClientAdapterRV;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Client;
import com.example.prorestoadmin.model.ClientRappelPaiment;
import com.example.prorestoadmin.model.Personnel;
import com.example.prorestoadmin.model.RemiseArticle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


public class EtatRemiseClientTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;

    RecyclerView rv_list_remise_client;

    ProgressBar pb_chargement;
    SearchView searchViewClient ;

    String CodeDepot;

    ConnectionClass connectionClass;
    String user, password, base, ip;

    ArrayList<RemiseArticle> listRemiseArticle = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public EtatRemiseClientTask(Activity activity, RecyclerView rv_list_remise_client, ProgressBar pb_chargement,    SearchView searchViewClient) {
        this.activity = activity;

        this.pb_chargement = pb_chargement;
        this.rv_list_remise_client = rv_list_remise_client;
        this.pb_chargement = pb_chargement;
        this.searchViewClient=searchViewClient ;

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

                String query = " select  RemiseArticleClient.CodeClient ,RaisonSociale ,RemiseArticleClient.CodeArticle  , Article.Designation  \n" +
                        ",RemiseArticleClient.TauxRemise , Article.PrixVenteTTC  , Article.PrixVenteHT ,Article.CodeTVA  , TauxTVA  \n" +
                        "from  RemiseArticleClient   \n" +
                        "inner join Client on Client.CodeClient = RemiseArticleClient.CodeClient\n" +
                        "inner join Article  on Article.CodeArticle = RemiseArticleClient.CodeArticle \n" +
                        "INNER  JOIN  TVA  on TVA.CodeTVA = Article.CodeTVA \n" +
                        "where Client.Inactif = 0  and Client.Contentieux =0 \n" +
                        "and  CodeNature = '1'  and Stockable  = '1'   and Actif  = '1' and Article.CodeArticle like '00%'\n" +
                        "order BY CodeClient ,CodeArticle ,TauxRemise\n ";


                Log.e("query_list_article", query);
                listRemiseArticle.clear();

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);


                while (rs.next()) {

                    String CodeClient = rs.getString("CodeClient");
                    String RaisonSociale = rs.getString("RaisonSociale");
                    String CodeArticle = rs.getString("CodeArticle");
                    String Designation = rs.getString("Designation");
                    double TauxRemise = rs.getDouble("TauxRemise");
                    double PrixVenteTTC = rs.getDouble("PrixVenteTTC");

                    double PrixVenteHT = rs.getDouble("PrixVenteHT");
                    int CodeTVA = rs.getInt("CodeTVA");
                    double TauxTVA = rs.getDouble("TauxTVA");

                    RemiseArticle remiseArticle = new RemiseArticle(CodeClient, RaisonSociale, CodeArticle, Designation, TauxRemise, PrixVenteTTC, PrixVenteHT, CodeTVA, TauxTVA);
                    listRemiseArticle.add(remiseArticle);


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

        ArrayList<String> listCLientDistinct = new ArrayList<>();

        for (RemiseArticle ra : listRemiseArticle) {

            if (!listCLientDistinct.contains(ra.getCodeClient()))
                listCLientDistinct.add(ra.getCodeClient());

        }

        final ArrayList<Client> listClient  = new ArrayList<>();

        for (String CodeClient : listCLientDistinct) {

            Client  client  = new Client() ;
            ArrayList<RemiseArticle> listRemiseArticleClient  = new ArrayList<>();
            for (RemiseArticle ra : listRemiseArticle) {
                if (CodeClient.equals(ra.getCodeClient())) {

                    client.setCodeClient(ra.getCodeClient());
                    client.setRaisonSocial(ra.getRaisonSociale());

                    listRemiseArticleClient.add(ra) ;

                }
            }

            client.setListRemiseArticleClient(listRemiseArticleClient);


            listClient.add(client) ;



        }


        RemiseClientAdapterRV  remiseClientAdapterRV  = new RemiseClientAdapterRV(activity ,listClient);
        rv_list_remise_client.setAdapter(remiseClientAdapterRV);



        //Log.e("Client_Disctinct " + listCLientDistinct.size(), listCLientDistinct.toString());

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



                RemiseClientAdapterRV  remiseClientAdapterRV  = new RemiseClientAdapterRV(activity ,fitler_list_client);
                rv_list_remise_client.setAdapter(remiseClientAdapterRV);




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
