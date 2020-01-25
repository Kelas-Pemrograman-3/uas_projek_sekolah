package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sekolah.R;
import com.example.sekolah.detailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import model.sekolahModel;

public class adapterSekolah extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<sekolahModel> item;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public adapterSekolah(Activity activity, List<sekolahModel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
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
            convertView = inflater.inflate(R.layout.contentsekolah, null);


        TextView kodepelajaran= (TextView) convertView.findViewById(R.id.txtkodepelajaran);
        TextView namapelajaran = (TextView) convertView.findViewById(R.id.txtnamapelajaran);
        TextView namaguru = (TextView) convertView.findViewById(R.id.txtnamaguru);
        Button btnDetail = (Button) convertView.findViewById(R.id.btnDetail);


        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, detailActivity.class);
                i.putExtra("_id", item.get(position).get_id());
                i.putExtra("kodepelajaran", item.get(position).getKodepelajaran());
                i.putExtra("namapelajaran", item.get(position).getNamapelajaran());
                i.putExtra("jam", item.get(position).getJam());
                i.putExtra("hari", item.get(position).getHari());
                i.putExtra("ruangan", item.get(position).getRuangan());
                i.putExtra("nign", item.get(position).getNign());
                i.putExtra("namaguru", item.get(position).getNamaguru());
                i.putExtra("detailorupdate", "detail");
                activity.startActivity(i);
                activity.finish();
            }
        });


        kodepelajaran.setText(item.get(position).getKodepelajaran());
        namapelajaran.setText(item.get(position).getNamapelajaran());
        namaguru.setText(item.get(position).getNamaguru());

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
