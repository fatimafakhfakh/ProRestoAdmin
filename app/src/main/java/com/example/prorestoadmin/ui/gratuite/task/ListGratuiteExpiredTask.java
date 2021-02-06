package com.example.prorestoadmin.ui.gratuite.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.example.prorestoadmin.adapter.GratuiteEnCoursExtensibleAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Convention;
import com.example.prorestoadmin.model.GratuiteClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ListGratuiteExpiredTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;


    Date date_jour;
    int mode_gratuite ;
    ExpandableListView elv_list_gratuite_expired;
    ProgressBar progressBar;

    ConnectionClass connectionClass;
    String user, password, base, ip;


    ArrayList<GratuiteClient> listGratuiteClient = new ArrayList<>();

    DateFormat dtfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public ListGratuiteExpiredTask(Activity activity,  Date date_jour, int mode_gratuite  , ExpandableListView elv_list_gratuite_expired , ProgressBar progressBar) {
        this.activity = activity;

        this.date_jour = date_jour;
        this.mode_gratuite=mode_gratuite ;
        this.elv_list_gratuite_expired = elv_list_gratuite_expired;
        this.progressBar = progressBar;


        SharedPreferences pref = activity.getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);

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

                String CONDITION = "";



                String query = "    select Client. CodeClient , Client.RaisonSociale , DateDebut  ,DateFin  from Vue_MaquetteBonGratuite\n" +
                        "    inner join  Client on Client.CodeClient =  Vue_MaquetteBonGratuite.CodeClient  \n" +
                        "    where  1=1  " + CONDITION + "    and   '" + sdf.format(date_jour) + "'  >  DateFin \n" +
                        "    group by   Client.CodeClient , Client.RaisonSociale   , DateDebut  ,DateFin   ";


                Log.e("query_gratuite_en_cours", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String CodeClient = rs.getString("CodeClient");
                    String RaisonSociale = rs.getString("RaisonSociale");

                    Date DateDebut = dtfSQL.parse(rs.getString("DateDebut"));
                    Date DateFin = dtfSQL.parse(rs.getString("DateFin"));

                    GratuiteClient gratuiteClient = new GratuiteClient(CodeClient, RaisonSociale, DateDebut, DateFin);


                    ArrayList<Convention> listConvention = new ArrayList<>();

                    String query_conventions_client = " select CodeArticle , Designation , Quantiteminimum , QuantiteGratuite , Convention , QTVendu  , QTRetour  , QTPalierGratuite  ,Periode\n" +
                            "from Vue_MaquetteBonGratuite \n" +
                            "where CodeClient = '" + CodeClient + "'\n ";


                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query_conventions_client);


                    while (rs2.next()) {

                        String CodeArticle = rs2.getString("CodeArticle");
                        String Designation = rs2.getString("Designation");
                        int Quantiteminimum = rs2.getInt("Quantiteminimum");
                        int QuantiteGratuite = rs2.getInt("QuantiteGratuite");
                        String convention = rs2.getString("Convention");
                        int QTVendu = rs2.getInt("QTVendu");
                        int QTRetour = rs2.getInt("QTRetour");
                        int QTPalierGratuite = rs2.getInt("QTPalierGratuite");
                        int Periode = rs2.getInt("Periode");

                        Convention cnv = new Convention(CodeArticle, Designation, Quantiteminimum, QuantiteGratuite, convention, QTVendu, QTRetour, QTPalierGratuite,Periode);
                        listConvention.add(cnv);
                    }

                    boolean gratuite_atteint = false;

                    for (Convention conv : listConvention) {
                        if ((conv.getQuantiteMinimum() - conv.getQTPalierGratuite()) <= 0) {
                            gratuite_atteint = true;
                            break;
                        }

                    }

                    gratuiteClient.setGratuit_atteint(gratuite_atteint) ;
                    gratuiteClient.setListConvention(listConvention);


                    if (mode_gratuite==0) //tt
                    {
                        listGratuiteClient.add(gratuiteClient);
                    }
                    else  if (mode_gratuite==1) // gratuite atteint
                    {
                      if (gratuiteClient.isGratuit_atteint())
                          listGratuiteClient.add(gratuiteClient);
                    }
                    else  if (mode_gratuite== 2 ) // gratuite  nn  atteint
                    {
                        if (!gratuiteClient.isGratuit_atteint())
                            listGratuiteClient.add(gratuiteClient);
                    }


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


        GratuiteEnCoursExtensibleAdapter adapter = new GratuiteEnCoursExtensibleAdapter(activity, listGratuiteClient);
        elv_list_gratuite_expired.setAdapter(adapter);


    }

}
