package com.example.prorestoadmin.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.Caisse;
import com.example.prorestoadmin.model.Client;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ClientRV extends RecyclerView.Adapter<ClientRV.ViewHolder> {


    private final Activity activity;

    private final ArrayList<Client> listClient ;


    public static String login;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("0.000");


    public ClientRV(Activity activity, ArrayList<Client> listClient ) {

        this.activity = activity;
        this.listClient = listClient;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Client client = listClient.get(position);



       holder.txt_raison_sociale.setText(client.getRaisonSocial());

    }


    @Override
    public int getItemCount() {
        Log.e("size", "" + listClient.size());
        return listClient.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public CardView card_item_client ;


        public TextView txt_raison_sociale ;



        public ViewHolder(View itemView) {
            super(itemView);

            card_item_client = (CardView) itemView.findViewById(R.id.card_item_client);
            txt_raison_sociale = (TextView) itemView.findViewById(R.id.txt_raison_social_client);



        }

    }

}
