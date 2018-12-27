package com.vtcac.thuhuong.intentremakedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity {
    EditText etName;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        etName = findViewById(R.id.etName);
        btnUpdate = findViewById(R.id.btnUpdate);
        Intent receivedIntent = getIntent();
        String name = receivedIntent.getStringExtra("user_name");
        etName.setText(name);

        if (name == null || name.equals("")) {
            btnUpdate.setText("ADD");
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etName.getText().toString();
                Intent intent = new Intent(getBaseContext(), ContactDetailActivity.class);
                intent.putExtra("new_name", newName);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }
}
