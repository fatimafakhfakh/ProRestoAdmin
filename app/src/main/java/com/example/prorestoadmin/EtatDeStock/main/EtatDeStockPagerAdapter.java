package com.example.prorestoadmin.EtatDeStock.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.model.Depot;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class EtatDeStockPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    ArrayList<Depot> listDepot ;
    //
    public EtatDeStockPagerAdapter(Context context , FragmentManager fm, ArrayList<Depot> listDepot ) {
        super(fm);
        mContext = context;
        this.listDepot=listDepot  ;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return EtatDeStockFragment.newInstance(position + 1 ,listDepot.get(position).getCodeDepot());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return mContext.getResources().getString(TAB_TITLES[position]);
        return listDepot.get(position).getLibelle();
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return listDepot.size();
    }
}