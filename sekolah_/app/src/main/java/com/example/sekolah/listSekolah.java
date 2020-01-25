package com.example.sekolah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.adapterSekolah;
import model.sekolahModel;
import server.ConfigUrl;

public class listSekolah extends AppCompatActivity {

    ProgressDialog pDialog;

    adapterSekolah adapter;
    ListView list;

    ArrayList<sekolahModel> newsList = new ArrayList<sekolahModel>();

    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sekolah);
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new adapterSekolah(listSekolah.this, newsList, mRequestQueue, pDialog);
        list.setAdapter(adapter);
        getAllData();
    }
    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, ConfigUrl.getMk, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    sekolahModel sekolah = new sekolahModel();
                                    sekolah.set_id(jsonObject.getString("_id"));
                                    sekolah.setKodepelajaran(jsonObject.getString("kodepelajaran"));
                                    sekolah.setNamapelajaran(jsonObject.getString("namapelajaran"));
                                    sekolah.setJam(jsonObject.getString("jam"));
                                    sekolah.setHari(jsonObject.getString("hari"));
                                    sekolah.setRuangan(jsonObject.getString("ruangan"));
                                    sekolah.setNign(jsonObject.getString("nign"));
                                    sekolah.setNamaguru(jsonObject.getString("namaguru"));
//                                    String dataguru = jsonObject.getString("guru");
//                                    JSONArray arrayDosen = new JSONArray(dataguru);
//                                    for (int j = 0; j < arrayDosen.length(); j++){
//                                        JSONObject objDosen = arrayDosen.getJSONObject(j);
//                                        sekolah.setNign(objDosen.getString("nign"));
//                                        sekolah.setNamaguru(objDosen.getString("namaguru"));
//                                    }
                                    newsList.add(sekolah);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
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
    public void onBackPressed(){
        Intent i = new Intent(listSekolah.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
