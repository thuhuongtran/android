package com.vtcac.thuhuong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.vtcac.thuhuong.R;
import com.vtcac.thuhuong.custom_list_view.CustomListViewDemoActivity;
import com.vtcac.thuhuong.dialog.Dialog12Demo;
import com.vtcac.thuhuong.read_n_file.FileDemoActivity;
import com.vtcac.thuhuong.view_control.AdvanceViewControlDemo;
import com.vtcac.thuhuong.view_control.LinearLayout;
import com.vtcac.thuhuong.view_control.RelativeLayout;
import com.vtcac.thuhuong.view_control.ViewControlActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnViewControl;
    Button btnAdvanced;
    Button btnRelativeLayoutDemo;
    Button btnLinearLayoutDemo;
    Button btnTableLayoutDemo;
    Button btnDialogDemo;
    Button btnCustomListViewDemo;
    Button btnReadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewControl = findViewById(R.id.btnViewControl);
        btnViewControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ViewControlActivity.class);
                startActivity(intent);
            }
        });
        btnAdvanced = findViewById(R.id.btnAdvanced);
        btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AdvanceViewControlDemo.class);
                startActivity(intent);
            }
        });
        btnRelativeLayoutDemo = findViewById(R.id.btnRelativeLayoutDemo);
        btnRelativeLayoutDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RelativeLayout.class);
                startActivity(intent);
            }
        });
        btnLinearLayoutDemo = findViewById(R.id.btnLinearLayoutDemo);
        btnLinearLayoutDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LinearLayout.class);
                startActivity(intent);
            }
        });
        btnDialogDemo = findViewById(R.id.btnDialog12Demo);
        btnDialogDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Dialog12Demo.class);
                startActivity(intent);
            }
        });
        btnCustomListViewDemo = findViewById(R.id.btnCustomeListViewDemo);
        btnCustomListViewDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CustomListViewDemoActivity.class);
                startActivity(intent);
            }
        });
        btnReadFile = findViewById(R.id.btnReadFile);
        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FileDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    //solution 2

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                break;

        }
    }
}