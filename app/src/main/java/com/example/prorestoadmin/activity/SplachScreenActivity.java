package com.example.prorestoadmin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SplachScreenActivity extends AppCompatActivity {

    boolean etatSQL = false;
    boolean etatUser = false;

    ConnectionClass connectionClass;
    String NomUtilisateur, NomPrenom, MotDePasse, CodeFonction, LibelleFonction, CodeRepresentant/*, CodeDepot, Depot*/;
    String user, password, base, ip;
    boolean actif = false;


    private static int splash_screen = 3000;
    Animation topAnim;
    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        logo = findViewById(R.id.logo);
        logo.setAnimation(topAnim);



                SharedPreferences prefUser = getSharedPreferences(StaticValues.PREF_USER, Context.MODE_PRIVATE);

                //  get PARAM USER
                NomUtilisateur = prefUser.getString("NomUtilisateur", "0");
                NomPrenom = prefUser.getString("NomPrenom", NomPrenom);
                MotDePasse = prefUser.getString("MotDePasse", "0");
                CodeFonction = prefUser.getString("CodeFonction", CodeFonction);
                LibelleFonction = prefUser.getString("Fonction", LibelleFonction);

                etatUser = prefUser.getBoolean("etatuser", false);

                Log.e("Spach_etat_user", etatUser + "");
                SharedPreferences prefs = getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
                etatSQL = prefs.getBoolean("etatsql", false);




    }

    @Override
    protected void onPostResume() {
        super.onPostResume();


               new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

        SharedPreferences p = getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
        etatSQL = p.getBoolean("etatsql", false);


        if (etatSQL == true) {

            SharedPreferences p_ = getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);

            ///  get param SQL
            user = p_.getString("user", user);
            ip = p_.getString("ip", ip);
            password = p_.getString("password", password);
            base = p_.getString("base", base);


            if (etatUser == true) {


                SharedPreferences prefUser = getSharedPreferences(StaticValues.PREF_USER, Context.MODE_PRIVATE);

                //  get PARAM USER
                NomUtilisateur = prefUser.getString("NomUtilisateur", "0");
                MotDePasse = prefUser.getString("MotDePasse", "0");

                Log.e("onResume_Parm", "CheckLogin");

                CheckLogin checkLogin = new CheckLogin(NomUtilisateur, MotDePasse);
                checkLogin.execute();


            } else {

                Log.e("onResume_Parm", "LoginActivity");
                Intent i = new Intent(getApplicationContext(), LoginAdminActivity.class);
                startActivity(i);
                finish();
            }


        } else {

            Intent i = new Intent(getApplicationContext(), ParametrageActivity.class);
            startActivity(i);

        }



            }
        }, splash_screen);



    }


    public class CheckLogin extends AsyncTask<String, String, String> {

        String z = "";
        Boolean isSuccess = false;


        String _login;
        String _password;

        public CheckLogin(String _login, String _password) {
            this._login = _login;
            this._password = _password;


            SharedPreferences pref = getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
            //SharedPreferences.Editor edt = pref.edit();
            user = pref.getString("user", user);
            ip = pref.getString("ip", ip);
            password = pref.getString("password", password);
            base = pref.getString("base", base);
            connectionClass = new ConnectionClass();

        }

        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... params) {

            if (_login.trim().equals("") || _password.trim().equals(""))
                z = "Please enter User Id and Password";

            else {
                try {
                    Connection con = connectionClass.CONN(ip, password, user, base);
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "     select \n" +
                                "Utilisateur. NomUtilisateur ,  Respensable.Nom as NomPrenom  , MotDePasse   , \n" +
                                "Fonction.CodeFonction  , Fonction.Libelle as Fonction  , \n" +
                                "Utilisateur.Actif  , CodeRepresentant \n" +
                                "                from Utilisateur  \n" +
                                "                inner  join Fonction  on Fonction.CodeFonction = Utilisateur.CodeFonction \n" +
                                "                INNER JOIN Respensable  on Respensable.CodeRespensable  = Utilisateur.CodeRepresentant \n" +
                                "               \n" +
                                "                where  Utilisateur.NomUtilisateur = '" + _login + "'  \n" +
                                "                and MotDePasse = '" + _password + "' \n" +
                                "                and Utilisateur.Actif = '1' \n" +
                                "                and Fonction.CodeFonction  = 'FN001' \n" +
                                "                 ";
                        ;


                        Log.e("query_login", query);

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);


                        if (rs.next()) {

                            boolean TEST = false;
                            NomUtilisateur = rs.getString("NomUtilisateur");
                            NomPrenom = rs.getString("NomPrenom");
                            MotDePasse = rs.getString("MotDePasse");
                            CodeFonction = rs.getString("CodeFonction");
                            LibelleFonction = rs.getString("Fonction");
                            CodeRepresentant = rs.getString("CodeRepresentant");
                           /* CodeDepot = rs.getString("CodeDepot");
                            Depot = rs.getString("Libelle");*/

                            actif = rs.getBoolean("Actif");

                            if (actif) {
                                isSuccess = true;
                                z = "Login avec Succès";
                                Log.e("login_", z.toString());
                            } else if (!actif) {
                                isSuccess = false;
                                z = "Login échoué";
                                Log.e("login_ERROR", z.toString());
                            }

                        }


                    }
                } catch (SQLException ex) {

                    isSuccess = false;
                    z = "Login échoué";
                    Log.e("ERROR", ex.getMessage().toString());
                }
            }
            return z;
        }


        @Override
        protected void onPostExecute(String r) {

            //  Toast.makeText(SplashScreenActivity.this, r, Toast.LENGTH_SHORT).show();

            // progressBar.setVisibility(View.INVISIBLE);
            // txt_error.setVisibility(View.INVISIBLE);

            if (r.equals("Error in connection with SQL server")) {
                Intent ToConnexionFailed = new Intent(SplachScreenActivity.this, ConnexionEuServeurEchoueActivity.class);
                startActivity(ToConnexionFailed);
            } else {

                if (isSuccess) {

                    // Toast.makeText(getApplicationContext(), NomUtilisateur, Toast.LENGTH_LONG).show();

                    SharedPreferences prefs = getSharedPreferences(StaticValues.PREF_USER, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = prefs.edit();

                    edt.putString("NomUtilisateur", NomUtilisateur);
                    edt.putString("NomPrenom", NomPrenom);
                    edt.putString("MotDePasse", MotDePasse);
                    edt.putString("CodeFonction", CodeFonction);
                    edt.putString("Fonction", LibelleFonction);
                    edt.putString("CodeRepresentant", CodeRepresentant);
                    //edt.putString("CodeDepot", CodeDepot);
                    //edt.putString("Depot", Depot);

                    edt.commit();


                    SharedPreferences p = getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
                    etatSQL = p.getBoolean("etatsql", false);

                    if (etatSQL == true) {


                        if (etatUser == false) {

                            Log.e("onResume_Parm", "CheckLogin " + etatUser);
                            Intent i = new Intent(getApplicationContext(), LoginAdminActivity.class);
                            startActivity(i);
                            finish();

                        } else if (etatUser == true) {

                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(i);
                            finish();

                            /*CheckUserPointed checkUserPointed = new CheckUserPointed(NomUtilisateur);
                            checkUserPointed.execute();*/

                        }

                    }

                } else {

                    Intent ToCompteDesactive = new Intent(SplachScreenActivity.this, CompteDesactiveActivity.class);
                    ToCompteDesactive.putExtra("nom_user", NomPrenom);
                    ToCompteDesactive.putExtra("fonction", LibelleFonction);
                    startActivity(ToCompteDesactive);

                    // Toast.makeText(getApplicationContext()," Verifiez Vos Données ",Toast.LENGTH_SHORT).show();
                    // txt_error.setVisibility(View.VISIBLE);

                }
            }
        }
    }

}