package com.example.prorestoadmin.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class LoginAdminActivity extends AppCompatActivity {

    private Button btn_login;
    private TextInputLayout ed_login , ed_password ;
    String user , password , base, ip ;

    String NomUtilisateur = "", NomPrenom = "" ,MotDePasse = "" , CodeFonction = "", LibelleFonction = "" ,CodeRepresentant = "" /*,CodeDepot = "" ,Depot = ""*/ ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_admin);
        getWindow() . setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);


        btn_login = findViewById(R.id.btn);
        ed_login = findViewById(R.id.login);
        ed_password = findViewById(R.id.password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!valideLogin() | !valideMtp()) {
                    return;
                }
                String _login = ed_login.getEditText().getText().toString();
                String _mot_de_passe = ed_password.getEditText().getText().toString();


                DoLogin loginTask = new DoLogin(LoginAdminActivity.this, _login, _mot_de_passe,btn_login);
                loginTask.execute();


            }
        });




    }

    private Boolean valideLogin() {
        String val = ed_login.getEditText().getText().toString();
        if (val.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("alert")
//set message
                    .setMessage("champ mail est vide")
//set positive button
//set negative button
                    .setNegativeButton("okey", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what should happen when negative button is clicked
                            Toast.makeText(getApplicationContext(), "Nothing Happened", Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
            ed_login.setError("le champ ne peut pas être vide");
            return false;
        } else {
            ed_login.setError(null);
            ed_login.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean valideMtp() {
        String val = ed_password.getEditText().getText().toString();
        if (val.isEmpty()) {

            AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("alert")
//set message
                    .setMessage("champ mot de passe est vide")
//s
//set negative button
                    .setNegativeButton("okey", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what should happen when negative button is clicked
                            Toast.makeText(getApplicationContext(), "Nothing Happened", Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();

            ed_password.setError("le champ ne peut pas être vide");
            return false;
        } else {
            ed_password.setError(null);
            ed_password.setErrorEnabled(false);
            return true;
        }
    }

    public class DoLogin extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;

        Activity  activity ;
        String _login;
        String _password;
        Button btn_connexion   ;

        ConnectionClass connectionClass;
        String user, password, base, ip;

        public DoLogin(Activity  activity ,String _login, String _password , Button btn_connexion    ) {
            this.activity=activity ;
            this._login = _login;
            this._password = _password;
            this.btn_connexion  = btn_connexion  ;


            SharedPreferences pref = activity.getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
            //SharedPreferences.Editor edt = pref.edit();
            user = pref.getString("user", user);
            ip = pref.getString("ip", ip);
            password = pref.getString("password", password);
            base = pref.getString("base", base);
            connectionClass = new ConnectionClass();


        }

        @Override
        protected void onPreExecute() {

            btn_connexion.setText("Connexion en cours ...");
            btn_connexion.setEnabled(false);

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
                        String query = "  select \n" +
                                "Utilisateur. NomUtilisateur ,  Respensable.Nom as NomPrenom  , MotDePasse  , \n" +
                                "Fonction.CodeFonction  , Fonction.Libelle as Fonction  , \n" +
                                "Utilisateur.Actif  , CodeRepresentant \n" +
                                "                from Utilisateur  \n" +
                                "                inner  join Fonction  on Fonction.CodeFonction = Utilisateur.CodeFonction \n" +
                                "                INNER JOIN Respensable  on Respensable.CodeRespensable  = Utilisateur.CodeRepresentant \n" +
                                "               \n" +
                                "                where  Utilisateur.NomUtilisateur = '"+_login+"'  \n" +
                                "                and MotDePasse = '"+_password+"' \n" +
                                "                and Utilisateur.Actif = '1' \n" +
                                "                and Fonction.CodeFonction  = 'FN001' \n" +
                                "                 ";


                        Log.e("query_login", query);

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);


                        if (rs.next()) {

                            boolean TEST = false ;

                            NomUtilisateur = rs.getString("NomUtilisateur");
                            NomPrenom      = rs.getString("NomPrenom");
                            MotDePasse     = rs.getString("MotDePasse");
                            CodeFonction   = rs.getString("CodeFonction");
                            LibelleFonction = rs.getString("Fonction");
                            CodeRepresentant = rs.getString("CodeRepresentant");
                            //CodeDepot       = rs.getString("CodeDepot");
                           //Depot           = rs.getString("Libelle");

                            isSuccess = true;
                            z = "Login avec succès";
                            Log.e("ERROR", z.toString());

                        }

                    }
                } catch (SQLException ex) {
                    isSuccess = false;
                    Log.e("ERROR", ex.getMessage().toString());
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String r) {

            Toast.makeText(LoginAdminActivity.this, r, Toast.LENGTH_SHORT).show();


            if (isSuccess) {

                SharedPreferences prefs = LoginAdminActivity.this.getSharedPreferences(StaticValues.PREF_USER, Context.MODE_PRIVATE);
                SharedPreferences.Editor edt = prefs.edit();

                edt.putBoolean("etatuser", true);
                edt.putString("NomUtilisateur", NomUtilisateur);
                edt.putString("NomPrenom", NomPrenom);
                edt.putString("MotDePasse", MotDePasse);
                edt.putString("CodeFonction", CodeFonction);
                edt.putString("Fonction", LibelleFonction);
                edt.putString("CodeRepresentant", CodeRepresentant);
                //edt.putString("CodeDepot", CodeDepot);
                //edt.putString("Depot", Depot);

                edt.commit();



                Intent i = new Intent(LoginAdminActivity.this, HomeActivity.class);
                startActivity(i);
                finish();


                Toast.makeText(getApplicationContext(), NomUtilisateur, Toast.LENGTH_LONG).show();

              /*  CheckUserPointed checkUserPointed  =  new CheckUserPointed(NomUtilisateur);
                checkUserPointed.execute();*/


            } else {

                 Toast.makeText(getApplicationContext()," Verifiez Vos  Login et Mot de passe ",Toast.LENGTH_SHORT).show();
                  btn_connexion.setText("Connexion");
                  btn_connexion.setEnabled(true);

            }

        }
    }

}