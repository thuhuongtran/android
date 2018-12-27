package com.vtcac.thuhuong.mytraveldemo3_2.base;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }
}
