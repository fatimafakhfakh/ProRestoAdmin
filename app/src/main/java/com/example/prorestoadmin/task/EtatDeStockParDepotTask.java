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

import com.example.prorestoadmin.adapter.EtatDeStockAdapterLV;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.ArticleStock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class EtatDeStockParDepotTask extends AsyncTask<String, String, String> {

    String res;

    Activity activity;

    ListView lv_list_article ;
   // SearchView search_v_article ;
  ProgressBar pb_chargement;

    String CodeDepot ;

    ConnectionClass connectionClass;
    String user, password, base, ip;

    ArrayList<ArticleStock> listArticle = new ArrayList<>()  ;

   LinearLayout ll_stock_empty ;
   LinearLayout ll_stock_full ;



    int  full = 0  ;


    public EtatDeStockParDepotTask(Activity activity,  ListView lv_list_article , String CodeDepot,ProgressBar pb_chargement ,   LinearLayout ll_stock_empty  ,   LinearLayout ll_stock_full) {
        this.activity = activity;
        this.lv_list_article = lv_list_article ;
        this.CodeDepot = CodeDepot ;

        /*
         this.search_v_article = search_v_article ;
         */

        this.pb_chargement =pb_chargement ;

        this.ll_stock_empty=ll_stock_empty ;
        this.ll_stock_full=ll_stock_full ;

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
          pb_chargement.setVisibility(View.VISIBLE);
         full = 0  ;

    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Connection con = connectionClass.CONN(ip, password, user, base);       // Connect to database
            Log.e("con", "" + con);
            if (con == null) {
                res = "Check Your Internet Access!";
            } else {

                String query = "  select Article.CodeArticle  , Designation , PrixVenteHT , PrixVenteTTC ,PrixAchatHT , Article.CodeTVA  , TauxTVA  ,  Stock.Quantite as QuantiteStock\n" +
                        "     from Article  \n" +
                        "     INNER  JOIN  TVA  on TVA.CodeTVA = Article.CodeTVA\n" +
                        "     INNER JOIN Stock  on Stock.CodeArticle  = Article.CodeArticle and CodeDepot  = '"+CodeDepot+"'\n" +
                        "     where CodeNature = '1'  and Stockable  = '1'   and Actif  = '1'  and Stock.Quantite !=0 "  ;


                Log.e("query_list_article", query);
                listArticle.clear();

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    String CodeArticle  = rs.getString("CodeArticle");
                    String Designation  = rs.getString("Designation");
                    double PrixVenteHT  = rs.getDouble("PrixVenteHT");
                    double PrixVenteTTC = rs.getDouble("PrixVenteTTC");
                    double PrixAchatHT = rs.getDouble("PrixAchatHT");

                    int    CodeTVA      = rs.getInt   ("CodeTVA");
                    double TauxTVA      = rs.getDouble("TauxTVA");
                    int    QuantiteStock      = rs.getInt   ("QuantiteStock");

                    ArticleStock article  = new ArticleStock(CodeArticle ,Designation ,PrixVenteHT,PrixVenteTTC,PrixAchatHT,CodeTVA,TauxTVA,QuantiteStock,0);
                    listArticle.add(article) ;

                    full = 1 ;
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

        if (full == 0) {

            ll_stock_empty.setVisibility(View.VISIBLE);
            ll_stock_full.setVisibility(View.INVISIBLE);
        } else if (full == 1) {
             ll_stock_empty.setVisibility(View.INVISIBLE);
             ll_stock_full.setVisibility(View.VISIBLE);

            EtatDeStockAdapterLV etatDeStockAdapterLV = new EtatDeStockAdapterLV(activity, listArticle);
            lv_list_article.setAdapter(etatDeStockAdapterLV);

            /*search_v_article.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    if (!search_v_article.isIconified()) {
                        search_v_article.setIconified(true);
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {

                    final ArrayList<ArticleStock> fitler_list_article = filter(listArticle, query);

                    EtatDeStockAdapterLV etatDeStockAdapterLV  = new EtatDeStockAdapterLV(activity  , fitler_list_article )  ;
                    lv_list_article.setAdapter(etatDeStockAdapterLV);

                    return false;
                }
            });*/

        }


    }
}


  /*  private ArrayList<Article> filter(ArrayList<Article> listArticle, String term) {

        term = term.toLowerCase();
        final ArrayList<Article> filetrListArticle = new ArrayList<>();

        for (Article a : listArticle) {
            final String txt_designation = a.getDesignationArticle().toLowerCase();
            final String txt_code_article = a.getCodeArticle().toLowerCase();


            if (txt_designation.contains(term) || txt_code_article
                    .contains(term) ) {
                filetrListArticle.add(a);
            }
        }
        return filetrListArticle;
    }*/





