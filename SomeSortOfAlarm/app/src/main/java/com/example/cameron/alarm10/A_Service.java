package com.example.cameron.alarm10;

/**
 * Created by Cameron on 04/02/2017.
 */

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

abstract public class A_Service extends IntentService {

    private NotificationManager alarmNotificationManager;

    public A_Service() {
        super("AlarmService Function");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification("GET UP, YOU FUCK");
    }

    private void sendNotification(String msg) {
        Log.d("AlarmService", "sending notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_zen) //Use a custom image for the notification, in this case Zenyatta!
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }
}

