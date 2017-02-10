package com.bink.mybink.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bink.mybink.R;
import com.bink.mybink.model.ImageModel;
import com.bink.mybink.utils.Constants;
import com.bink.mybink.utils.GalleryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowFlagsActivity extends BaseActivity {

    @BindView(R.id.button_show_countries)
    Button button_show_Countries;

    @BindView(R.id.recycleView_flag_list)
    RecyclerView mRecyclerView;

    JSONArray jsonarray;
    GalleryAdapter mAdapter;
    ArrayList<ImageModel> imageList = new ArrayList<>();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_flags);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new GalleryAdapter(getApplicationContext(), imageList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.button_show_countries)
    public void onShowCountriesClicked(){

        button_show_Countries.setEnabled(false);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest req  = new JsonObjectRequest(Request.Method.GET, Constants.flagUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hidePDialog();
                        JSONObject jsonObject = response;
                        try {
                            jsonarray = jsonObject.getJSONArray("worldpopulation");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                jsonObject = jsonarray.getJSONObject(i);
                                ImageModel imageModel = new ImageModel();
                                imageModel.setUrl(jsonObject.getString("flag"));
                                imageList.add(imageModel);
                            }
                        } catch (JSONException e) {
                            Log.e("Error", e.getMessage());
                            e.printStackTrace();
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("d","d");
                hidePDialog();
            }
        }
        );
        req.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
