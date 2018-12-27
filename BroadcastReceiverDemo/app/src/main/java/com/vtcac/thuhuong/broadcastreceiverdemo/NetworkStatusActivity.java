package com.vtcac.thuhuong.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NetworkStatusActivity extends AppCompatActivity {
    TextView tvNetworkStatus;
    TextView tvType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_status);
        tvNetworkStatus = findViewById(R.id.tvNetworkStatus);
        tvType = findViewById(R.id.tvType);

        BroadcastNetwork broadcastNetwork = new BroadcastNetwork();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");

        registerReceiver(broadcastNetwork, intentFilter);
    }

    class BroadcastNetwork extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                tvNetworkStatus.setText("CONNECTED");
                switch (networkInfo.getType()){
                    case ConnectivityManager.TYPE_WIFI:
                        tvType.setText("WIFI");
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        tvType.setText("3G");
                        break;
                }
            } else {
                tvNetworkStatus.setText("DISCONNECTED");
            }
        }
    }
}
