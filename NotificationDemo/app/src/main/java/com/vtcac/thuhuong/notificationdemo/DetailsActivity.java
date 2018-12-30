package com.vtcac.thuhuong.notificationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvDetail = findViewById(R.id.tvDetails);
        String message = getIntent().getStringExtra("message");
        tvDetail.setText(message);
    }
}
