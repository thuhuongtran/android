package com.vtcac.thuhuong.notificationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        TextView tvReply = findViewById(R.id.tvReply);
        String email = getIntent().getStringExtra("reply_to");
        tvReply.setText(email);
    }
}
