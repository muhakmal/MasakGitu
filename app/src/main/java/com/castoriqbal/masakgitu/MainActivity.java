package com.castoriqbal.masakgitu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import io.fabianterhorst.isometric.Color;
import io.fabianterhorst.isometric.Isometric;
import io.fabianterhorst.isometric.IsometricView;
import io.fabianterhorst.isometric.Point;
import io.fabianterhorst.isometric.shapes.Cylinder;
import io.fabianterhorst.isometric.shapes.Prism;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    IsometricView isometricView;
    Color millenialPink;
    Prism cube;
    TextView textView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        isometricView = findViewById(R.id.isometric_view);
        millenialPink = new Color(255,182,193);
        cube = new Prism(new Point(3,3,2.8),1.5,1.5,1.5);
        isometricView.add(cube, millenialPink);
        isometricView.add(cube.translate(0,0,1.7), millenialPink);
        isometricView.add(cube.translate(0,0,-1.7), millenialPink);
        textView = findViewById(R.id.text_view_main);

        intent = new Intent(this, JumlahBahanActivity.class);

        isometricView.setClickListener(new IsometricView.OnItemClickListener() {
            @Override
            public void onClick(@NonNull Isometric.Item item) {
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
