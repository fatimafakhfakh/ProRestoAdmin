package com.example.prorestoadmin.rapportPersonnel.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.prorestoadmin.R;

import java.util.Date;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class RapportPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_livraison, R.string.tab_retour , R.string.tab_gratuit , R.string.tab_caisse};
    private final Context mContext;
    String NomUtilisateur  ;
    Date  date_rapport  ;

    public RapportPagerAdapter(Context context, FragmentManager fm, String NomUtilisateur ,Date  date_rapport ) {
        super(fm);
        mContext = context;
        this.NomUtilisateur=NomUtilisateur;
        this.date_rapport=date_rapport ;
    }

    @Override
    public Fragment getItem(int position) {

        return RapportFragment.newInstance(position + 1 ,NomUtilisateur ,date_rapport);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}