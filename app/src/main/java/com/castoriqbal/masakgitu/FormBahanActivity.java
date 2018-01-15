package com.castoriqbal.masakgitu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.fujiyuu75.sequent.Animation;
import com.fujiyuu75.sequent.Sequent;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FormBahanActivity extends AppCompatActivity {
    private static final String URL_ARRAY_NAMA_BAHAN = "http://masakini.xyz/masakgituapi/namaBahan.php";
    int jumlahBahan;
    LinearLayout linearLayout;
    ArrayAdapter<String> adapter;
    Button button;
    Intent intent;
    ArrayList<String> listBahan = new ArrayList<>();
    private ArrayList<String> listNamaBahan = new ArrayList<>();
    Response.Listener<String> listener;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_form_bahan);

        jumlahBahan = Integer.parseInt(getIntent().getStringExtra("jumlahBahan"));
        linearLayout = findViewById(R.id.linear_layout_form_bahan);
        button = findViewById(R.id.button_search_bahan);
        button.setEnabled(false);
        intent = new Intent(this, SearchActivity.class);
        adapter = new ArrayAdapter<String>(this, R.layout.simple_dropdown_item, listNamaBahan);

        listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listNamaBahan.clear();

                try {
                    JSONArray jsonArrayResponse = new JSONArray(response);
                    for(int i = 0; i < jsonArrayResponse.length(); i++){
                        listNamaBahan.add(i,jsonArrayResponse.get(i).toString().toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.clear();
                adapter.addAll(listNamaBahan);
                adapter.notifyDataSetChanged();
            }
        };

        request = new StringRequest(URL_ARRAY_NAMA_BAHAN, listener, null);
        VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue().add(request);

        for(int i = 0; i < jumlahBahan; i++){
            final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) getLayoutInflater().inflate(R.layout.auto_complete_text_view,
                    linearLayout, false);
            autoCompleteTextView.setId(i);
            autoCompleteTextView.setAdapter(adapter);
            autoCompleteTextView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            autoCompleteTextView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            autoCompleteTextView.setSingleLine(true);
            autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if(s.toString().equals("")){
                        autoCompleteTextView.setError("Field can't be Empty");
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(!s.toString().equals("")){
                        autoCompleteTextView.setError(null);
                    }else{
                        autoCompleteTextView.setError("Field can't be Empty");
                    }
                    validateEmpty();
                }
            });
            linearLayout.addView(autoCompleteTextView);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < jumlahBahan; i++){
                    AutoCompleteTextView autoCompleteTextView = linearLayout.findViewById(i);
                    listBahan.add(autoCompleteTextView.getText().toString());
                }
                intent.putStringArrayListExtra("listBahan",listBahan);
                startActivity(intent);
            }
        });

        Sequent.origin(linearLayout).duration(400).anim(this, Animation.BOUNCE_IN).start();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listBahan.clear();
    }

    protected void validateEmpty(){
        int emptyFlag = 0;
        for(int i = 0; i < jumlahBahan; i++){
            AutoCompleteTextView autoCompleteTextView = linearLayout.findViewById(i);
            if(autoCompleteTextView.getText().toString().equals("")){
                emptyFlag++;
            }
        }
        if(emptyFlag == 0){
            button.setEnabled(true);
        }else{
            button.setEnabled(false);
        }
    }

}
