package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.ArticleStock;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class EtatDeStockAdapterLV extends ArrayAdapter<ArticleStock> {


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    DecimalFormat formatter = new DecimalFormat("0.000");
    private final Activity activity;
    private final ArrayList<ArticleStock> listArticles;

    public EtatDeStockAdapterLV(Activity activity, ArrayList<ArticleStock> listArticles) {
        super(activity, R.layout.item_article_stock  , listArticles);
        // TODO Auto-generated constructor stub
        this.activity     = activity;
        this.listArticles = listArticles;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        Context context = parent.getContext();
        View rowView = inflater.inflate (R.layout.item_article_stock  , null, true);

        ArticleStock article = listArticles.get(position);

        TextView txt_code_article = (TextView) rowView.findViewById(R.id.txt_code_article);
        TextView txt_designation_article = (TextView) rowView.findViewById(R.id.txt_designation_article);
        TextView txt_qt_stock  = (TextView) rowView.findViewById(R.id.txt_qt_stock_article);


        txt_code_article  .setText(article.getCodeArticle());
        txt_designation_article.setText(article.getDesignationArticle());
        txt_qt_stock.setText(article.getQuantiteStock()+"");


        return rowView;

    }




}


