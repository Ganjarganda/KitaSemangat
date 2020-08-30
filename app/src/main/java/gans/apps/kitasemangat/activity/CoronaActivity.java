package gans.apps.kitasemangat.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gans.apps.kitasemangat.MyApplication;
import gans.apps.kitasemangat.R;
import gans.apps.kitasemangat.adapter.CoronaProvinsiAdapter;
import gans.apps.kitasemangat.model.CoronaProvinsi;

public class CoronaActivity extends AppCompatActivity {

    Context context;
    Toolbar toolbar;
    ActionBar actionBar;

    SwipeRefreshLayout pullToRefresh;
    ShimmerFrameLayout shimmer_view;
    LinearLayout ll_data;

    /*total*/
    TextView tv_total_positif, tv_total_sembuh, tv_total_meniggal, tv_total_dirawat;

    /*search*/
    EditText et_search;

    /*JSON*/
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<CoronaProvinsi> coronaProvinsiList;
    CoronaProvinsiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);

        context = this;
        toolbar = findViewById(R.id.toolbar);
        setupToolbar();

        pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        shimmer_view = findViewById(R.id.shimmer_view);
        ll_data = findViewById(R.id.ll_data);

        tv_total_positif = findViewById(R.id.tv_total_positif);
        tv_total_sembuh = findViewById(R.id.tv_total_sembuh);
        tv_total_meniggal = findViewById(R.id.tv_total_meniggal);
        tv_total_dirawat = findViewById(R.id.tv_total_dirawat);

        et_search = findViewById(R.id.et_search);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        coronaProvinsiList = new ArrayList<>();
        adapter = new CoronaProvinsiAdapter(context, coronaProvinsiList);

        refreshData();

        pullToRefresh.setOnRefreshListener(() -> {
            pullToRefresh.setRefreshing(true);
            showAnimationData(true);
            (new Handler()).postDelayed(() -> {
                pullToRefresh.setRefreshing(false);
                refreshData();
            }, 2000);
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String nextText) {
        ArrayList<CoronaProvinsi> dataFilter = new ArrayList<>();
        for (CoronaProvinsi data : coronaProvinsiList) {

            if (data.getProvinsi().toLowerCase().contains(nextText.toLowerCase())){
                dataFilter.add(data);
            }
        }
        adapter.setFilter(dataFilter);
    }

    private void refreshData() {
        /*Total Indonesia*/
        String url_total_corona_indonesia = "https://api.kawalcorona.com/indonesia/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_total_corona_indonesia, (Response.Listener<String>) response -> {
            try {
                showAnimationData(false);
                JSONArray array = new JSONArray(response);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    tv_total_positif.setText(object.getString("positif"));
                    tv_total_sembuh.setText(object.getString("sembuh"));
                    tv_total_meniggal.setText(object.getString("meninggal"));
                    tv_total_dirawat.setText(object.getString("dirawat"));
                }
            } catch (Exception e) {
                showAnimationData(true);
                MyApplication.give_tag(e.getMessage() + "(11a)");
                Toast.makeText(context, e.toString() + "(11a)", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            showAnimationData(true);
            MyApplication.give_tag(error.getMessage() + "(11b)");
            Toast.makeText(context, error.getMessage() + "(11b)", Toast.LENGTH_SHORT).show();
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        /*Corona Provinsi*/
        String url_corona_provinsi = "https://api.kawalcorona.com/indonesia/provinsi/";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url_corona_provinsi, (Response.Listener<String>) response -> {
            try {
                coronaProvinsiList.clear();
                showAnimationData(false);
                JSONArray array1 = new JSONArray(response);
                for (int i = 0; i < array1.length(); i++) {
                    JSONObject object1 = array1.getJSONObject(i);
                    JSONObject object2 = object1.getJSONObject("attributes");

                    coronaProvinsiList.add(new CoronaProvinsi(
                            object2.getString("FID"),
                            object2.getString("Kode_Provi"),
                            object2.getString("Provinsi"),
                            object2.getString("Kasus_Posi"),
                            object2.getString("Kasus_Semb"),
                            object2.getString("Kasus_Meni")
                    ));
                }
                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                showAnimationData(true);
                MyApplication.give_tag(e.getMessage() + "(22a)");
                Toast.makeText(context, e.toString() + "(22a)", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            showAnimationData(true);
            MyApplication.give_tag(error.getMessage() + "(22b)");
            Toast.makeText(context, error.getMessage() + "(22b)", Toast.LENGTH_SHORT).show();
        });
        RequestQueue requestQueue1 = Volley.newRequestQueue(context);
        requestQueue1.add(stringRequest1);

    }

    private void showAnimationData(boolean b) {
        if (b) {
            shimmer_view.startShimmer();
            shimmer_view.setVisibility(View.VISIBLE);
            ll_data.setVisibility(View.GONE);
        } else {
            shimmer_view.stopShimmer();
            shimmer_view.setVisibility(View.GONE);
            ll_data.setVisibility(View.VISIBLE);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            String name_toolbar = "Covid-19 (Corona)";
            getSupportActionBar().setTitle(name_toolbar);
        }
        toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();
        shimmer_view.startShimmer();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        shimmer_view.stopShimmer();

    }

}