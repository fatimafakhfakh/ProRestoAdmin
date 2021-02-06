package com.example.prorestoadmin.task;

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
import com.example.prorestoadmin.model.Personnel;
import com.example.prorestoadmin.ui.client.ClientMenuFragment;
import com.example.prorestoadmin.ui.vente.VenteMenuFragment;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListLivRecTaskForSearchableSpinner extends AsyncTask<String,String,String> {

    Connection con;
    String res ;

    Activity activity  ;
    SearchableSpinner sp_client ;
    String login ;
    String origine ;

    ArrayList<String> listRecouv = new ArrayList<>() ;
    ArrayList<Personnel> listRecouvreur = new ArrayList<Personnel>() ;

    DateFormat df  = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    DateFormat dfSQL = new SimpleDateFormat("dd/MM/yyyy");

    SimpleDateFormat formatDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    ConnectionClass connectionClass;
    String user, password, base, ip;

    public ListLivRecTaskForSearchableSpinner(Activity activity , SearchableSpinner sp_client ,  String origine) {
        this.activity = activity;
        this.sp_client = sp_client ;
        this.origine = origine ;


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
                String query = " select  CodeRespensable  , Nom   from  Respensable  \n" +
                        "where CodeRespensable  in (select CodeRepresentant  from  Utilisateur  where CodeFonction  in (  'FN007','FN008' ) )\n" +
                        "and CodeRespensable in ( select   CodeRepresentant from  BonLivraisonVente where NumeroEtatPaiement = 'E07' ) \n" ;

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                Log.e("query", query) ;


                listRecouv.clear();
                listRecouv.add("Tout les Recouvreurs");

                listRecouvreur .add(new Personnel("","Tout les Recouvreurs"))  ;

                while ( rs.next() ) {


                    String CodeRespensable = rs.getString("CodeRespensable") ;
                    String Nom =rs.getString("Nom") ;


                     Personnel  personnel = new Personnel(CodeRespensable ,Nom) ;
                     listRecouvreur.add(personnel) ;
                    listRecouv.add(personnel.getNomResponsable())  ;
                     Log.e("Recouvreur", personnel.getCodeResponsable()+ " - " +personnel.getNomResponsable());


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


        SpinnerAdapter adapter = new SpinnerAdapter(activity  , listRecouv)  ;
        sp_client.setAdapter(adapter);



        sp_client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                Log.e("Recouvreur_selected"  ,""+listRecouvreur.get(position).toString())  ;

                if (origine.equals("ClientMenuFragment"))
                {
                    ClientMenuFragment.CodeRecouvreurSeleted  = listRecouvreur.get(position).getCodeResponsable()  ;
                    ClientMenuFragment.NomRecouvreurSeleted  = listRecouvreur.get(position).getNomResponsable()  ;
                }

                else  if (origine.equals("VenteMenuFragment"))
                {

                    VenteMenuFragment.CodeRecouvreurSeleted  = listRecouvreur.get(position).getCodeResponsable()  ;
                    VenteMenuFragment.NomRecouvreurSeleted  = listRecouvreur.get(position).getNomResponsable()  ;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


    }


}
