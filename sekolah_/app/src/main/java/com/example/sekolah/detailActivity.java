package com.example.sekolah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class detailActivity extends AppCompatActivity {

    EditText kdpelajaran, namapelajaran, jam,hari,ruangan,nign,namaguru;
    Button btnKirim;

    private RequestQueue mRequestQueue;

    private ProgressDialog pDialog;

    Intent intent;
    String detailorupdate, _id, kodepljrn, strNamapljrn, strJam, strHari, strRuangan, strNign, strNamaguru;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        kdpelajaran = (EditText) findViewById(R.id.kodepelajaran);
        namapelajaran = (EditText) findViewById(R.id.namapelajaran);
        jam = (EditText) findViewById(R.id.jam);
        hari = (EditText) findViewById(R.id.hari);
        ruangan = (EditText) findViewById(R.id.ruangan);
        nign = (EditText) findViewById(R.id.nign);
        namaguru = (EditText) findViewById(R.id.namaguru);

        intent = getIntent();
        detailorupdate = intent.getStringExtra("detailorupdate");
        _id         = intent.getStringExtra("_id");
        kodepljrn      = intent.getStringExtra("kodepelajaran");
        strNamapljrn   = intent.getStringExtra("namapelajaran");
        strJam      = intent.getStringExtra("jam");
        strHari     = intent.getStringExtra("hari");
        strRuangan  = intent.getStringExtra("ruangan");
        strNign     = intent.getStringExtra("nign");
        strNamaguru  = intent.getStringExtra("namaguru");

        kdpelajaran.setText(kodepljrn);
        namapelajaran.setText(strNamapljrn);
        jam.setText(strJam);
        hari.setText(strHari);
        ruangan.setText(strRuangan);
        nign.setText(strNign);
        namaguru.setText(strNamaguru);
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
        Intent i = new Intent(detailActivity.this, listSekolah.class);
        startActivity(i);
        finish();
    }
}
