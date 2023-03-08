/**
package C196.mainactivity.UI;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import C196.mainactivity.R;

public class Notifications extends BroadcastReceiver {
    public static final String courseAlarmsChannel = "Course Alarms Channel";
    public static final String assessmentAlarmsChannel = "Assessment Alarms Channel";
    public static final String alarms = "alarms";
    public static final String nextAlarms = "nextAlarmID";
    private static final String channel = "alarmChannel";

    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("Channel ID = " + channel), Toast.LENGTH_LONG).show();

        String destination = intent.getStringExtra("destination");

        int id = intent.getIntExtra("id", 0);
        String alarmTitle = intent.getStringExtra("title");
        String alarmText = intent.getStringExtra("text");
        int nextAlarmID = intent.getIntExtra("nextAlarmID", getAndIncrementNextAlarmID(context));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel).setSmallIcon(R.drawable.ic_baseline_alarm_24).setContentTitle(alarmTitle).setContentText(alarmText);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(nextAlarmID, builder.build());
        }

    private static int getAndIncrementNextAlarmID(Context context){
        int nextAlarmID = getNextAlarmID(context);
        incrementNextAlarmID(context);
        return nextAlarmID;
    }

    private static int getNextAlarmID(Context context){
        SharedPreferences alarmPreferences;
        alarmPreferences = context.getSharedPreferences(alarms, Context.MODE_PRIVATE);
        return alarmPreferences.getInt(nextAlarms,1);
    }

    private static void incrementNextAlarmID(Context context){
        SharedPreferences alarmPreferences;
        alarmPreferences = context.getSharedPreferences(alarms, Context.MODE_PRIVATE);
        int nextAlarmID = alarmPreferences.getInt(nextAlarms, 1);
        SharedPreferences.Editor alarmEditor = alarmPreferences.edit();
        alarmEditor.putInt(nextAlarms, nextAlarmID + 1);
        alarmEditor.commit();
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    public static boolean scheduleAlarm(Context context, int id, long time, String title, String text, String destinationString, String alarmFile){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int nextAlarmID = getNextAlarmID(context);
        Intent intentAlarm = new Intent(context, Notifications.class);
        intentAlarm.putExtra("id", id);
        intentAlarm.putExtra("title", title);
        intentAlarm.putExtra("text", text);
        intentAlarm.putExtra("destination", destinationString);
        intentAlarm.putExtra("nextAlarmID", nextAlarmID);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, nextAlarmID, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        SharedPreferences sharedPreferences = context.getSharedPreferences(alarmFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Integer.toString(id), nextAlarmID);
        editor.commit();

        incrementNextAlarmID(context);
        return true;
    }

    /**
    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel courseNotificationChannel = new NotificationChannel(
                    courseAlarmsChannel,
                    "Course Alarms Channel",
                    NotificationManager.IMPORTANCE_HIGH
                    );
            courseNotificationChannel.setDescription("This is the course notification channel");

            NotificationChannel termNotificationChannel = new NotificationChannel(
                    termAlarmsChannel,
                    "Term Alarms Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            termNotificationChannel.setDescription("This is the term notification channel");

            NotificationChannel assessmentNotificationChannel = new NotificationChannel(
                    assessmentAlarmsChannel,
                    "Assessment Alarms Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            assessmentNotificationChannel.setDescription("This is the assessment notification channel");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(assessmentNotificationChannel);
            notificationManager.createNotificationChannel(courseNotificationChannel);
            notificationManager.createNotificationChannel(termNotificationChannel);
        }


    public static boolean scheduleCourseAlarm(Context context, int id, long time, String title, String text){
        return scheduleAlarm(context, id, time, title, text, "course", courseAlarmsChannel);
    }

    public static boolean scheduleAssessmentAlarm(Context context, int id, long time, String title, String text){
        return scheduleAlarm(context, id, time, title, text, "assessment", assessmentAlarmsChannel);
    }


}*/
