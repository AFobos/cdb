package com.example.andrew.cdb;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class Info extends AppCompatActivity {
    ImageView ivImageFromUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ivImageFromUrl = (ImageView) findViewById(R.id.iv_image_from_url);
        Picasso.with(getApplicationContext())
                .load("https://pp.userapi.com/c622417/v622417613/2bbb0/W1wr0ZojW9Y.jpg")
                .into(ivImageFromUrl);


    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
