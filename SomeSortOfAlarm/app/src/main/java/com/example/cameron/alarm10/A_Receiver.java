package com.example.cameron.alarm10;

/**
 * Created by Cameron on 06/06/2016.
 */

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver; //Support class for the wakelock.

//NB: A wakelock is basically a permission for an app to wake up the device screen.

abstract public class A_Receiver extends WakefulBroadcastReceiver
{

    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with message
        MainActivity inst = MainActivity.instance(); //using the instance we made in the main activity
        inst.setAlarmText("WAKE UP, YOU LAZY FUCK");

        //raise alarm in loop continuously.
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play(); //Play whatever ringtone your phone has been set.

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                A_Service.class.getName()); //We need to manage the alarm service also. See the class for info. This allows for our notifcation messege to work correctly.
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK); //Condition code, OK = Everything exited as intended.

    }
}
