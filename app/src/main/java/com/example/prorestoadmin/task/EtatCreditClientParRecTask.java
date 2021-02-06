package com.example.prorestoadmin.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.prorestoadmin.adapter.BonLivraisonHistRVAdapter;
import com.example.prorestoadmin.client.CreditClientParRecActivity;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.BonLivraisonVente;
import com.example.prorestoadmin.model.LigneBonLivraisonVente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


public class EtatCreditClientParRecTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;
    String _codeRec;

    Date date_debut, date_fin;
    RecyclerView rv_credit_client;
    ProgressBar progressBar;
    SearchView searchViewClient;
    ConnectionClass connectionClass;
    String user, password, base, ip;

    double  tot_credit  =0 ;

    ArrayList<BonLivraisonVente> listBonLivraisonVente = new ArrayList<>();

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat dtfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public EtatCreditClientParRecTask(Activity activity, String CodeRecouvreur, Date date_debut, Date date_fin, RecyclerView rv_credit_client, ProgressBar progressBar, SearchView searchViewClient) {
        this.activity = activity;
        this.rv_credit_client = rv_credit_client;
        this.progressBar = progressBar;

        this._codeRec = CodeRecouvreur;
        this.searchViewClient = searchViewClient;
        this.date_debut = date_debut;
        this.date_fin = date_fin;

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

                tot_credit =0 ;
                String CONDITION_REC = "";

                if (_codeRec.equals("")) {
                    CONDITION_REC = "" ;
                } else {
                    CONDITION_REC =    "   and CodeRepresentant  = '" + _codeRec + "'\n"  ;
                }
                String query_solde = "       select * from ( select  \n" +
                        "        NumeroBonLivraisonVente ,\n" +
                        "        HeureCreation as HeureLivraison ,\n" +
                        "        CodeClient ,\n" +
                        "        CodeDepot ,\n" +
                        "        RaisonSociale ,\n" +
                        "        TotalTTC ,\n" +
                        "        BonLivraisonVente.NumeroEtat ,\n" +
                        "        Etat.Libelle as etatPayement    ,( select    ISNULL ( NomUtilisateur ,'' )     from ReglementClient where NumeroReglementClient  = (Select top(1) NumeroReglementClient from   LigneReglementClient  where NumeroPiece = NumeroBonLivraisonVente))  as PayéPar     , \n" +
                        "        (select  ISNULL ( SUM  ( TotalPayee  ) ,0)    from LigneReglementClient  where NumeroPiece = NumeroBonLivraisonVente ) as MontantRecuPayement    , NumeroBonGratuiteVente\n" +
                        "        , NumeroFactureVente\n" +
                        "        , BonLivraisonVente.NomUtilisateur as EtabliePar     ,BonLivraisonVente.NumeroEtatPaiement\n" +
                        "        , case when NumeroBonLivraisonVente  in (select NumeroBonLivraisonVente from  LigneBonRetourVente   ) \n" +
                        "        then 'true'\n" +
                        "        else  'false' end as 'Retour' \n" +
                        "        \n" +
                        "        \n" +
                        "        ,case when NumeroBonLivraisonVente  in   (select NumeroPiece from  DetailDraftReglementClient  \n" +
                        "        inner join DraftReglementClient  on  DetailDraftReglementClient.NumeroDraftClient  =   DraftReglementClient.NumeroDraftClient\n" +
                        "        where  DraftReglementClient.Annuler = 0  and   DraftReglementClient.Cloturer =0 )\n" +
                        "         \n" +
                        "        then 'true'\n" +
                        "        else  'false' end as 'Draft' , \n" +
                        "        \n" +
                        "        ( select    ISNULL ( NomUtilisateur ,'' )     from DraftReglementClient where NumeroDraftClient  = (Select top(1) NumeroDraftClient from   DetailDraftReglementClient  where NumeroPiece = BonLivraisonVente.NumeroBonLivraisonVente ) ) as DraftéPar \n" +
                        "               from BonLivraisonVente   \n" +
                        "        INNER  JOIN Etat on  Etat.NumeroEtat = BonLivraisonVente.NumeroEtatPaiement\n" +
                        "        where     CONVERT (date  ,DateBonLivraisonVente)   between   '" + df.format(date_debut) + "'  and   '" + df.format(date_fin) + "'  \n" +
                        "        and BonLivraisonVente.NumeroEtatPaiement  in ('E07','E30')\n" +
                        "        \n" +
                        CONDITION_REC +
                        "\n" +
                        "        ) as CreditClient\n" +
                        "        where Draft = 'false' and  NumeroEtat <> 'E00' \n" +
                        "        order by HeureLivraison \n" +
                        "        ";


                Log.e("query_solde", query_solde);

                Statement stmt_solde = con.createStatement();
                ResultSet rs = stmt_solde.executeQuery(query_solde);

                while (rs.next()) {


                    String NumeroBonLivraisonVente = rs.getString("NumeroBonLivraisonVente");
                    Date HeureLivraison = dtfSQL.parse(rs.getString("HeureLivraison"));
                    String CodeClient = rs.getString("CodeClient");
                    String CodeDepot = rs.getString("CodeDepot");
                    String RaisonSociale = rs.getString("RaisonSociale");
                    double TotalTTC = rs.getDouble("TotalTTC");
                    String etatPayement = rs.getString("etatPayement"); //
                    String NumeroEtat = rs.getString("NumeroEtat"); //

                    String PayéPar = rs.getString("PayéPar");

                    double MontantRecuPayement = rs.getDouble("MontantRecuPayement");

                    String EtabliePar = rs.getString("EtabliePar");


                    String NumeroBonGratuiteVente = rs.getString("NumeroBonGratuiteVente"); //
                    String NumeroFactureVente = rs.getString("NumeroFactureVente"); //
                    String NumeroEtatPaiement = rs.getString("NumeroEtatPaiement"); //
                    boolean Retour = rs.getBoolean("Retour"); //
                    boolean Draft = rs.getBoolean("Draft"); //

                    String DraftéPar = rs.getString("DraftéPar");


                    Log.e("livraison_model", etatPayement + " " + NumeroEtat + " " + Retour + "  -" + NumeroEtatPaiement);


                    String query_detail_livraison = "    select  CodeArticle ,  DesignationArticle  , Quantite from LigneBonLivraisonVente    where NumeroBonLivraisonVente = '" + NumeroBonLivraisonVente + "' ";
                    Log.e("query_detail_livraison", query_detail_livraison);

                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query_detail_livraison);
                    ArrayList<LigneBonLivraisonVente> listLigneBonLivraisonVentes = new ArrayList<>();

                    while (rs2.next()) {

                        String CodeArticle = rs2.getString("CodeArticle");
                        String DesignationArticle = rs2.getString("DesignationArticle");
                        int Quantite = rs2.getInt("Quantite");

                        LigneBonLivraisonVente ligneBL = new LigneBonLivraisonVente(CodeArticle, DesignationArticle, Quantite);
                        listLigneBonLivraisonVentes.add(ligneBL);

                    }

                    BonLivraisonVente bl = new BonLivraisonVente(NumeroBonLivraisonVente, HeureLivraison, CodeClient, RaisonSociale, TotalTTC, MontantRecuPayement, etatPayement, NumeroEtat, CodeDepot, NumeroBonGratuiteVente, NumeroFactureVente, NumeroEtatPaiement, Retour, Draft, EtabliePar, DraftéPar, PayéPar);
                    bl.setNumeroEtatPaiment(NumeroEtatPaiement);

                    bl.setList_ligne_bl(listLigneBonLivraisonVentes);
                    listBonLivraisonVente.add(bl);



                  tot_credit = tot_credit + bl.getTotalTTC()-  bl.getMontantRecuPayement()  ;


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


        DecimalFormat  decFormat  = new DecimalFormat("0.000") ;
        CreditClientParRecActivity.txt_tot_credit.setText(decFormat.format(tot_credit) +" Dt");

        BonLivraisonHistRVAdapter adapter = new BonLivraisonHistRVAdapter(activity, listBonLivraisonVente);
        rv_credit_client.setAdapter(adapter);


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

                final ArrayList<BonLivraisonVente> fitler_list_client = filter(listBonLivraisonVente, query);

                BonLivraisonHistRVAdapter adapter = new BonLivraisonHistRVAdapter(activity, fitler_list_client);
                rv_credit_client.setAdapter(adapter);


                return false;
            }

        });


    }


    private ArrayList<BonLivraisonVente> filter(ArrayList<BonLivraisonVente> listBL, String term) {

        term = term.toLowerCase();
        final ArrayList<BonLivraisonVente> filetrListClient = new ArrayList<>();

        for (BonLivraisonVente bl : listBL) {
            final String txt_raison = bl.getRaisonSociale().toLowerCase();

            if (txt_raison.contains(term)) {
                filetrListClient.add(bl);
            }
        }
        return filetrListClient;
    }

}
