package com.vtcac.thuhuong.lab9.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vtcac.thuhuong.lab9.R;
import com.vtcac.thuhuong.lab9.activities.HomeProductActivity;

public class MainActivity extends AppCompatActivity {
    public final static int REQUEST_CODE_ADD_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getBaseContext(), HomeProductActivity.class);
        startActivity(intent);
    }
}
