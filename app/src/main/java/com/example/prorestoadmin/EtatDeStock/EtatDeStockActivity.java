package com.example.prorestoadmin.EtatDeStock;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.prorestoadmin.task.ListDepotTask;
import com.example.prorestoadmin.task.ListTotStockTask;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.example.prorestoadmin.R;

public class EtatDeStockActivity extends AppCompatActivity {


    FloatingActionButton fab_arrow;
    RelativeLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;

    ListView lv_list_tot_stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat_de_stock);


        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);
        Spinner sp_article = findViewById(R.id.sp_article);

        lv_list_tot_stock = findViewById(R.id.lv_list_tot_stock) ;

        ListDepotTask listDepotTask = new ListDepotTask(this, tabs, sp_article, viewPager, "EtatDeStockActivity");
        listDepotTask.execute();

        ListTotStockTask  listTotStockTask  = new ListTotStockTask(this , lv_list_tot_stock,"EtatDeStockActivity") ;
        listTotStockTask.execute() ;

        //exptens_lv_stock_en_repture   = (ExpandableListView)findViewById(R.id.ex_list_stock_en_repture);
        layoutBottomSheet = (RelativeLayout) findViewById(R.id.bottom_sheet);

        fab_arrow = (FloatingActionButton) findViewById(R.id.fab_arrow);

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setHideable(false);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                        // Toast.makeText(getActivity() , "Close Sheet" ,Toast.LENGTH_LONG).show();
                        fab_arrow.setImageResource(R.drawable.ic_arrow_down);

                      /* ReptureStockClientTaskTask reptureStockClientTaskTask = new ReptureStockClientTaskTask(getActivity() ,"1" , exptens_lv_stock_en_repture) ;
                        reptureStockClientTaskTask.execute() ;*/

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        // Toast.makeText(getActivity() , "Expand Sheet" ,Toast.LENGTH_LONG).show();
                          fab_arrow.setImageResource(R.drawable.ic_arrow_up);
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });



       /*

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */


    }
}