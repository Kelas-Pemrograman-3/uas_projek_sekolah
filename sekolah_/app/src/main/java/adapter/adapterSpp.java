package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.sekolah.DetailSppActivity;
import com.example.sekolah.R;
import com.example.sekolah.detailActivity;

import java.util.ArrayList;
import java.util.List;

import model.sekolahModel;
import model.sppModel;

public class adapterSpp extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<sppModel> item;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public adapterSpp(Activity activity, ArrayList<sppModel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
        this.activity = activity;
        this.item = item;
        this.mRequestQueue = mRequestQueue;
        this.pDialog = pDialog;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.contenspp, null);


        TextView Nis = (TextView) convertView.findViewById(R.id.txtnis);
        TextView Nama = (TextView) convertView.findViewById(R.id.txtnama);
        TextView Spp = (TextView) convertView.findViewById(R.id.txtspp);
        TextView Status = (TextView) convertView.findViewById(R.id.txtstatus);
//        Button btnDetail = (Button) convertView.findViewById(R.id.btnDetail);
//
//
//        btnDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(activity, DetailSppActivity.class);
//                i.putExtra("_id", item.get(position).get_id());
//                i.putExtra("nis", item.get(position).getNis());
//                i.putExtra("nama", item.get(position).getNama());
//                i.putExtra("spp", item.get(position).getSpp_bulan());
//                i.putExtra("status", item.get(position).getStatus());
//                i.putExtra("detailorupdate", "detail");
//                activity.startActivity(i);
//                activity.finish();
//            }
//        });

        Nis.setText(item.get(position).getNis());
        Nama.setText(item.get(position).getNama());
        Spp.setText(item.get(position).getSpp_bulan());
        Status.setText(item.get(position).getStatus());

        return convertView;
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
