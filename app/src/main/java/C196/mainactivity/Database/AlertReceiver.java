package C196.mainactivity.Database;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import C196.mainactivity.R;

public class AlertReceiver extends BroadcastReceiver {
    String channelID = "test";
    static int notificationID;


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void createNotificationChannel(Context context, Intent intent){
        CharSequence channelNameCharacters = context.getResources().getString(R.string.channelName);
        String channelDescriptionString = context.getResources().getString(R.string.channelDescription);

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(channelID, channelNameCharacters, importance);

        notificationChannel.setDescription(channelDescriptionString);

    }
}