package com.example.prorestoadmin.ui.vente;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.client.ClientNonMouvementeActivity;
import com.example.prorestoadmin.client.CreditClientParRecActivity;
import com.example.prorestoadmin.task.ListLivRecTaskForSearchableSpinner;
import com.example.prorestoadmin.ui.stock.StockViewModel;
import com.example.prorestoadmin.vente.activity.BonLivraisonActivity;
import com.example.prorestoadmin.vente.activity.BonRetourActivity;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class VenteMenuFragment extends Fragment {

    private StockViewModel stockViewModel;

    public static int year_x1, month_x1, day_x1;
    public static int year_x2, month_x2, day_x2;
    public static int id_DatePickerDialog = 0;

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("00");


    Date date_debut = null;
    Date date_fin = null;

    public static String CodeClientSeleted     = "";
    public static String RaisonClientSeleted   = "";

    public static String CodeRecouvreurSeleted = "";
    public static String NomRecouvreurSeleted  = "";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stockViewModel =
                ViewModelProviders.of(this).get(StockViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vente_menu, container, false);

        // final TextView textView = root.findViewById(R.id.text_dashboard);
        final CardView btn_bon_livraison = root.findViewById(R.id.btn_bon_livraison);
        final CardView btn_bon_retour = root.findViewById(R.id.btn_bon_retour);

        final CardView btn_bon_gratuite = root.findViewById(R.id.btn_bon_gratuite);
        final CardView btn_etat_liv_rec = root.findViewById(R.id.btn_etat_livreur_recouvreur);


        final CardView  btn_etat_credit_par_rec  = root.findViewById(R.id.btn_etat_credit_par_rec);






        stockViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });


        btn_bon_livraison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toBonLivraison = new Intent(getActivity()  , BonLivraisonActivity.class) ;
                startActivity(toBonLivraison);

            }
        });


        btn_bon_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toBonRetour  = new Intent(getActivity()  , BonRetourActivity.class) ;
                startActivity(toBonRetour);

            }
        });


        btn_etat_credit_par_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
                //ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_solde_credit_par_rec, null, false);

                Date currentDate = new Date() ;

                final TextView txt_date_debut   = (TextView) dialogView.findViewById(R.id.txt_date_debut);
                final TextView txt_date_fin     = (TextView) dialogView.findViewById(R.id.txt_date_fin);
                SearchableSpinner sp_recouvreur = (SearchableSpinner) dialogView.findViewById(R.id.sp_recouvreur);


                final Calendar cal1 = Calendar.getInstance();
                cal1.setTime(currentDate);
                cal1.add(Calendar.DAY_OF_MONTH, -1) ;
                year_x1 = cal1.get(Calendar.YEAR);
                month_x1 = cal1.get(Calendar.MONTH);
                day_x1 = cal1.get(Calendar.DAY_OF_MONTH);


                final Calendar cal2 = Calendar.getInstance();
                cal2.setTime(currentDate);
                //  cal2.add(Calendar.DAY_OF_YEAR, +7);
                year_x2 = cal2.get(Calendar.YEAR);
                month_x2 = cal2.get(Calendar.MONTH);
                day_x2 = cal2.get(Calendar.DAY_OF_MONTH);


                date_debut = cal1.getTime();
                String _date_du = df.format(cal1.getTime());
                txt_date_debut.setText(_date_du);

                date_fin = cal2.getTime();
                String _date_au = df.format(cal2.getTime());
                txt_date_fin.setText(_date_au);


                txt_date_debut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        id_DatePickerDialog = 0;
                        Log.e("month_x1", "On picker  : " + month_x1);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                if (id_DatePickerDialog == 0) {
                                    year_x1 = year;
                                    month_x1 = monthOfYear;
                                    day_x1 = dayOfMonth;

                                    txt_date_debut.setText("" + formatter.format(day_x1) + "/" + formatter.format(month_x1 + 1) + "/" + year_x1);

                                    String _date_du = formatter.format(day_x1) + "/" + formatter.format(month_x1 + 1) + "/" + year_x1 + " ";
                                    String _date_au = txt_date_fin.getText().toString();


                                    try {
                                        date_debut = df.parse(_date_du);
                                        date_fin = df.parse(_date_au);

                                    } catch (Exception e) {
                                        Log.e("Exception--", " " + e.getMessage());
                                    }

                                }
                            }
                        }, year_x1, month_x1, day_x1);
                        datePickerDialog.show();
                    }
                });


                txt_date_fin.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        id_DatePickerDialog = 1;

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if (id_DatePickerDialog == 1) {

                                    year_x2 = year;
                                    month_x2 = monthOfYear;
                                    day_x2 = dayOfMonth;

                                    txt_date_fin.setText("" + formatter.format(day_x2) + "/" + formatter.format(month_x2 + 1) + "/" + year_x2);

                                    String _date_au = "" + formatter.format(day_x2) + "/" + formatter.format(month_x2 + 1) + "/" + year_x2;
                                    String _date_du = txt_date_debut.getText().toString();


                                    try {
                                        date_debut = df.parse(_date_du);
                                        date_fin = df.parse(_date_au);

                                    } catch (Exception e) {
                                        Log.e("Exception--", " " + e.getMessage());
                                    }


                                }
                            }
                        }, year_x2, month_x2, day_x2);
                        datePickerDialog.show();
                    }
                });



                ListLivRecTaskForSearchableSpinner listLivRecTaskForSearchableSpinner = new ListLivRecTaskForSearchableSpinner(getActivity(), sp_recouvreur,"VenteMenuFragment");
                listLivRecTaskForSearchableSpinner.execute();


                Button buttonOk = dialogView.findViewById(R.id.buttonOk);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.e("Credit_btn", "rec " + CodeRecouvreurSeleted);
                        Log.e("Credit_btn", "date_debut " + df.format(date_debut));
                        Log.e("Credit_btn", "date_din " + df.format(date_fin));

                        Intent toCreditClientParRec = new Intent(getActivity(), CreditClientParRecActivity.class);

                        toCreditClientParRec.putExtra("cle_code_recouv", CodeRecouvreurSeleted);
                        toCreditClientParRec.putExtra("cle_Nom_recouv", NomRecouvreurSeleted);
                        toCreditClientParRec.putExtra("cle_date_debut", df.format(date_debut));
                        toCreditClientParRec.putExtra("cle_date_fin", df.format(date_fin));

                        startActivity(toCreditClientParRec);


                    }
                });

                alertDialog.show();


            }
        });

      /*  btn_fiche_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  toFicheArticle = new Intent(getActivity()  , FicheArticleActivity.class) ;
                startActivity(toFicheArticle);

            }
        }); */



        final CardView  btn_client_nn_mouvemente  = root.findViewById(R.id.btn_client_nn_mouvemente);


        btn_client_nn_mouvemente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
                //ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_client_nn_mouvemente , null, false);

                Date currentDate = new Date();

                final TextView txt_date_debut = (TextView) dialogView.findViewById(R.id.txt_date_debut);
                final TextView txt_date_fin = (TextView) dialogView.findViewById(R.id.txt_date_fin);


                final Calendar cal1 = Calendar.getInstance();
                cal1.setTime(currentDate);
                cal1.add(Calendar.DAY_OF_MONTH, -1);
                year_x1 = cal1.get(Calendar.YEAR);
                month_x1 = cal1.get(Calendar.MONTH);
                day_x1 = cal1.get(Calendar.DAY_OF_MONTH);


                final Calendar cal2 = Calendar.getInstance();
                cal2.setTime(currentDate);
                cal2.add(Calendar.DAY_OF_MONTH, -0);
                year_x2 = cal2.get(Calendar.YEAR);
                month_x2 = cal2.get(Calendar.MONTH);
                day_x2 = cal2.get(Calendar.DAY_OF_MONTH);


                date_debut = cal1.getTime();
                String _date_du = df.format(cal1.getTime());
                txt_date_debut.setText(_date_du);

                date_fin = cal2.getTime();
                String _date_au = df.format(cal2.getTime());
                txt_date_fin.setText(_date_au);


                txt_date_debut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        id_DatePickerDialog = 0;
                        Log.e("month_x1", "On picker  : " + month_x1);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                if (id_DatePickerDialog == 0) {
                                    year_x1 = year;
                                    month_x1 = monthOfYear;
                                    day_x1 = dayOfMonth;

                                    txt_date_debut.setText("" + formatter.format(day_x1) + "/" + formatter.format(month_x1 + 1) + "/" + year_x1);

                                    String _date_du = formatter.format(day_x1) + "/" + formatter.format(month_x1 + 1) + "/" + year_x1 + " ";
                                    String _date_au = txt_date_fin.getText().toString();


                                    try {
                                        date_debut = df.parse(_date_du);
                                        date_fin = df.parse(_date_au);

                                    } catch (Exception e) {
                                        Log.e("Exception--", " " + e.getMessage());
                                    }

                                }
                            }
                        }, year_x1, month_x1, day_x1);
                        datePickerDialog.show();
                    }
                });


                txt_date_fin.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        id_DatePickerDialog = 1;

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if (id_DatePickerDialog == 1) {

                                    year_x2 = year;
                                    month_x2 = monthOfYear;
                                    day_x2 = dayOfMonth;

                                    txt_date_fin.setText("" + formatter.format(day_x2) + "/" + formatter.format(month_x2 + 1) + "/" + year_x2);

                                    String _date_au = "" + formatter.format(day_x2) + "/" + formatter.format(month_x2 + 1) + "/" + year_x2;
                                    String _date_du = txt_date_debut.getText().toString();


                                    try {
                                        date_debut = df.parse(_date_du);
                                        date_fin = df.parse(_date_au);

                                    } catch (Exception e) {
                                        Log.e("Exception--", " " + e.getMessage());
                                    }


                                }
                            }
                        }, year_x2, month_x2, day_x2);
                        datePickerDialog.show();
                    }
                });





                Button buttonOk = dialogView.findViewById(R.id.buttonOk);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();

                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.e("Credit_btn", "rec " + CodeRecouvreurSeleted);
                        Log.e("Credit_btn", "date_debut " + df.format(date_debut));
                        Log.e("Credit_btn", "date_din "   + df.format(date_fin));

                        Intent toClientnonMouvemente  = new Intent(getActivity(), ClientNonMouvementeActivity.class);

                        toClientnonMouvemente.putExtra("cle_date_debut",  df.format(date_debut));
                        toClientnonMouvemente.putExtra("cle_date_fin",    df.format(date_fin));

                        startActivity(toClientnonMouvemente);

                    }
                });

                alertDialog.show();

            }
        });



        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Vente");
    }

}