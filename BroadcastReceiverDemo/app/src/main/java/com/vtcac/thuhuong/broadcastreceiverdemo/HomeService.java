package com.vtcac.thuhuong.broadcastreceiverdemo;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

public class HomeService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "Home Service running", Toast.LENGTH_SHORT).show();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String provider = LocationManager.GPS_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }

        locationManager.requestLocationUpdates(provider, 1000, 20, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longtitude = location.getLongitude();
                Location home = new Location("home");
                home.setLatitude(20.995650);
                home.setLongitude(105.862646);

                // tinh khoang canh tu vi tri hien tai den nha cua minh
                float distance = home.distanceTo(location);
                if (distance <= 50) {
                    // bat den: turn on lamp
                    Intent intent  = new Intent();
                    intent.putExtra("lamp_status", true);
                    intent.setAction("detect_lamp_status");
                    sendBroadcast(intent);
                    Log.d("location demo ", "MainActivity.onLocationChanged: Welcome home "+distance);
                } else {
                    // tat den: turn off lamp
                    Log.d("Location demo", "MainActivity.onLocationChanged: Distance: " + distance
                            + " lat: " + latitude + " longtitude: " + longtitude);
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
