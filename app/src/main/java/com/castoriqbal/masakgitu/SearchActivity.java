package com.castoriqbal.masakgitu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.fujiyuu75.sequent.Animation;
import com.fujiyuu75.sequent.Sequent;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ResepListAdapter resepListAdapter;
    static ArrayList<String> listBahan = new ArrayList<>();
    ArrayList<Resep> listResep = new ArrayList<>();
    RequestQueue requestQueue;
    Response.Listener<String> listener;
    Response.ErrorListener errorListener;
    ResepRequest resepRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recycler_view_search);
        resepListAdapter = new ResepListAdapter(listResep);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(resepListAdapter);
        listBahan = getIntent().getStringArrayListExtra("listBahan");
        requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonArray jsonResponse = new JsonParser().parse(response).getAsJsonArray();
                Type listType = new TypeToken<ArrayList<Resep>>(){}.getType();
                listResep = new Gson().fromJson(jsonResponse, listType);
                resepListAdapter.setListResep(listResep);
                resepListAdapter.notifyDataSetChanged();
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        resepRequest = new ResepRequest(listBahan, listener, errorListener);

        requestQueue.add(resepRequest);
    }

}