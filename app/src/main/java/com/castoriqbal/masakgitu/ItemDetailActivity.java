package com.castoriqbal.masakgitu;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.toolbox.NetworkImageView;
import com.fujiyuu75.sequent.Animation;
import com.fujiyuu75.sequent.Sequent;
import com.matrixxun.starry.badgetextview.MaterialBadgeTextView;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class ItemDetailActivity extends AppCompatActivity {
    private Resep resep;
    LinearLayout linearLayoutFront;
    LinearLayout linearLayoutBack;
    NetworkImageView imageView;
    EasyFlipView easyFlipView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_item_detail);

        resep = (Resep) getIntent().getSerializableExtra("resep");
        easyFlipView = findViewById(R.id.easy_flip_view_item);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(resep.getJudulResep());
        setSupportActionBar(toolbar);
        linearLayoutFront = findViewById(R.id.linear_layout_front);
        linearLayoutBack = findViewById(R.id.linear_layout_back);
        imageView = findViewById(R.id.app_bar_image);
        imageView.setImageUrl(resep.getResepImage(),VolleySingleton.getInstance(this.getApplicationContext()).getImageLoader());

        for(int i = 0; i < resep.getBahan().size(); i++){
            View view = getLayoutInflater().inflate(R.layout.text_bahan, linearLayoutFront, false);
            TextView textViewTakaranBahan = view.findViewById(R.id.text_takaran_bahan);
            TextView textViewNamaBahan = view.findViewById(R.id.text_nama_bahan);
            textViewTakaranBahan.setText(resep.getBahan().get(i).getTakaran());
            textViewNamaBahan.setText(resep.getBahan().get(i).getNama());
            linearLayoutFront.addView(view);
        }

        for(int i = 0; i < resep.getStep().size(); i++){
            View view = getLayoutInflater().inflate(R.layout.text_step, linearLayoutBack, false);
            MaterialBadgeTextView materialBadgeTextView = view.findViewById(R.id.drawable_count_step);
            TextView textViewJudulStep = view.findViewById(R.id.text_judul_step);
            TextView textViewPenjelasanStep = view.findViewById(R.id.text_penjelasan_step);
            materialBadgeTextView.setBadgeCount(i+1);
            textViewJudulStep.setText(resep.getStep().get(i).getJudul());
            textViewPenjelasanStep.setText(resep.getStep().get(i).getPenjelasan());
            linearLayoutBack.addView(view);
        }

    }
}
