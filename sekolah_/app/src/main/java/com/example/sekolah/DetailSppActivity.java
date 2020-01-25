package com.example.sekolah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class DetailSppActivity extends AppCompatActivity {

    EditText kodenis, kodenama, kodespp,kodeStatus;
    Button btnKirim;

    private RequestQueue mRequestQueue;

    private ProgressDialog pDialog;

    Intent intent;
    String detailorupdate, _id, knis, knama, kspp, kStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spp);

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        kodenis = (EditText) findViewById(R.id.idnis);
        kodenama = (EditText) findViewById(R.id.idnama);
        kodespp = (EditText) findViewById(R.id.idspp);
        kodeStatus = (EditText) findViewById(R.id.idstatus);

        intent = getIntent();
        detailorupdate = intent.getStringExtra("detailorupdate");
        _id         = intent.getStringExtra("_id");
        knis      = intent.getStringExtra("kodepelajaran");
        knama   = intent.getStringExtra("namapelajaran");
        kspp      = intent.getStringExtra("jam");
        kStatus     = intent.getStringExtra("hari");


        kodenis.setText(knis);
        kodenama.setText(knama);
        kodespp.setText(kspp);
        kodeStatus.setText(kStatus);

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DetailSppActivity.this, listSekolah.class);
        startActivity(i);
        finish();
    }
}
