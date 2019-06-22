package com.example.andrew.cdb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lv1_list, lv2_list, lv3_list;
    private LinearLayout lv1, lv2, lv3, lv4;
    private DatabaseAdapterLibrary adapterLibrary;
    private DatabaseAdapterMy adapterMy;
    ArrayAdapter<ComicLibrary> libraryArrayAdapter;

    private DatabaseAdapterLibrary adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv1 = (LinearLayout)findViewById(R.id.lv1);
        lv2 = (LinearLayout)findViewById(R.id.lv2);
        lv3 = (LinearLayout)findViewById(R.id.lv3);
        lv4 = (LinearLayout)findViewById(R.id.lv4);

        lv1_list = (ListView)findViewById(R.id.lv1_list);
        lv2_list = (ListView)findViewById(R.id.lv2_list);
        lv3_list = (ListView)findViewById(R.id.lv3_list);

        adapterLibrary = new DatabaseAdapterLibrary(this);
        adapterMy = new DatabaseAdapterMy(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        lv1.setVisibility(View.VISIBLE);

        adapterLibrary.open();

        List<String> publishers = adapterLibrary.getPublishers();
        ArrayAdapter<String> publ = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, publishers);
        lv1_list.setAdapter(publ);
        adapterLibrary.close();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_mycomics) {
            // Handle the camera action
        } else if (id == R.id.nav_library) {
            Intent intent=new Intent(this, Info.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent=new Intent(this, Info.class);
            startActivity(intent);
        } else if (id == R.id.nav_info) {
            Intent intent=new Intent(this, Info.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }
}
