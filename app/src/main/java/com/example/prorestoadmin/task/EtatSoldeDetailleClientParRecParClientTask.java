package com.example.prorestoadmin.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;


import com.example.prorestoadmin.adapter.DetailSoldeClientRVAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.ClientRappelPaiment;
import com.example.prorestoadmin.model.PieceRappelPaiment;
import com.example.prorestoadmin.model.RappelPaiementClientGlobal;
import com.example.prorestoadmin.model.SoldeClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;


public class EtatSoldeDetailleClientParRecParClientTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;
    String _codeClient;
    String _codeRec;

    Date date_debut, date_fin;
    RecyclerView rv_list_detail_solde;
    ProgressBar progressBar;
    SearchView searchViewClient;
    ConnectionClass connectionClass;
    String user, password, base, ip;


    FloatingActionButton fab_arrow;
    RelativeLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;


    ArrayList<RappelPaiementClientGlobal> listRappelPaiementClientGlobal = new ArrayList<>();
    ArrayList<SoldeClient> listSoldeCLient = new ArrayList<>();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat dtfSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public EtatSoldeDetailleClientParRecParClientTask(Activity activity, String CodeClient, String CodeRecouvreur, Date date_debut, Date date_fin, RecyclerView rv_list_detail_solde, ProgressBar progressBar, SearchView searchViewClient) {
        this.activity = activity;
        this.rv_list_detail_solde = rv_list_detail_solde;
        this.progressBar = progressBar;
        this._codeClient = CodeClient;
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


                String query_solde = "DECLARE\t@return_value int\n" +
                        "\n" +
                        "EXEC\t@return_value = [dbo].[SoldeClients]\n" +
                        "\t\t@DateAu = '" + df.format(new Date()) + "'\n" +
                        "\n" +
                        "SELECT\t'Return Value' = @return_value  ";


                Log.e("query_solde", query_solde);

                Statement stmt_solde = con.createStatement();
                ResultSet rs_solde = stmt_solde.executeQuery(query_solde);

                while (rs_solde.next()) {

                    String CodeClient = rs_solde.getString("CodeClient");
                    double soldeClient = rs_solde.getDouble("TotalDebit") - rs_solde.getDouble("TotalCredit");

                    SoldeClient soldeClient_1 = new SoldeClient(CodeClient, soldeClient);
                    listSoldeCLient.add(soldeClient_1);

                }


                String query = " DECLARE\t@return_value int\n" +
                        "\n" +
                        "EXEC\t@return_value = [dbo].[RappelPaiementClientGlobal]\n" +
                        "\t\t@DateDebut = '" + df.format(date_debut) + "',\n" +
                        "\t\t@DateFin = '" + df.format(date_fin) + "'\n" +
                        "\n" +
                        "SELECT\t'Return Value' = @return_value" +
                        "   ";


                Log.e("query_detail_solde", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String TypePiece = rs.getString("TypePiece");
                    String CodeDepot = rs.getString("CodeDepot");
                    String NumeroPiece = rs.getString("NumeroPiece");
                    String CodeClient = rs.getString("CodeClient");
                    String RaisonSociale = rs.getString("RaisonSociale");
                    String CodeRepresentant = rs.getString("CodeRepresentant");
                    String CodeZone = rs.getString("CodeZone");
                    String CodeVille = rs.getString("CodeVille");

                    String CodeSousRegion = rs.getString("CodeSousRegion");
                    Date DatePiece = dtfSQL.parse(rs.getString("DatePiece"));


                    double Debit = rs.getDouble("Debit");
                    double Credit = rs.getDouble("Credit");
                    double RecuDraft = rs.getDouble("RecuDraft");

                    if (Debit != 0)
                    {

                        if (_codeClient.equals("")) {
                            if (_codeRec.equals("")) {

                                RappelPaiementClientGlobal rappelPaiementClientGlobal = new RappelPaiementClientGlobal(TypePiece, CodeDepot, NumeroPiece, CodeClient, RaisonSociale, CodeRepresentant, CodeZone, CodeVille, CodeSousRegion, DatePiece, Debit, Credit, RecuDraft);
                                listRappelPaiementClientGlobal.add(rappelPaiementClientGlobal);
                                Log.e("R.Pai.Client.Global", rappelPaiementClientGlobal.toString());
                            } else if (!_codeRec.equals("")) {

                                if (CodeRepresentant.equals(_codeRec)) {
                                    RappelPaiementClientGlobal rappelPaiementClientGlobal = new RappelPaiementClientGlobal(TypePiece, CodeDepot, NumeroPiece, CodeClient, RaisonSociale, CodeRepresentant, CodeZone, CodeVille, CodeSousRegion, DatePiece, Debit, Credit, RecuDraft);
                                    listRappelPaiementClientGlobal.add(rappelPaiementClientGlobal);
                                    Log.e("R.Pai.Client.Global", rappelPaiementClientGlobal.toString());

                                }

                            }

                        } else if (!_codeClient.equals("")) {

                            if (CodeClient.equals(_codeClient) && _codeRec.equals("")) {

                                RappelPaiementClientGlobal rappelPaiementClientGlobal = new RappelPaiementClientGlobal(TypePiece, CodeDepot, NumeroPiece, CodeClient, RaisonSociale, CodeRepresentant, CodeZone, CodeVille, CodeSousRegion, DatePiece, Debit, Credit, RecuDraft);
                                listRappelPaiementClientGlobal.add(rappelPaiementClientGlobal);
                                Log.e("R.Pai.Client.Global", rappelPaiementClientGlobal.toString());
                            } else if (CodeClient.equals(_codeClient) && !_codeRec.equals("")) {

                                if (CodeRepresentant.equals(_codeRec)) {
                                    RappelPaiementClientGlobal rappelPaiementClientGlobal = new RappelPaiementClientGlobal(TypePiece, CodeDepot, NumeroPiece, CodeClient, RaisonSociale, CodeRepresentant, CodeZone, CodeVille, CodeSousRegion, DatePiece, Debit, Credit, RecuDraft);
                                    listRappelPaiementClientGlobal.add(rappelPaiementClientGlobal);
                                    Log.e("R.Pai.Client.Global", rappelPaiementClientGlobal.toString());

                                }

                            }

                        } else if (CodeClient.equals(_codeClient) && CodeRepresentant.equals(_codeRec)) {
                            RappelPaiementClientGlobal rappelPaiementClientGlobal = new RappelPaiementClientGlobal(TypePiece, CodeDepot, NumeroPiece, CodeClient, RaisonSociale, CodeRepresentant, CodeZone, CodeVille, CodeSousRegion, DatePiece, Debit, Credit, RecuDraft);
                            listRappelPaiementClientGlobal.add(rappelPaiementClientGlobal);
                            Log.e("R.Pai.Client.Global", rappelPaiementClientGlobal.toString());
                        }


                    }



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

        Log.e("nbr_bl", "" + listRappelPaiementClientGlobal.size());

        final ArrayList<ClientRappelPaiment> listClientRappelPaiment = new ArrayList<>();

        ArrayList<String> clientDistinct = new ArrayList<>();

        //  groupage  par  client
        //  liste client de la liste
        for (RappelPaiementClientGlobal rpcg : listRappelPaiementClientGlobal) {

            if (!clientDistinct.contains(rpcg.getCodeClient().toLowerCase().trim()))
                clientDistinct.add(rpcg.getCodeClient().toLowerCase().trim());

        }

        Log.e("list_clients_distinct", clientDistinct.toString());
        for (String codeClient : clientDistinct) {

            ClientRappelPaiment clientRappelPaiment = new ClientRappelPaiment();

            double TotalMontant = 0;
            double TotalReglement = 0;
            double TotalResteAPayer = 0;
            double TotalRecuDraft = 0;
            ArrayList<PieceRappelPaiment> listPieceRappelPaiment = new ArrayList<>();


            for (RappelPaiementClientGlobal rpcg : listRappelPaiementClientGlobal) {


                if (codeClient.equals(rpcg.getCodeClient())) {

                    PieceRappelPaiment pieceRappelPaiment = new PieceRappelPaiment(rpcg.getDatePiece(), rpcg.getTypePiece(), rpcg.getNumeroPiece(), rpcg.getDedit(), rpcg.getCredit(), (rpcg.getDedit() - rpcg.getCredit()), rpcg.getRecuDraft());

                    listPieceRappelPaiment.add(pieceRappelPaiment);
                    TotalMontant = TotalMontant + rpcg.getDedit();
                    TotalReglement = TotalReglement + rpcg.getCredit();
                    TotalRecuDraft = TotalRecuDraft + rpcg.getRecuDraft();

                    TotalResteAPayer = TotalResteAPayer + rpcg.getDedit() - rpcg.getCredit();
                    clientRappelPaiment.setCodeClient(rpcg.getCodeClient());
                    clientRappelPaiment.setRaisonSocial(rpcg.getRaisonSociale());
                }

            }


            for (SoldeClient sc : listSoldeCLient) {
                if (sc.getCodeClient().equals(codeClient)) {
                    clientRappelPaiment.setSoldeClient(sc.getSolde());
                    break;
                }
            }


            clientRappelPaiment.setTotalMontant(TotalMontant);
            clientRappelPaiment.setTotalReglement(TotalReglement);
            clientRappelPaiment.setTotalResteAPayer(TotalResteAPayer);
            clientRappelPaiment.setTotalRecuDraft(TotalRecuDraft);
            clientRappelPaiment.setListRappelPaiment(listPieceRappelPaiment);


            listClientRappelPaiment.add(clientRappelPaiment);
        }


        Log.e("listClientRappelPaiment", "" + listClientRappelPaiment.toString());


        DetailSoldeClientRVAdapter adapter = new DetailSoldeClientRVAdapter(activity, listClientRappelPaiment);
        rv_list_detail_solde.setAdapter(adapter);


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

                final ArrayList<ClientRappelPaiment> fitler_list_client = filter(listClientRappelPaiment, query);

                DetailSoldeClientRVAdapter adapter = new DetailSoldeClientRVAdapter(activity, fitler_list_client);
                rv_list_detail_solde.setAdapter(adapter);


                return false;
            }

        });


    }


    private ArrayList<ClientRappelPaiment> filter(ArrayList<ClientRappelPaiment> listClient, String term) {

        term = term.toLowerCase();
        final ArrayList<ClientRappelPaiment> filetrListClient = new ArrayList<>();

        for (ClientRappelPaiment c : listClient) {
            final String txt_raison = c.getRaisonSocial().toLowerCase();

            if (txt_raison.contains(term)) {
                filetrListClient.add(c);
            }
        }
        return filetrListClient;
    }

}
