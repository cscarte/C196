package C196.mainactivity.Database;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import C196.mainactivity.R;

public class AlertReceiver extends BroadcastReceiver {
    String channelID = "test";
    static int notificationID;


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(context, intent.getStringExtra("startAlert"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context, channelID);

        Notification startNotification = new NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.ic_baseline_alarm_24)
                .setContentText(intent.getStringExtra("startAlert"))
                .setContentTitle("Notification Test").build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, startNotification);
    }

    private void createNotificationChannel(Context context, String channelID){
        CharSequence channelNameCharacters = context.getResources().getString(R.string.channelName);
        String channelDescriptionString = context.getResources().getString(R.string.channelDescription);

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(channelID, channelNameCharacters, importance);

        notificationChannel.setDescription(channelDescriptionString);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }
}