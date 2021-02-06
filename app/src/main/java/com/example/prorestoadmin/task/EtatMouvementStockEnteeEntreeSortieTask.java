package com.example.prorestoadmin.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DatabaseErrorHandler;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.prorestoadmin.EntreStock.EtatEntreStockActivity;
import com.example.prorestoadmin.adapter.TotaliteProductionStockRVAdapter;
import com.example.prorestoadmin.adapter.TotaliteStockAdapter;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Depot;
import com.example.prorestoadmin.model.MouvementArticle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class EtatMouvementStockEnteeEntreeSortieTask extends AsyncTask<String, String, String> {

    String res;

    AppCompatActivity activity;

    RecyclerView  etat_production_entree_sortie ;
    Date  date_debut  , date_fin ;

    String Origine;
    ConnectionClass connectionClass;
    String user, password, base, ip;

    ArrayList<MouvementArticle> listMouvementArticle = new ArrayList<>();

    SimpleDateFormat   sdf  = new SimpleDateFormat("dd/MM/yyyy") ;

    int  tot_prod  =0, tot_sortie_transfert =0 , tot_entree_transfert =0   ,tot_retour =0  , tot_liv =0 ,tot_gratuit =0 ;


    public EtatMouvementStockEnteeEntreeSortieTask(AppCompatActivity activity,  Date  date_debut  ,Date date_fin   , RecyclerView  etat_production_entree_sortie , String Origine) {
        this.activity = activity;
        this.date_debut  = date_debut ;
        this.date_fin  =date_fin ;
        this.etat_production_entree_sortie = etat_production_entree_sortie ;
        this.Origine = Origine;


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

                String query = "select  CodeArticle ,DesignationArticle , SUM (Quantite) as QtProd  \n" +
                        "\n" +
                        "\n" +
                        ", (select SUM (Quantite)  from LigneBonTransfert \n" +
                        "  INNER JOIN BonTransfert  on BonTransfert.NumeroBonTransfert  = LigneBonTransfert.NumeroBonTransfert \n" +
                        "  where Convert (Date ,DateBonTransfert ) between   '"+sdf.format(date_debut)+"'  and  '"+sdf.format(date_fin)+"'  \n" +
                        "  and CodeDepotSource = '01' and CodeArticle  = LigneBonEntrer.CodeArticle)  as QtSortieTransfert\n" +
                        "\n" +
                        "\n" +
                        ", (select SUM (Quantite)  from LigneBonTransfert \n" +
                        "  INNER JOIN BonTransfert  on BonTransfert.NumeroBonTransfert  = LigneBonTransfert.NumeroBonTransfert \n" +
                        "  where Convert (Date ,DateBonTransfert ) between  '"+sdf.format(date_debut)+"'  and  '"+sdf.format(date_fin)+"'  \n" +
                        "  and CodeDepotDestination= '01' and CodeArticle  = LigneBonEntrer.CodeArticle)  as QtEntreeTransfert\n" +
                        " \n" +
                        " \n" +
                        " , (select ISNULL (SUM (Quantite) ,0 ) from LigneBonRetourVente \n" +
                        "  INNER JOIN BonRetourVente   on BonRetourVente .NumeroBonRetourVente  = LigneBonRetourVente.NumeroBonRetourVente \n" +
                        "  where Convert (Date ,DateBonRetourVente ) between  '"+sdf.format(date_debut)+"'  and  '"+sdf.format(date_fin)+"'  \n" +
                        "  and CodeDepot = '01' and CodeArticle  = LigneBonEntrer.CodeArticle)  as QtRetour\n" +
                        " \n" +
                        " \n" +
                        "  , (select ISNULL (SUM (Quantite) ,0 ) from LigneBonLivraisonVente \n" +
                        "  INNER JOIN BonLivraisonVente    on BonLivraisonVente .NumeroBonLivraisonVente  = LigneBonLivraisonVente.NumeroBonLivraisonVente\n" +
                        "  where Convert (Date ,DateBonLivraisonVente ) between '"+sdf.format(date_debut)+"'  and  '"+sdf.format(date_fin)+"'  \n" +
                        "  and CodeDepot = '01' and CodeArticle  = LigneBonEntrer.CodeArticle)  as QtLivraison\n" +
                        "  \n" +
                        "  \n" +
                        "    , (select ISNULL (SUM (Quantite) ,0 ) from LigneBonGratuiteVente \n" +
                        "  INNER JOIN BonGratuiteVente    on BonGratuiteVente .NumeroBonGratuiteVente  = LigneBonGratuiteVente.NumeroBonGratuiteVente\n" +
                        "  where Convert (Date ,DateBonGratuiteVente ) between  '"+sdf.format(date_debut)+"'  and  '"+sdf.format(date_fin)+"'  \n" +
                        "  and CodeDepot = '01' and CodeArticle  = LigneBonEntrer.CodeArticle)  as QtGratuit" +
                        "" +
                        "\n" +
                        " from LigneBonEntrer \n" +
                        " INNER JOIN BonEntrer  on BonEntrer.NumeroBonEntrer  =  LigneBonEntrer.NumeroBonEntrer\n" +
                        " where  Convert (Date ,DateBonEntrer ) between  '"+sdf.format(date_debut)+"'  and  '"+sdf.format(date_fin)+"'   \n" +
                        " and CodeDepot  = '01'\n" +
                        " Group By CodeArticle ,DesignationArticle \n" +
                        "   ";


                Log.e("query_tot_stock", query);

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String CodeArticle = rs.getString("CodeArticle");
                    String DesignationArticle = rs.getString("DesignationArticle");

                    int    QtProd  = rs.getInt("QtProd") ;
                    int    QtSortieTransfert  = rs.getInt("QtSortieTransfert") ;
                    int    QtEntreeTransfert  = rs.getInt("QtEntreeTransfert") ;

                    int    QtRetour  = rs.getInt("QtRetour") ;
                    int    QtLivraison  = rs.getInt("QtLivraison") ;
                    int    QtGratuit  = rs.getInt("QtGratuit") ;

                    tot_prod=tot_prod +QtProd ;
                    tot_sortie_transfert = tot_sortie_transfert +QtSortieTransfert ;
                    tot_entree_transfert = tot_entree_transfert +QtEntreeTransfert ;

                    tot_retour = tot_retour +QtRetour ;
                    tot_liv =  tot_liv+QtLivraison ;
                    tot_gratuit =tot_gratuit + QtGratuit ;

                    MouvementArticle  mouvementArticle = new MouvementArticle(CodeArticle  ,DesignationArticle , QtProd ,QtSortieTransfert ,QtEntreeTransfert ,QtRetour ,QtLivraison ,QtGratuit) ;
                    listMouvementArticle.add(mouvementArticle) ;

                }

                listMouvementArticle.add(new MouvementArticle("Total" , "",tot_prod  ,tot_sortie_transfert , tot_entree_transfert  ,tot_retour , tot_liv  ,tot_gratuit)) ;

            }
            con.close();

        } catch (Exception ex) {

            res = ex.getMessage();
            Log.e("ERROR_depot", res.toString());
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


        TotaliteProductionStockRVAdapter adapter1  = new TotaliteProductionStockRVAdapter(activity  , listMouvementArticle) ;
        EtatEntreStockActivity.rv_list_tot_production.setAdapter(adapter1);

     }


}
