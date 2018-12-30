package com.vtcac.thuhuong.resourceconfigchangedemo;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnOrder;
    String order;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LaucherActivity.class);
        startActivity(intent);
    }

    // config change

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return sum ;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("save_order", order);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        order = savedInstanceState.getString("save_order");
        btnOrder.setText(order);

    }
}
