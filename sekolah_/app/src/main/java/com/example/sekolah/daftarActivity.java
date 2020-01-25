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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;

public class daftarActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;

    private Button btnlinkmasuk;
    //inputdata
    private EditText edtNis, edtNama,edtBirth,edtGender,edtPhone, edtEmail, edtPassword;
    private Button btnKirim;

    //user interfaces loading
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        mRequestQueue = Volley.newRequestQueue(this);
        getSupportActionBar().hide();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        //batas interfaces

        edtNis = (EditText) findViewById(R.id.EdtNis);
        edtNama = (EditText) findViewById(R.id.Edtnama);
        edtBirth = (EditText) findViewById(R.id.Edtbirth);
        edtGender = (EditText) findViewById(R.id.Edtgender);
        edtPhone = (EditText) findViewById(R.id.Edtphone);
        edtEmail = (EditText) findViewById(R.id.EdtEmail);
        edtPassword = (EditText) findViewById(R.id.EdtPassword);

        btnKirim = (Button) findViewById(R.id.BTNregister);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String STRnis = edtNis.getText().toString();
                String STRnama = edtNama.getText().toString();
                String STRbirth = edtBirth.getText().toString();
                String STRgender = edtGender.getText().toString();
                String STRphone = edtPhone.getText().toString();
                String STRemail = edtEmail.getText().toString();
                String STRpass = edtPassword.getText().toString();

                if(STRnis.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nis tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (STRnama.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (STRbirth.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (STRgender.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (STRphone.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (STRemail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else if (STRpass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "password tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                } else {
                    inputdata(STRnis,STRnama,STRbirth,STRgender,STRphone,STRemail,STRpass);
                }

            }
        });

        btnlinkmasuk = (Button) findViewById(R.id.btnlinklogin);

        btnlinkmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void inputdata(String NIS, String NAMA, String BIRTH, String GENDER, String PHONE ,String EMAIL, String PASSWORD) {
        //final String URL = "/volley/resource/12";
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("nis", NIS);
        params.put("nama", NAMA);
        params.put("birth", BIRTH);
        params.put("gender", GENDER);
        params.put("phone", PHONE);
        params.put("email", EMAIL);
        params.put("password", PASSWORD);
        //userinterfaces
        pDialog.setMessage("Mohon Tunggu");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.inputdatamhs, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //userinterfaces
                        hideDialog();

                        try {
                            //pesan error
                            boolean status = response.getBoolean("error");
                            String msg;
                            if (status == true){
                                msg = response.getString("pesan");
                            }else {
                                msg = response.getString("pesan");

                                Intent a = new Intent(daftarActivity.this, LoginActivity.class);
                                startActivity(a);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            //batas error

                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //user interfaces
                hideDialog();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
//ApplicationController.getInstance().addToRequestQueue(req);
        mRequestQueue.add(req);
    }
    //user interfaces loading
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();

    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    //batas interfaces loading

}
