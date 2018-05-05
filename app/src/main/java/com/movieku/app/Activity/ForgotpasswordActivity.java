package com.movieku.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.movieku.app.R;

public class ForgotpasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
