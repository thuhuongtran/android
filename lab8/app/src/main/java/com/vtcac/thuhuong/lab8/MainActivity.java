package com.vtcac.thuhuong.lab8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(sp.getString("username",null)!=null){
            Intent intent = new Intent(MainActivity.this, WelcomePageActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
