package com.castoriqbal.masakgitu;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hariofspades.incdeclibrary.IncDecCircular;
import com.john.waveview.WaveView;
import com.simmorsal.recolor_project.ReColor;

import java.util.ArrayList;

public class JumlahBahanActivity extends AppCompatActivity {
    ConstraintLayout constraintLayoutJumlahBahan;
    WaveView waveView;
    ReColor reColor;
    IncDecCircular incDecCircular;
    ArrayList<String> values = new ArrayList<>();
    Button button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_jumlah_bahan);

        intent = new Intent(this, FormBahanActivity.class);

        constraintLayoutJumlahBahan = findViewById(R.id.constraint_layout_jumlah_bahan);
        waveView = findViewById(R.id.wave_view_jumlah_bahan);

        reColor = new ReColor(this);
        reColor.setViewBackgroundColor(constraintLayoutJumlahBahan, "#"+Integer.toHexString(ContextCompat.getColor(this, R.color.millenialPink) & 0xffffff),
                "#"+Integer.toHexString(ContextCompat.getColor(this, R.color.millenialPink02) & 0xffffff), 2000);

        for(int i = 1; i <= 10; i++){
            values.add(Integer.toString(i));
        }
        incDecCircular = findViewById(R.id.increment_decrement_buttons);
        incDecCircular.setConfiguration(LinearLayout.HORIZONTAL,IncDecCircular.TYPE_ARRAY,
                IncDecCircular.DECREMENT,IncDecCircular.INCREMENT);
        incDecCircular.setArrayList(values);
        incDecCircular.setArrayIndexes(0,values.size()-1,1);
        incDecCircular.enableLongPress(true, true, 500);
        incDecCircular.setOnValueChangeListener(new IncDecCircular.OnValueChangeListener() {
            @Override
            public void onValueChange(IncDecCircular view, float oldValue, float newValue) {
                int actualOldValue = ((int)oldValue)+1;
                int actualNewValue = ((int)newValue)+1;
                int oldWaveProgress = actualOldValue*10;
                int newWaveProgress = actualNewValue*10;
                waveView.setProgress(newWaveProgress);
            }
        });

        button = findViewById(R.id.button_search_bahan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("jumlahBahan",incDecCircular.getValue());
                startActivity(intent);
            }
        });
    }
}
