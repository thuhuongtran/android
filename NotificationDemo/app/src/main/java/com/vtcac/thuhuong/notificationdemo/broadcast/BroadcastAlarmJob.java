package com.vtcac.thuhuong.notificationdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastAlarmJob extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("alarm job", "BroadcaseAlarmJob.onReceive: ");
    }
}
