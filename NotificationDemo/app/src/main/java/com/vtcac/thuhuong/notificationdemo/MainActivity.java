package com.vtcac.thuhuong.notificationdemo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // btn alarm service
        findViewById(R.id.btnAlarmService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                Intent intentAction = new Intent();
                intentAction.setAction("alarm_job");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0,
                        intentAction, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManage = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManage.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        1000, pendingIntent);
            }
        });
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // btnNotification Action
        findViewById(R.id.btnAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete email - action delete
                Intent intentDelete = new Intent();
                intentDelete.setAction("delete_email");
                PendingIntent pendingIntentDelete = PendingIntent.getBroadcast(getBaseContext(),
                        0, intentDelete, PendingIntent.FLAG_UPDATE_CURRENT);
                Intent replyIntent = new Intent(getBaseContext(), ReplyActivity.class);

                //action reply
                replyIntent.putExtra("reply_to", "thuhuong@mail.com");

                PendingIntent pendingIntentReply = PendingIntent.getActivity(getBaseContext(), 0,
                        replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle("New message")
                        .setContentText("You got new message from Khoaha pending Detail")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .addAction(R.mipmap.ic_launcher, "Reply",pendingIntentReply)
                        .addAction(R.mipmap.ic_launcher, "Delete",pendingIntentDelete)
                        .build();
                notificationManager.notify(11,notification);
            }
        });
        //open app with stack
        findViewById(R.id.btnOpenAppWithStack).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
                // click notification sau đó vào trang activity đã đc fix sẵn, (code bên dưới dùng để quay lại trang home khi click vào nút back lại)
                TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(getBaseContext());
                taskStackBuilder.addParentStack(DetailsActivity.class); // add activity cha cua DetailActivity vao trc
                taskStackBuilder.addNextIntent(intent); // sau do add tiep DetailActivity vao
                PendingIntent pendingIntentDetails = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new NotificationCompat.Builder(getBaseContext())
                         .setContentTitle("New message")
                        .setContentText("You got new message from Khoaha pending Detail")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntentDetails)
                        .setAutoCancel(true) // auto close notification - tu dong notification khi click vao
                        .build();
                notificationManager.notify(10, notification);
            }
        });

        //btn OpenAppFromNotification - open app (just that activity) from notification
        // click notification sau đó vào trang activity đã đc fix sẵn, (code bên dưới ấn nút back lại, nó tự thoát app, không quay lại trang home)
        findViewById(R.id.btnOpenApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to open app from notification
                Intent mainIntent = new Intent(getBaseContext(), DetailsActivity.class);
                mainIntent.putExtra("message", "You got a new message from VTC Pending Intent");
                PendingIntent pendingIntentMain = PendingIntent
                        .getActivity(getBaseContext(), 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle("New message")
                        .setContentText("You got new message from Khoaha")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntentMain)
                        .build();
                notificationManager.notify(9, notification);
            }
        });
        // btnPrivate - show content notification in private
        findViewById(R.id.btnPrivate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle("Private Notification")
                        .setContentText("You got a message from KHOAHA")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setVisibility(Notification.VISIBILITY_PRIVATE)
                        .build();
                notificationManager.notify(3, notification);
            }
        });
        // btnPublic - show content notification in public
        findViewById(R.id.btnPublic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle("Public Notification")
                        .setContentText("You got a message from KHOAHA")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setVisibility(Notification.VISIBILITY_PUBLIC)
                        .build();
                notificationManager.notify(5, notification);
            }
        });
        // btnSecret - do not show content notification
        findViewById(R.id.btnSecret).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification notification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle("Secret Notification")
                        .setContentText("You got a message from VTC")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setVisibility(Notification.VISIBILITY_SECRET)
                        .build();
                notificationManager.notify(6, notification);
            }
        });
        // btnSimpleNotification
        findViewById(R.id.btnSimpleNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Notification publicNotification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle("New message")
                        .setContentText("From sms")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();
                Notification notification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle("New message")
                        .setContentText("You got a message from KHOAHA")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setVisibility(Notification.VISIBILITY_PRIVATE)
                        .setPublicVersion(publicNotification)
                        .build();*/
                Notification notification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle("New message")
                        .setContentText("You got a message from KHOAHA")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();
                notificationManager.notify(1, notification);
            }
        });

        // btnCounterNotification
        findViewById(R.id.btnCounterNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                Notification notification = new NotificationCompat.Builder(getBaseContext())
                        .setContentTitle(count + " new messages")
                        .setContentText("You got new messages")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setNumber(count)
                        .build();
                notificationManager.notify(2, notification);
            }
        });
    }
}
