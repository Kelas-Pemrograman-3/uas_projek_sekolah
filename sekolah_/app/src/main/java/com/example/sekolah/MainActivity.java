package com.example.sekolah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;
import session.SessionManager;

public class MainActivity extends AppCompatActivity {

//    private SessionManager session;

    private CircularRevealCardView keluar,jadwal,spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jadwal = (CircularRevealCardView) findViewById(R.id.Card);
        jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, listSekolah.class);
                startActivity(a);
                finish();
            }
        });

        spp = (CircularRevealCardView) findViewById(R.id.card2);
        spp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, listSppActivity.class);
                startActivity(a);
                finish();
            }
        });

//        session = new SessionManager(this);
        keluar = (CircularRevealCardView) findViewById(R.id.card3);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                session.setLogin(false);
//                session.setSkip(false);
//                session.setSessid(0);

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    }

