package com.vtcac.thuhuong.lab6.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vtcac.thuhuong.lab6.R;
import com.vtcac.thuhuong.lab6.beans.BeanMessage;

import java.util.ArrayList;

public class SendSMSEmailActivity extends AppCompatActivity {

    private Spinner spListFriend;
    ArrayList<String> friends = new ArrayList<>();
    EditText etContentMsg;
    ImageView ivBack;
    TextView tvHeaderTitle;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms_email);
        final Intent intent = new Intent(getBaseContext(), SendSMSEmailActivity.class);
        spListFriend = findViewById(R.id.spListFriend);
        etContentMsg = findViewById(R.id.etContentMsg);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        btnSend = findViewById(R.id.btnSend);

        // arrow back screen
        ivBack = findViewById(R.id.ivBackHome);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        friends.add("Mr. A");
        friends.add("Mr. B");
        friends.add("Mr. C");
        friends.add("Mr. D");
        friends.add("Mr. E");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.bg_drop_menu, friends);
        spListFriend.setAdapter(adapter);

        Intent receivedIntent = getIntent();
        final String action = receivedIntent.getStringExtra("action");
        switch (action) {
            case "send_sms": {
                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = spListFriend.getSelectedItem().toString();
                        String content = etContentMsg.getText().toString();
                        intent.putExtra("new_msg", new BeanMessage(name, content, "sms"));
                        Toast.makeText(getBaseContext(), "Send SMS successfully.", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });

            }
            break;
            case "send_email": {
                tvHeaderTitle.setText("Send Email");
                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = spListFriend.getSelectedItem().toString();
                        String content = etContentMsg.getText().toString();
                        intent.putExtra("new_msg", new BeanMessage(name, content, "email"));
                        Toast.makeText(getBaseContext(), "Send email successfully.", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });

            }
            break;
        }


    }
}
