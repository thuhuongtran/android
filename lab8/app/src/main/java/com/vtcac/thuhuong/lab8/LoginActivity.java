package com.vtcac.thuhuong.lab8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText etName;
    EditText etPass;
    Button btnSignIn;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = findViewById(R.id.etName);
        etPass = findViewById(R.id.etPass);
        btnSignIn = findViewById(R.id.btnSignIn);

        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sp.edit();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("username", etName.getText().toString());
                editor.putString("userpass", etPass.getText().toString());
                editor.commit();
                Intent intent = new Intent(getBaseContext(), WelcomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}
