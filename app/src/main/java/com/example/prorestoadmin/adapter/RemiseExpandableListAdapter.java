package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.Client;
import com.example.prorestoadmin.model.RemiseArticle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.core.app.ActivityCompat;

public class RemiseExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private ArrayList<Client> listClientRemise;
    public LayoutInflater inflater;
    /*private List<String> expandableListTitle;
    private HashMap  <String, List<String> > expandableListDetail;*/

    public RemiseExpandableListAdapter(Activity activity, ArrayList<Client> listClientRemise) {
        this.activity = activity;
        this.listClientRemise = listClientRemise;
        inflater = activity.getLayoutInflater();

        Log.e("adpapter" ,"RemiseExpandableListAdapter" ) ;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.list_group, null);
        Client  client = (Client) getGroup(groupPosition) ;
        Log.e("getViewGroup",""+client.toString()) ;

        ExpandableListView mExpandableListView = (ExpandableListView) parent;
        mExpandableListView.expandGroup(groupPosition);

        TextView txt_raison = (TextView) convertView.findViewById(R.id.listTitle);
        txt_raison.setText(client.getRaisonSocial());


      /* String listTitle = (String)  listClientRemise.get(listPosition).getRaisonSocial() ;//  getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);  */

        return convertView;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return   this.listClientRemise.get(listPosition).getListRemiseArticleClient().get(expandedListPosition) ;

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



        convertView = inflater.inflate(R.layout.item_child_remise_article, null);
    //    Client clientRemise  = (Client) getGroup(groupPosition) ;

        final RemiseArticle remArticle = (RemiseArticle) getChild(groupPosition, childPosition) ;
        Log.e("getViewGroup",""+remArticle.toString()) ;

        TextView txt_code_article = (TextView) convertView.findViewById(R.id.txt_code_article);
        TextView txt_taux_remise = (TextView) convertView.findViewById(R.id.txt_taux_remise);
        TextView txt_pv_ttc = (TextView) convertView.findViewById(R.id.txt_mnt_ttc);


        DecimalFormat dec_remise  = new DecimalFormat("0.00") ;
        DecimalFormat dec_ttc  = new DecimalFormat("0.000") ;
        txt_code_article.setText(remArticle.getCodeArticle());
        txt_taux_remise.setText(dec_remise.format(remArticle.getTauxRemise())+" %");
        txt_pv_ttc.setText(dec_ttc.format(remArticle.getPrixVenteTTCRemise())+" DT");

    /*    final String expandedListText = (String)  listClientRemise.get(listPosition).getListRemiseArticleClient().get(expandedListPosition).getCodeArticle() ;  //getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);*/
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {

        return   this.listClientRemise.get(listPosition).getListRemiseArticleClient().size() ;

       /* return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();*/
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.listClientRemise.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listClientRemise.size();
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