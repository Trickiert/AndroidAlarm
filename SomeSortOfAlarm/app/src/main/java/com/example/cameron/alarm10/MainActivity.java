package com.example.cameron.alarm10;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar; //Java calander libaries

public class MainActivity extends Activity {

    AlarmManager alarmManager; //Alarm management class
    private PendingIntent pendingIntent; //Lets a 3rd party app deal with an intent send from this one
    private TimePicker alarmTimePicker; //To pick the time for the alarm to go off
    private static MainActivity inst; //Instance the activity.
    private TextView alarmTextView; //Text view for the alarm, in the case the message comes up once the alarm has been activated.

public static MainActivity instance()
{
    return inst;    //Return current instance

}

    @Override
    public void onStart() {  //On app start, do this.
        super.onStart();
        inst = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) { //On activity creation, do this.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Default generated code!

        ///////////////////NEW SHIT FOR THE ALARM ///////////////////////////////////////////////

        //Here the resources in the XML file are instantiated in the activity allowing us to actually use them.
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE); //Bind alarm manager to the built in Android OS Alarm service.
    }

    public void onToggleClicked(View view) //Control the button toggle functionality
    {
        if (((ToggleButton)view).isChecked()) { //If the toggle button is checked in current view...

            Log.d("Hello!","Alarm Is On"); //Log this
            Calendar calendar = Calendar.getInstance(); //Get the currently instanciated calender.
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour()); //Depreciated on Android 5 (Lollipop) and Above, but works for this project.
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute()); //Same as above, but for the current minute.

            Intent myIntent = new Intent(MainActivity.this, A_Receiver.class); //An intent to our second class that handles the receiver. TODO
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0); //Use of the pending intent.
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent); //Same as above but with the android alarm manager.
        }
        else { //Else the alarm is off...
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("GoodBye!", "Alarm Off");
        }
    }
    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText); //Pass a string to the alarm text. Might not be needed in this form?
    }
}
