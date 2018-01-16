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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BuatAkunActivity extends AppCompatActivity {

    AutoCompleteTextView etNama;
    AutoCompleteTextView etEmail;
    AutoCompleteTextView etPassword;
    AutoCompleteTextView etNotelp;
    AutoCompleteTextView etAlamat;

    Button btnDaftar;
    CardView progressCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_buat_akun);
        progressCard = findViewById(R.id.progressCard);

        etNama = findViewById(R.id.et_nama_daftar);
        etEmail = findViewById(R.id.et_email_daftar);
        etPassword = findViewById(R.id.et_password_daftar);
        etNotelp = findViewById(R.id.et_notelp_daftar);
        etAlamat = findViewById(R.id.et_alamat_daftar);
        btnDaftar = findViewById(R.id.btn_daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String no_hp = etNotelp.getText().toString();
                String alamat_lengkap = etAlamat.getText().toString();

                progressCard.setVisibility(View.VISIBLE);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(BuatAkunActivity.this, "Data anda telah tersimpan, silahkan masuk dengan data anda.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(BuatAkunActivity.this, MasukActivity.class);
                                BuatAkunActivity.this.startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BuatAkunActivity.this);
                                builder.setMessage("User sudah terdaftar dan atau format yang anda masukkan salah")
                                        .setTitle("Buat Akun Gagal")
                                        .setNegativeButton("Coba lagi", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressCard.setVisibility(View.GONE);
                    }
                };
                BuatAkunRequest buatAkunRequest = new BuatAkunRequest(nama, email, password, no_hp, alamat_lengkap, responseListener);
                VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().add(buatAkunRequest);
            }
        });




    }
}
