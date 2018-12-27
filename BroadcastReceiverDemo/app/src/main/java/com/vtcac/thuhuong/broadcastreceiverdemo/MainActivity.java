package com.vtcac.thuhuong.broadcastreceiverdemo;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    SwitchCompat swPower;

    // BroadCastReceiver transfer info between HomeService and Activity
    class BroadCastLampStatus extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean status = intent.getBooleanExtra("lamp_status", false);
            swPower.setChecked(status);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swPower = findViewById(R.id.swPower);

        // dang ky broadcast-receiver
        BroadCastLampStatus broadCastLampStatus = new BroadCastLampStatus();
        IntentFilter intentFilter = new IntentFilter();
        registerReceiver(broadCastLampStatus, intentFilter);

        Intent intent = new Intent(getBaseContext(), HomeService.class);
        startService(intent);
    }
}
