package com.vtcac.thuhuong.lab3_lesson3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bExc1;
    Button bExc2;
    Button bExc3;
    Button btnDrawerLayoutDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bExc1 = findViewById(R.id.bExc1);
        bExc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Excercise1Activity.class);
                startActivity(intent);
            }
        });

        bExc2 = findViewById(R.id.bExc2);
        bExc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Excercise2Activity.class);
                startActivity(intent);
            }
        });

        bExc3 = findViewById(R.id.bExc3);
        bExc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AlarmExercise3Activity.class);
                startActivity(intent);
            }
        });

        btnDrawerLayoutDemo = findViewById(R.id.btnDrawerLayoutDemo);
        btnDrawerLayoutDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DrawerLayoutDemoActivity.class);
                startActivity(intent);
            }
        });
    }
}
