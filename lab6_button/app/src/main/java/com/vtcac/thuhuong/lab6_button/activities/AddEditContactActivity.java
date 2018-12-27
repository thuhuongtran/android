package com.vtcac.thuhuong.lab6_button.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vtcac.thuhuong.lab6_button.R;

public class AddEditContactActivity extends AppCompatActivity {
    ImageView ivBackHome;
    TextView tvHeaderTitle;
    Button btnAddEdit;
    EditText etContactName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);
        final Intent intent = new Intent(getBaseContext(), AddEditContactActivity.class);

        // arrow back
        ivBackHome = findViewById(R.id.ivBackHome);
        ivBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("action", "");
                setResult(RESULT_OK);
                finish();
            }
        });
        //btn add edit contact
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        btnAddEdit = findViewById(R.id.btnAddEditContact);
        etContactName = findViewById(R.id.etContactName);
        final Intent receivedIntent = getIntent();
        String action = receivedIntent.getStringExtra("action");
        switch (action) {
            case "edit":
                tvHeaderTitle.setText("Edit Contact");
                btnAddEdit.setText("Edit");
                String name = receivedIntent.getStringExtra("edit_name");
                etContactName.setText(name);
                btnAddEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newName = etContactName.getText().toString();
                        if (newName.length()>2){
                            intent.putExtra("new_name", newName);
                            intent.putExtra("action","edit_result");
                        }
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                });
                break;
            case "add":
                btnAddEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String addName = etContactName.getText().toString();
                        if(addName.length()>2){
                            intent.putExtra("add_name", addName);
                            intent.putExtra("action", "add_result");
                        }
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                break;
        }
    }

}
