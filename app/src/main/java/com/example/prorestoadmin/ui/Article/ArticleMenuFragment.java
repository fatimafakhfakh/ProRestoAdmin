package com.example.prorestoadmin.ui.Article;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prorestoadmin.EntreStock.EtatEntreStockActivity;
import com.example.prorestoadmin.FicheArticle.FicheArticleActivity;
import com.example.prorestoadmin.R;
import com.example.prorestoadmin.ui.stock.StockViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ArticleMenuFragment extends Fragment {

    private StockViewModel stockViewModel ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stockViewModel =
                ViewModelProviders.of(this).get(StockViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_article_menu, container, false);

       // final TextView textView = root.findViewById(R.id.text_dashboard);
        /*final CardView  btn_etat_de_stock =  root.findViewById(R.id.btn_etat_de_stock);

*/

        stockViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });




        final CardView btn_fiche_article =  root.findViewById(R.id.btn_fiche_article);
        btn_fiche_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toFicheArticle = new Intent(getActivity()  , FicheArticleActivity.class) ;
                startActivity(toFicheArticle);

            }
        });



        final   CardView btn_entree_stock  = root.findViewById(R.id.btn_bon_entree) ;
        btn_entree_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to_bon_entree  = new Intent(getActivity() , EtatEntreStockActivity.class) ;
                startActivity(to_bon_entree);

            }

        });


        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Article");
    }

}