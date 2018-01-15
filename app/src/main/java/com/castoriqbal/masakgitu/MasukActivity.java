package com.castoriqbal.masakgitu;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MasukActivity extends AppCompatActivity {
    AutoCompleteTextView etEmail;
    AutoCompleteTextView etPassword;

    CardView progressCard;
    Button btnMasuk;
    TextView tvDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_masuk);
        progressCard = findViewById(R.id.progressCard);

        etEmail = findViewById(R.id.et_email_masuk);
        etPassword = findViewById(R.id.et_password_masuk);

        btnMasuk = findViewById(R.id.btn_masuk);
        tvDaftar = findViewById(R.id.tv_daftar);

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MasukActivity.this, BuatAkunActivity.class);
                startActivity(intent);
            }
        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                progressCard.setVisibility(View.VISIBLE);
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            boolean success = object.getBoolean("success");
                            if (success) {
                                Toast.makeText(MasukActivity.this, "Selamat, anda berhasil masuk", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MasukActivity.this, MainActivity.class);
                                MasukActivity.this.startActivity(intent);
                                MasukActivity.this.finish();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MasukActivity.this);
                                builder.setMessage(response)
                                        .setTitle("Masuk Gagal")
                                        .setMessage("Format email/password yang anda masukkan salah atau data anda tidak ada dalam database.")
                                        .setNegativeButton("Ulangi", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressCard.setVisibility(View.GONE);
                    }
                };
                MasukRequest masukRequest = new MasukRequest(email, password, listener);
                RequestQueue antrian = Volley.newRequestQueue(MasukActivity.this);
                antrian.add(masukRequest);
            }
        });

    }
}
