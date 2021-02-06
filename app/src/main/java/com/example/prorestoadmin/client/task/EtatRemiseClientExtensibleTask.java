package com.example.prorestoadmin.client.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.prorestoadmin.adapter.RemiseExpandableListAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Client;
import com.example.prorestoadmin.model.RemiseArticle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class EtatRemiseClientExtensibleTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;

    ExpandableListView elv_list_cleint_remise;

    ProgressBar pb_chargement;
    SearchView searchViewClient;


    ConnectionClass connectionClass;
    String user, password, base, ip;


    ArrayList<Client> listClient = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public EtatRemiseClientExtensibleTask(Activity activity, ExpandableListView elv_list_cleint_remise, ProgressBar pb_chargement, SearchView searchViewClient) {
        this.activity = activity;

        this.pb_chargement = pb_chargement;
        this.elv_list_cleint_remise = elv_list_cleint_remise;
        this.pb_chargement = pb_chargement;
        this.searchViewClient = searchViewClient;

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

                String query = "    select  distinct  RemiseArticleClient.CodeClient , RaisonSociale  \n" +
                        "    from  RemiseArticleClient   \n" +
                        "    inner join Client on Client.CodeClient = RemiseArticleClient.CodeClient\n" +
                        "    where RemiseArticleClient.TauxRemise  > 0.009 \n" +
                        "    and Client.Inactif = 0  and Client.Contentieux =0 ";


                Log.e("query_rem_client", query);


                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);


                while (rs.next()) {

                    String CodeClient = rs.getString("CodeClient");
                    String RaisonSociale = rs.getString("RaisonSociale");


                    Client client = new Client(CodeClient, RaisonSociale);

                    String queryRemise = " select  RemiseArticleClient.CodeArticle  , Article.Designation  \n" +
                            "    ,RemiseArticleClient.TauxRemise , Article.PrixVenteTTC  , Article.PrixVenteHT ,Article.CodeTVA  , TauxTVA  \n" +
                            "    from  RemiseArticleClient   \n" +
                            "    inner join Article  on Article.CodeArticle = " +
                            "     RemiseArticleClient.CodeArticle \n" +
                            "    INNER  JOIN  TVA  on TVA.CodeTVA = Article.CodeTVA  \n" +
                            "    where   CodeNature = '1'  and  Stockable  = '1' and Actif  = '1' and Article.CodeArticle like '00%'  \n" +
                            "    and RemiseArticleClient.TauxRemise > 0.009" +
                            "    and RemiseArticleClient.CodeClient = '" + CodeClient + "'\n  " ;


                    Log.e("query_rem_article", queryRemise);

                    Statement stmtRemise = con.createStatement();
                    ResultSet rsRemise = stmtRemise.executeQuery(queryRemise);


                    ArrayList<RemiseArticle> listRemiseArticle = new ArrayList<>();

                    while (rsRemise.next()) {

                        String CodeArticle = rsRemise.getString("CodeArticle");
                        String Designation = rsRemise.getString("Designation");
                        double TauxRemise = rsRemise.getDouble("TauxRemise");
                        double PrixVenteTTC = rsRemise.getDouble("PrixVenteTTC");

                        double PrixVenteHT = rsRemise.getDouble("PrixVenteHT");
                        int CodeTVA = rsRemise.getInt("CodeTVA");
                        double TauxTVA = rsRemise.getDouble("TauxTVA");

                        RemiseArticle remiseArticle = new RemiseArticle(CodeArticle, Designation, TauxRemise, PrixVenteTTC, PrixVenteHT, CodeTVA, TauxTVA);
                        listRemiseArticle.add(remiseArticle);

                    }

                    client.setListRemiseArticleClient(listRemiseArticle);
                    listClient.add(client);

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


        RemiseExpandableListAdapter remiseExpandableListAdapter = new RemiseExpandableListAdapter(activity, listClient);
        elv_list_cleint_remise.setAdapter(remiseExpandableListAdapter);



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

                RemiseExpandableListAdapter remiseExpandableListAdapter = new RemiseExpandableListAdapter(activity, fitler_list_client);
                elv_list_cleint_remise.setAdapter(remiseExpandableListAdapter);

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
