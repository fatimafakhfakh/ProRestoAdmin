package com.example.prorestoadmin.EtatDeStock.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.task.EtatDeStockParDepotTask;
import com.example.prorestoadmin.task.TransfertChargeParDepotTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class EtatDeStockFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SECTION_CODE_DEPOT = "CODE_DEPOT";
    private PageViewModel pageViewModel;
    String CodeDepot  = ""  ;
    public static EtatDeStockFragment newInstance(int index , String CodeDepot) {
        EtatDeStockFragment fragment = new EtatDeStockFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putString(ARG_SECTION_CODE_DEPOT, CodeDepot);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            CodeDepot =  getArguments().getString(ARG_SECTION_CODE_DEPOT) ;
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView  (
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
      //  final TextView textView = root.findViewById(R.id.section_label);
        final ListView lv_list_article = root.findViewById(R.id.lv_list_article);
        final ProgressBar progressBar  = root.findViewById(R.id.pb) ;
        final LinearLayout ll_stock_full   = root.findViewById(R.id.ll_stock_full) ;
        final LinearLayout ll_stock_empty   = root.findViewById(R.id.ll_stock_empty) ;

        ll_stock_empty.setVisibility(View.INVISIBLE);


        final RecyclerView rv_list_transfert_en_charge  = root.findViewById(R.id.rv_list_bt_charge) ;
        rv_list_transfert_en_charge.setHasFixedSize(true);
        rv_list_transfert_en_charge.setLayoutManager( new LinearLayoutManager( getActivity() ) );





        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                EtatDeStockParDepotTask etatDeStockParDepotTask  = new EtatDeStockParDepotTask(getActivity() ,lv_list_article, CodeDepot,progressBar ,ll_stock_empty , ll_stock_full);
                etatDeStockParDepotTask.execute();

                TransfertChargeParDepotTask  transfertChargeParDepotTask  = new TransfertChargeParDepotTask(getActivity() ,CodeDepot,rv_list_transfert_en_charge) ;
                transfertChargeParDepotTask.execute();

            }
        });


        return root;
    }
}