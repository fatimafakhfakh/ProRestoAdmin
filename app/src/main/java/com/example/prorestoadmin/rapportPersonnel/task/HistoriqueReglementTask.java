package com.example.prorestoadmin.rapportPersonnel.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.prorestoadmin.adapter.DraftReglementHistRVAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.BonRetourVente;
import com.example.prorestoadmin.model.LigneBonRetourVente;
import com.example.prorestoadmin.model.draft.DetailDraftReglementClient;
import com.example.prorestoadmin.model.draft.DraftReglement;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.model.draft.DraftReglement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


public class HistoriqueReglementTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity ;
    String codeLivreur ;

    Date  date_debut   , date_fin  ;
    RecyclerView rv_list_draft ;
    ProgressBar progressBar ;
    ConnectionClass connectionClass ;
    String user, password, base, ip ;


    ArrayList<DraftReglement> listDraftReglement = new ArrayList<>();
    SimpleDateFormat df  = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat       dtfSQL    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    String   NomUtilisateur ;

    public HistoriqueReglementTask(Activity activity, String codeLivreur, String NomUtilisateur   , Date  date_debut , Date date_fin  , RecyclerView rv_list_draft, ProgressBar progressBar) {
        this.activity = activity;
        this.rv_list_draft = rv_list_draft;
        this.progressBar = progressBar;
        this.codeLivreur = codeLivreur  ;
        this.NomUtilisateur = NomUtilisateur  ;

        this.date_debut= date_debut  ;
        this.date_fin=date_fin ;

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

                String query = " select \n" +
                        "        NumeroDraftClient ,  \n" +
                        "        HeureCreation as HeureDraft ,\n" +
                        "        CodeClient ,\n" +
                        "        RaisonSociale ,\n" +
                        "        Montant ,\n" +
                        "        (case when  Annuler = 1 then 'Annulé' \n" +
                        "         else case when Cloturer =  1 then  'clôturé' \n" +
                        "         else  '' end end   ) as etat \n" +
                        "        \n" +
                        "from DraftReglementClient \n" +
                        "\n" +
                        "    where  NomUtilisateur  = '"+NomUtilisateur+"'  \n" +
                        " and DateDraftClient  between  '" + df.format(date_debut)+"' and '" + df.format(date_fin)+"'\n" +
                        " order by HeureCreation  ";




                Log.e("query_his_reg", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String NumeroDraftClient = rs.getString("NumeroDraftClient");
                    Date HeureDraft = dtfSQL.parse(rs.getString("HeureDraft"));
                    String CodeClient = rs.getString("CodeClient");
                    String RaisonSociale = rs.getString("RaisonSociale");
                    double Montant = rs.getDouble("Montant");
                    String etat = rs.getString("etat");



                    String query_detail_draft = " select NumeroPiece , MontantPieceTTC  , TotalRecuDraft from DetailDraftReglementClient where NumeroDraftClient  = '"+NumeroDraftClient+"'   "  ;
                    Log.e("query_detail_retour", query_detail_draft);

                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query_detail_draft);
                    ArrayList<DetailDraftReglementClient>  listLigneDetailDraft = new ArrayList<>() ;

                    while (rs2.next()) {

                        String NumeroPiece = rs2.getString("NumeroPiece");
                        double MontantPieceTTC = rs2.getDouble("MontantPieceTTC");
                        double TotalRecuDraft = rs2.getDouble("TotalRecuDraft");

                        DetailDraftReglementClient  ligneDetailDraft  = new DetailDraftReglementClient(NumeroPiece ,MontantPieceTTC ,TotalRecuDraft ) ;
                        listLigneDetailDraft.add(ligneDetailDraft) ;

                    }


                    DraftReglement   dr  = new DraftReglement(NumeroDraftClient,HeureDraft ,CodeClient ,RaisonSociale ,Montant ,etat  );

                    dr.setDetailDraftReg(listLigneDetailDraft);
                    listDraftReglement.add(dr) ;




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

        DraftReglementHistRVAdapter adapter  = new DraftReglementHistRVAdapter(activity , listDraftReglement) ;
        rv_list_draft.setAdapter(adapter);



    }


}
