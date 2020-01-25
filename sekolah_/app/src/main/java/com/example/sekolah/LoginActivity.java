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
import session.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private Button btnLinkRegister;

    //login
    private EditText nis, Password;
    private Button Login;

    private ProgressDialog pDialog;

    //ini sesion
//    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRequestQueue = Volley.newRequestQueue(this);

        //user interfaces loading

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        //batas interfaces

        //sesion
//        session = new SessionManager(this);
//
//        if (session.isLoggedIn()) {
//            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(i);
//            finish();
//        }

        //login
        nis = (EditText) findViewById(R.id.edtnis);
        Password = (EditText) findViewById(R.id.edtpassword);

        Login = (Button) findViewById(R.id.btnlogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strnis = nis.getText().toString();
                String strpassword = Password.getText().toString();

                if (strnis.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nis tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else if (strnis.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong",
                            Toast.LENGTH_LONG).show();
                }else {
                    login(strnis,strpassword);
                }
            }
        });


//tambhakan
        getSupportActionBar().hide();

        btnLinkRegister = (Button) findViewById(R.id.btnlinkregister);

        btnLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, daftarActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void login(String Nis, String Password) {
        //final String URL = "/volley/resource/12";
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("nis", Nis);
        params.put("password", Password);
        //userinterfaces
        pDialog.setMessage("Mohon Tunggu");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.getlogin, new JSONObject(params),
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
//                                session.setLogin(true);
                                msg = response.getString("pesan");

                                Intent a = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(a);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            //batas error

//                            VolleyLog.v("Response:%n %s", response.toString(4));
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
