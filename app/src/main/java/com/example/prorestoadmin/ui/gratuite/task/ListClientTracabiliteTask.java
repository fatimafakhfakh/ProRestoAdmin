package com.example.prorestoadmin.ui.gratuite.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.prorestoadmin.adapter.SpinnerAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Client;
import com.example.prorestoadmin.ui.client.ClientMenuFragment;
import com.example.prorestoadmin.ui.gratuite.activity.TracabiliteGratuiteClientActivity;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListClientTracabiliteTask extends AsyncTask<String,String,String> {

    Connection con;
    String res ;

    Activity activity  ;
    SearchableSpinner sp_client ;
    String login ;
    String origine ;

    ArrayList<String> listRaison = new ArrayList<>() ;
    ArrayList<Client> listClient = new ArrayList<Client>() ;
    DateFormat df  = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    DateFormat dfSQL = new SimpleDateFormat("dd/MM/yyyy");

    SimpleDateFormat formatDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    ConnectionClass connectionClass;
    String user, password, base, ip;

    public ListClientTracabiliteTask(Activity activity , SearchableSpinner sp_client ) {
        this.activity = activity;
        this.sp_client = sp_client   ;


        SharedPreferences pref = activity.getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        user = pref.getString("user", user);
        ip = pref.getString("ip", ip);
        password = pref.getString("password", password);
        base = pref.getString("base", base);
        connectionClass = new ConnectionClass();



    }


    //  lancement  de progress dialog
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // txt_client.setHint("Patientez SVP ...");

    }




    //  donwnload  data
    @Override
    protected String doInBackground(String... strings) {

        try {
            Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
            Log.e("con", "" + con);
            if (con == null) {
                res = "Check Your Internet Access!";
            } else {
                String query = " select   distinct   CodeClient   , RaisonSociale   from  MaquetteBonGratuite " ;

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                Log.e("query", query) ;


                listRaison.clear();

                while ( rs.next() ) {


                    String codeClient = rs.getString("CodeClient") ;
                    String RaisonSociale =rs.getString("RaisonSociale") ;


                     Client client = new Client(codeClient ,RaisonSociale) ;
                     listClient.add(client) ;
                     listRaison.add(client.getRaisonSocial())  ;
                     Log.e("Client", client.getCodeClient() + " - " +client.getRaisonSocial());


                }
            }
            con.close();

        } catch (Exception ex) {

            res = ex.getMessage();
            Log.e("ERROR", res) ;

        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        SpinnerAdapter adapter = new SpinnerAdapter(activity  , listRaison)  ;
        sp_client.setAdapter(adapter);



        sp_client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                Log.e("Client_selected"  ,""+listClient.get(position).toString())  ;

                TracabiliteGratuiteClientActivity.CodeClientSeleted  = listClient.get(position).getCodeClient()  ;
                TracabiliteGratuiteClientActivity.RaisonClientSeleted  = listClient.get(position).getRaisonSocial()  ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


}
