package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.Client;
import com.example.prorestoadmin.model.Convention;
import com.example.prorestoadmin.model.GratuiteClient;
import com.example.prorestoadmin.model.RemiseArticle;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GratuiteEnCoursExtensibleAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private ArrayList<GratuiteClient> listClientGratuite;
    public LayoutInflater inflater;


    public GratuiteEnCoursExtensibleAdapter(Activity activity, ArrayList<GratuiteClient> listClientGratuite) {
        this.activity = activity;
        this.listClientGratuite = listClientGratuite;
        inflater = activity.getLayoutInflater();
        Log.e("adapter", "RemiseExpandableListAdapter");
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.item_entete_gratuite, null);
        GratuiteClient gratuiteClient = (GratuiteClient) getGroup(groupPosition);
        Log.e("getViewGroup", "" + gratuiteClient.toString());


        CardView item_gratuite_entete = (CardView) convertView.findViewById(R.id.item_gratuite_entete);

        ImageView icon_client_gratuit = (ImageView) convertView.findViewById(R.id.icon_client_gratuit);

        TextView txt_raison = (TextView) convertView.findViewById(R.id.txt_raison_social);
        TextView txt_debut = (TextView) convertView.findViewById(R.id.txt_date_debut);
        TextView txt_fin = (TextView) convertView.findViewById(R.id.txt_date_fin);

        txt_raison.setText(gratuiteClient.getRaisonSocial());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txt_debut.setText(sdf.format(gratuiteClient.getDateDebut()));
        txt_fin.setText(sdf.format(gratuiteClient.getDateFin()));


        if (gratuiteClient.isGratuit_atteint()) {
            icon_client_gratuit.setImageResource(R.drawable.ic_gratuit);
        }

        if (gratuiteClient.isGratuit_proche_atteint()) {
            icon_client_gratuit.setImageResource(R.drawable.ic_gratuite_proche);
        }

        if (groupPosition % 2 == 0) // paire
        {
            item_gratuite_entete.setCardBackgroundColor(activity.getResources().getColor(R.color.rouge_clair));
        } else {

            item_gratuite_entete.setCardBackgroundColor(activity.getResources().getColor(R.color.white));
        }


        return convertView;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.listClientGratuite.get(listPosition).getListConvention().get(expandedListPosition);

     /*   return this.expandableListDetail.get(this.listClientRemise.get(listPosition))
                .get(expandedListPosition);*/
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        convertView = inflater.inflate(R.layout.item_convention, null);


        final Convention conv = (Convention) getChild(groupPosition, childPosition);


        TextView txt_code_designation_article = (TextView) convertView.findViewById(R.id.txt_code_designation_article);
        TextView txt_convention = (TextView) convertView.findViewById(R.id.txt_convention);
        TextView txt_qt_reste_a_livrer = (TextView) convertView.findViewById(R.id.txt_qt_reste_a_livrer);
        TextView txt_gratuite = (TextView) convertView.findViewById(R.id.txt_gratuite);
        TextView txt_qt_livre = (TextView) convertView.findViewById(R.id.txt_qt_livre);


        txt_code_designation_article.setText(conv.getCodeArticle() + " - " + conv.getDesignation());
        txt_convention.setText("Convention " + conv.getConvention());
        txt_qt_reste_a_livrer.setText(conv.getQuantiteMinimum() - conv.getQTPalierGratuite() + "");
        txt_gratuite.setText(conv.getQuantiteGratuite() + "");
        txt_qt_livre.setText(conv.getQTPalierGratuite() + "");


        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {

        return this.listClientGratuite.get(listPosition).getListConvention().size();

        /* return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)) .size();*/
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.listClientGratuite.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listClientGratuite.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

}