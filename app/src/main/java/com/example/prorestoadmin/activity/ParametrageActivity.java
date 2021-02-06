package com.example.prorestoadmin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prorestoadmin.R;
import com.example.prorestoadmin.connexion.ConnectionClass;
import com.example.prorestoadmin.connexion.StaticValues;

public class ParametrageActivity extends AppCompatActivity {


    boolean st = false;

    Button btn_connect_server, btn_cancel;
    EditText edtip, edtpass, edtbase, edtuser;


    ConnectionClass connectionClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametrage);

        Log.e("Parm" , "onCreate") ;
        connectionClass = new ConnectionClass();

        btn_connect_server = (Button) findViewById(R.id.btn_connect_server);
        btn_cancel         = (Button) findViewById(R.id.btn_cancel);

        edtip  =  (EditText) findViewById(R.id.ed_ip_server);
        edtbase = (EditText) findViewById(R.id.ed_db_server);
        edtuser = (EditText) findViewById(R.id.ed_user_server);
        edtpass = (EditText) findViewById(R.id.ed_password_server);



        SharedPreferences prefs =   getSharedPreferences(StaticValues.PEF_SERVER, Context.MODE_PRIVATE);
        final SharedPreferences.Editor edt = prefs.edit();
        edt.putBoolean("etatsql", false);

        if( prefs.contains("ip") && prefs.contains("base") ) {
            String _ip = prefs.getString("ip", "");
            String _base = prefs.getString("base", "");

            edtip.setText(_ip);
            edtbase.setText(_base);

        }


        btn_connect_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edt.putBoolean("etatsql", true);

                edt.putString("user",     edtuser.getText().toString());
                edt.putString("password", edtpass.getText().toString());
                edt.putString("base",     edtbase.getText().toString());
                edt.putString("ip",       edtip  .getText().toString());

                edt.commit();

                finish();
                Intent intent = new Intent(getApplicationContext(), SplachScreenActivity.class);
                startActivity(intent);

            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}