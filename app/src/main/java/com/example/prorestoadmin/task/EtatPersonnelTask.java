package com.example.prorestoadmin.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.prorestoadmin.EtatDeStock.main.EtatDeStockPagerAdapter;
import com.example.prorestoadmin.FicheArticle.FicheArticleActivity;
import com.example.prorestoadmin.FicheArticle.ui.main.FicheArticlePagerAdapter;
import com.example.prorestoadmin.adapter.PersonnelAdapterRV;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Depot;
import com.example.prorestoadmin.model.Personnel;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


public class EtatPersonnelTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;

    RecyclerView rv_list_personnel;

    ProgressBar pb_chargement;
    TextView txt_tot_liv, txt_tot_retour, txt_tot_gratuit, txt_tot_draft_reg;
    TextView  txt_tot_livraison_ttc ,txt_tot_retour_ttc ;

    TextView  txt_prix_moy_pondere ;
    String CodeDepot;

    ConnectionClass connectionClass;
    String user, password, base, ip;

    ArrayList<Personnel> listPersonnel = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    int tot_qt_livraison = 0;
    double tot_ttc_livraison  =  0 ;

    int tot_qt_Retour = 0;
    double tot_ttc_retour  =  0 ;

    int tot_qt_Gratuite = 0;
    double tot_draft_reglement = 0;
    double tot_caisse = 0;
    Date date_etat;
    String CodeFonction;

    public EtatPersonnelTask(Activity activity, Date date_etat, String CodeFonction, RecyclerView rv_list_personnel, ProgressBar pb_chargement,
                             TextView txt_tot_liv, TextView txt_tot_retour, TextView txt_tot_gratuit, TextView txt_tot_draft_reg, TextView  txt_tot_livraison_ttc ,TextView txt_tot_retour_ttc
                             , TextView  txt_prix_moy_pondere  )
    {
        this.activity = activity;

        this.date_etat = date_etat;
        this.CodeFonction = CodeFonction;
        this.pb_chargement = pb_chargement;
        this.rv_list_personnel = rv_list_personnel;
        this.pb_chargement = pb_chargement;


        this.txt_tot_liv = txt_tot_liv;
        this.txt_tot_retour = txt_tot_retour;
        this.txt_tot_gratuit = txt_tot_gratuit;
        this.txt_tot_draft_reg = txt_tot_draft_reg;
        this.txt_tot_livraison_ttc = txt_tot_livraison_ttc;
        this.txt_tot_retour_ttc= txt_tot_retour_ttc;
        this.txt_prix_moy_pondere=txt_prix_moy_pondere ;


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

                String query = "  select * from (\n" +
                        " select  CodeRespensable , Respensable.Nom  ,Utilisateur.CodeFonction  , Fonction.Libelle as   Fonction \n" +
                        "    , Utilisateur.NomUtilisateur  \n" +
                        "    \n" +
                        "    ,(select  ISNULL ( SUM  ( Quantite )  ,0) as QTLivraison from  LigneBonLivraisonVente as LIVRAISION\n" +
                        "    inner join BonLivraisonVente  on BonLivraisonVente.NumeroBonLivraisonVente  = LIVRAISION.NumeroBonLivraisonVente\n" +
                        "    where BonLivraisonVente.UtilisateurIntervenant  =  Utilisateur.NomUtilisateur\n" +
                        "    and    DateBonLivraisonVente  =  '" + sdf.format(date_etat) + "'\n" +
                        "    and NumeroEtat <> 'E00' ) as QTLivraison  \n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    ,(select  ISNULL ( SUM  ( MontantTTC )  ,0) as QTLivraison from  LigneBonLivraisonVente as LIVRAISION\n" +
                        "    inner join BonLivraisonVente  on BonLivraisonVente.NumeroBonLivraisonVente  = LIVRAISION.NumeroBonLivraisonVente\n" +
                        "    where BonLivraisonVente.UtilisateurIntervenant  =  Utilisateur.NomUtilisateur\n" +
                        "    and    DateBonLivraisonVente  =  '" + sdf.format(date_etat) + "'\n" +
                        "    and NumeroEtat <> 'E00' ) as TTCLivraison  \n" +
                        "    \n" +
                        "    \n" +
                        "    ,(select  ISNULL ( SUM  ( Quantite ),0)from  LigneBonRetourVente \n" +
                        "    inner join BonRetourVente  on BonRetourVente.NumeroBonRetourVente  = LigneBonRetourVente.NumeroBonRetourVente\n" +
                        "    where BonRetourVente.NomUtilisateur  =  Utilisateur.NomUtilisateur\n" +
                        "    and    DateBonRetourVente  =  '" + sdf.format(date_etat) + "' ) as QTRetour\n" +
                        "    \n" +
                        "    \n" +
                        "        ,(select  ISNULL ( SUM  ( TotalTTC ),0)from  LigneBonRetourVente \n" +
                        "    inner join BonRetourVente  on BonRetourVente.NumeroBonRetourVente  = LigneBonRetourVente.NumeroBonRetourVente\n" +
                        "    where BonRetourVente.NomUtilisateur  =  Utilisateur.NomUtilisateur\n" +
                        "    and    DateBonRetourVente  =  '" + sdf.format(date_etat) + "' ) as TTCRetour\n" +
                        "    \n" +
                        "    \n" +
                        "    \n" +
                        "    ,(select ISNULL (SUM  (  Quantite )  ,0   ) from  LigneBonGratuiteVente \n" +
                        "    inner join BonGratuiteVente  on BonGratuiteVente.NumeroBonGratuiteVente  = LigneBonGratuiteVente.NumeroBonGratuiteVente\n" +
                        "    where BonGratuiteVente.NomUtilisateur  =  Utilisateur.NomUtilisateur\n" +
                        "    and    DateBonGratuiteVente=  '" + sdf.format(date_etat) + "' ) as QTGratuite\n" +
                        "    \n" +
                        "    \n" +
                        "    ,(select ISNULL (SUM ( Montant ) ,0   )  from  DraftReglementClient \n" +
                        "     \n" +
                        "    where DraftReglementClient.NomUtilisateur  =  Utilisateur.NomUtilisateur\n" +
                        "    and   CONVERT (date ,  DateDraftClient )  =  '" + sdf.format(date_etat) + "' \n" +
                        "     and  Annuler = 0 ) as TotalDraftRèglement\n" +
                        "    \n" +

                        "    from Respensable \n" +
                        "  \n" +
                        "    \n" +
                        "    INNER JOIN Utilisateur  on Utilisateur.CodeRepresentant = Respensable.CodeRespensable\n" +
                        "    INNER JOIN Fonction  on Fonction.CodeFonction = Utilisateur.CodeFonction \n" +
                        "     \n" +
                        "    where /*Utilisateur. Actif = 1    and*/ Fonction.CodeFonction in    " + CodeFonction + "  --and LIVRAISION.QTLivraison  <>  0 -- and  QTRetour <> 0 and  QTGratuite <> 0 and  TotalDraftRèglement <> 0 \n" +
                        "    GROUP  BY CodeRespensable , Respensable.Nom   ,Utilisateur.CodeFonction  , Fonction.Libelle , Utilisateur.NomUtilisateur\n" +
                        "  \n" +
                        " \n" +
                        ") as t\n" +
                        "where   QTLivraison<>0 or QTRetour <> 0  or QTGratuite<> 0  or  TotalDraftRèglement  <>  0  \n" +
                        " \n" +
                        "   ORDER  BY t.CodeRespensable  ";


                Log.e("query_list_personnel", query);
                listPersonnel.clear();

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);


                while (rs.next()) {

                    String CodeRespensable = rs.getString("CodeRespensable");
                    String Nom = rs.getString("Nom");
                    String CodeFonction = rs.getString("CodeFonction");

                    String Fonction = rs.getString("Fonction");
                    String NomUtilisateur = rs.getString("NomUtilisateur");
                    int QTLivraison = rs.getInt("QTLivraison");
                    double TTCLivraison = rs.getDouble("TTCLivraison");

                    int QTRetour = rs.getInt("QTRetour");
                    double  TTCRetour  = rs.getDouble("TTCRetour");

                    int QTGratuite = rs.getInt("QTGratuite");
                    double TotalDraftRèglement = rs.getDouble("TotalDraftRèglement");
                    //double caisse = rs.getDouble("caisse");

                    Personnel personnel = new Personnel(CodeRespensable, Nom, CodeFonction, Fonction, NomUtilisateur, QTLivraison,TTCLivraison , QTRetour,TTCRetour , QTGratuite, TotalDraftRèglement);
                    listPersonnel.add(personnel);

                    tot_qt_livraison = tot_qt_livraison + QTLivraison;
                    tot_ttc_livraison = tot_ttc_livraison + TTCLivraison;

                    tot_qt_Retour = tot_qt_Retour + QTRetour;
                    tot_ttc_retour = tot_ttc_retour + TTCRetour ;

                    tot_qt_Gratuite = tot_qt_Gratuite + QTGratuite;
                    tot_draft_reglement = tot_draft_reglement + TotalDraftRèglement;

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

        PersonnelAdapterRV adapterRV = new PersonnelAdapterRV(activity, listPersonnel , date_etat);
        rv_list_personnel.setAdapter(adapterRV);

        NumberFormat formatter = new DecimalFormat("0.000");

        txt_tot_liv.setText(tot_qt_livraison + "");
        txt_tot_retour.setText(tot_qt_Retour + "");
        txt_tot_gratuit.setText(tot_qt_Gratuite + "");

        txt_tot_draft_reg.setText(formatter.format(tot_draft_reglement) + "Dt");

        txt_tot_livraison_ttc.setText(formatter.format(tot_ttc_livraison) + "Dt");
        txt_tot_retour_ttc.setText(formatter.format(tot_ttc_retour) + "Dt");

        double  prix_moy_pondere  = (tot_ttc_livraison-tot_ttc_retour) / (tot_qt_livraison - tot_qt_Retour) ;

        txt_prix_moy_pondere.setText(formatter.format(prix_moy_pondere) + "Dt");

    }






}
