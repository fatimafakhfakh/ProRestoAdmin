package com.example.prorestoadmin.ui.gratuite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.example.prorestoadmin.model.Personnel;
import com.example.prorestoadmin.ui.gratuite.task.ListGratuiteEnCoursTask;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class GratuiteEnCoursActivity extends AppCompatActivity {


    TabLayout tab_recouvreur, tab_etat;
    ExpandableListView elv_list_gratuite_en_cours;
    ProgressBar progressBar;
    public static String CodeRecSelected = "";
    public static Date DateSelected = null;
    public static int mode_gratuite =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratuite_en_cours);

        elv_list_gratuite_en_cours = findViewById(R.id.elv_list_gratuite_en_cours);
        progressBar = findViewById(R.id.progress_bar);
        tab_recouvreur = (TabLayout) findViewById(R.id.tab_recouvreur);
        tab_etat = (TabLayout) findViewById(R.id.tab_etat);

        tab_etat.addTab(tab_etat.newTab().setText("tout"));            //0
        tab_etat.addTab(tab_etat.newTab().setText("gratuité atteint")); //1
        tab_etat.addTab(tab_etat.newTab().setText("gratuité proche à atteindre")); //2
        tab_etat.addTab(tab_etat.newTab().setText("gratuité non atteint")); //3
        mode_gratuite =0;

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, -10);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 10);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();


        DateSelected = new Date();


        ListRecForTabTask listRecForTabTask = new ListRecForTabTask(GratuiteEnCoursActivity.this, tab_recouvreur);
        listRecForTabTask.execute();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Log.e("Date_selected", position + " " + date.getTime());
                DateSelected = date.getTime();

                ListGratuiteEnCoursTask listGratuiteEnCoursTask = new ListGratuiteEnCoursTask(GratuiteEnCoursActivity.this, CodeRecSelected, DateSelected,mode_gratuite , elv_list_gratuite_en_cours, progressBar);
                listGratuiteEnCoursTask.execute();

            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView, int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {

                return true;
            }

        });

        tab_etat.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mode_gratuite= tab.getPosition() ;
               /* if (tab.getPosition() == 0) {
                    mode_gratuite = 0; // tout
                } else if (tab.getPosition() == 1) {
                    mode_gratuite = 1;
                } else if (tab.getPosition() == 2) {
                    mode_gratuite = 2;
                }
                else if (tab.getPosition() == 3) {
                    mode_gratuite = 3;
                }*/

                ListGratuiteEnCoursTask listGratuiteEnCoursTask = new ListGratuiteEnCoursTask(GratuiteEnCoursActivity.this, CodeRecSelected, DateSelected,mode_gratuite , elv_list_gratuite_en_cours, progressBar);
                listGratuiteEnCoursTask.execute();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public class ListRecForTabTask extends AsyncTask<String, String, String> {

        String res;

        Activity activity;
        TabLayout tab_rec;

        String CodeLivreur;
        String Origine;

        ConnectionClass connectionClass;
        String user, password, base, ip;


        ArrayList<Personnel> listRec = new ArrayList<>();


        public ListRecForTabTask(Activity activity, TabLayout tab_rec) {

            this.activity = activity;
            this.tab_rec = tab_rec;


            SharedPreferences pref = activity.getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
            SharedPreferences.Editor edt = pref.edit();
            user = pref.getString("user", user);
            ip = pref.getString("ip", ip);
            password = pref.getString("password", password);
            base = pref.getString("base", base);
            connectionClass = new ConnectionClass();


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Connection con = connectionClass.CONN(ip, password, user, base);  // Connect to database
                Log.e("con", "" + con);
                if (con == null) {
                    res = "Check Your Internet Access!";
                } else {

                    String query = " select CodeRespensable  , Nom  from  Respensable  \n" +
                            "where  CodeRespensable in ( select   CodeRepresentant from  Utilisateur  where CodeFonction in ( 'FN007' , 'FN008' ) and Actif =1 ) ";


                    Log.e("query_responsable", query);

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    listRec.add(new Personnel("", "tout"));
                    while (rs.next()) {

                        String CodeRespensable = rs.getString("CodeRespensable");
                        String Nom = rs.getString("Nom");


                        Personnel rec = new Personnel(CodeRespensable, Nom);
                        listRec.add(rec);

                        Log.e("liv_rec_X", rec.toString());

                    }
                }
                con.close();

            } catch (Exception ex) {

                res = ex.getMessage();
                Log.e("ERROR", res.toString());

            }
            return null;

        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            for (Personnel rec : listRec) {
                tab_rec.addTab(tab_rec.newTab().setText(rec.getNomResponsable())); //0

            }

            CodeRecSelected = listRec.get(0).getCodeResponsable();

            ListGratuiteEnCoursTask listGratuiteEnCoursTask = new ListGratuiteEnCoursTask(GratuiteEnCoursActivity.this, CodeRecSelected, DateSelected,mode_gratuite , elv_list_gratuite_en_cours, progressBar);
            listGratuiteEnCoursTask.execute();


            tab_rec.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    CodeRecSelected = listRec.get(tab.getPosition()).getCodeResponsable();

                    ListGratuiteEnCoursTask listGratuiteEnCoursTask = new ListGratuiteEnCoursTask(GratuiteEnCoursActivity.this, CodeRecSelected, DateSelected, mode_gratuite  ,elv_list_gratuite_en_cours, progressBar);
                    listGratuiteEnCoursTask.execute();

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


        }


    }
}