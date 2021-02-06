package com.example.prorestoadmin.ui.client.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.prorestoadmin.adapter.BonLivraisonHistRVAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.BonLivraisonVente;
import com.example.prorestoadmin.model.LigneBonLivraisonVente;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prorestoadmin.R ;


public class GetTauxRecouvrementTask extends AsyncTask<String, String, String>  {

    String res;

    Activity activity ;


    CircularProgressBar cpb_taux_recouvrement ;
    TextView txt_taux_recouvrement ;

    TextView txt_tot_vente ;
    TextView txt_tot_retour ;
    TextView txt_tot_reg_j ;
    TextView txt_tot_recouvrement ;
    TextView    txt_tot_credit ;

    ConnectionClass connectionClass ;
    String user, password, base, ip ;


    SimpleDateFormat df  = new SimpleDateFormat("dd/MM/yyyy");


    double TotalVente , TotalRetour  ,totalReg_j ,totalRecouvrement ;



  /*


    SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateFormat       dtfSQL    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    */

    public GetTauxRecouvrementTask(Activity activity,  CircularProgressBar cpb_taux_recouvrement  ,  TextView txt_taux_recouvrement
    , TextView txt_tot_vente , TextView txt_tot_retour ,   TextView txt_tot_reg_j ,TextView txt_tot_recouvrement ,TextView txt_tot_credit ) {
        this.activity = activity;
        this.cpb_taux_recouvrement = cpb_taux_recouvrement;
        this.txt_taux_recouvrement = txt_taux_recouvrement;

        this.txt_tot_vente  = txt_tot_vente ;
        this.txt_tot_reg_j = txt_tot_reg_j ;
        this.txt_tot_retour = txt_tot_retour ;
        this. txt_tot_credit=txt_tot_credit ;
        this.txt_tot_recouvrement    =txt_tot_recouvrement ;


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


    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
            Log.e("con", "" + con);
            if (con == null) {
                res = "Check Your Internet Access!";
            } else {

                String query = " select (\n" +
                        "    \n" +
                        "    (select ISNULL (  SUM(TotalTTC) , 0 )  as TotalVente   from BonLivraisonVente\n" +
                        "     where DateBonLivraisonVente = '"+df.format(new Date())+"'\n" +
                        "     and NumeroEtat <>'E00'  )  ) as TotalVente\n" +
                        "    \n" +
                        "    ,\n" +
                        "      \n" +
                        "    (select ISNULL (  SUM(BonRetourVente.TotalTTC) , 0 )  as TotalRetour   from BonRetourVente\n" +
                        "     inner Join LigneBonRetourVente on LigneBonRetourVente.NumeroBonRetourVente = BonRetourVente.NumeroBonRetourVente\n" +
                        "     inner Join BonLivraisonVente on BonLivraisonVente.NumeroBonLivraisonVente = LigneBonRetourVente.NumeroBonLivraisonVente\n" +
                        "     where DateBonRetourVente = '"+df.format(new Date())+"'\n" +
                        "     and BonLivraisonVente.DateBonLivraisonVente = '"+df.format(new Date())+"'\n" +
                        "      ) as TotalRetour\n" +
                        "    ,\n" +
                        "     (select ISNULL ( SUM(TotalPayee) , 0 )   as totalDraft from DetailDraftReglementClient \n" +
                        "     inner join DraftReglementClient on DraftReglementClient.NumeroDraftClient  = DetailDraftReglementClient.NumeroDraftClient\n" +
                        "     inner  JOIN BonLivraisonVente on BonLivraisonVente.NumeroBonLivraisonVente  = DetailDraftReglementClient.NumeroPiece\n" +
                        "     where BonLivraisonVente.DateBonLivraisonVente  = '"+df.format(new Date())+"'     \n" +
                        "     and  DraftReglementClient.DateDraftClient  = '"+df.format(new Date())+"'    \n" +
                        "      and  DraftReglementClient.Annuler =0  )as totalReg_j  \n" +
                        "\n" +
                        "      ,\n" +
                        "      \n" +
                        "     (select ISNULL ( SUM(TotalPayee) , 0 )   as totalDraft from DetailDraftReglementClient \n" +
                        "     inner join DraftReglementClient on DraftReglementClient.NumeroDraftClient  = DetailDraftReglementClient.NumeroDraftClient\n" +
                        "     inner  JOIN BonLivraisonVente on BonLivraisonVente.NumeroBonLivraisonVente  = DetailDraftReglementClient.NumeroPiece\n" +
                        "     where BonLivraisonVente.DateBonLivraisonVente  <> '"+df.format(new Date())+"' \n" +
                        "     and  DraftReglementClient.DateDraftClient  = '"+df.format(new Date())+"' \n" +
                        "     and  DraftReglementClient.Annuler =0 ) as totalRecouvrement  ";


                Log.e("query_Taux_recouvr", query );

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                      TotalVente = rs.getDouble("TotalVente");
                      TotalRetour = rs.getDouble("TotalRetour") ;
                      totalReg_j = rs.getDouble("totalReg_j");
                      totalRecouvrement= rs.getDouble("totalRecouvrement");

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



        double taux_recouvrement  = totalReg_j /(TotalVente-TotalRetour)   *100  ;

        DecimalFormat  decF  = new DecimalFormat("0.000") ;
        txt_taux_recouvrement.setText(decF.format(taux_recouvrement) +" %");


        cpb_taux_recouvrement.setBackgroundColor(ContextCompat.getColor(activity, R.color.tab_background_unselected));
        cpb_taux_recouvrement.setProgressBarWidth(activity.getResources().getDimension(R.dimen.progressBarWidth));
        cpb_taux_recouvrement.setBackgroundProgressBarWidth(activity.getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500; // 2500ms = 2,5s

        cpb_taux_recouvrement.setProgressWithAnimation((float)(taux_recouvrement) , animationDuration); // Default duration = 1500ms
        cpb_taux_recouvrement.setColor(ContextCompat.getColor(activity, R.color.color_g_7));
        // circularProgressBar.setCol


        txt_tot_vente.setText(decF.format(TotalVente)+ "");
        txt_tot_retour.setText(decF.format(TotalRetour)+ "");
        txt_tot_reg_j.setText(decF.format(totalReg_j)+ "");

        txt_tot_recouvrement.setText(decF.format(totalRecouvrement)+ "");


        double  credit   = TotalVente-TotalRetour-totalReg_j  ;
        txt_tot_credit.setText(decF.format(credit)+ "");


    }


}
