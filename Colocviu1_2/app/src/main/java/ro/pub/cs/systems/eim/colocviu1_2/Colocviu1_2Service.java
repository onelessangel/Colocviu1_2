package ro.pub.cs.systems.eim.colocviu1_2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

public class Colocviu1_2Service extends Service {
    private static final int BROADCAST_INTERVAL = 2000; // 2 second
    private boolean isServiceRunning = false;
    public Colocviu1_2Service() {
    }

    private void activityNotification() {
        // creates notification channel - channel id, channel name, importance high
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        }

        // creates notification manager - based on notification channel
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }

        // creates notification - context, channel id
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(getApplicationContext(), Constants.CHANNEL_ID).build();
        }

        // starts foreground service - used in versions higher than Oreo
        startForeground(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceRunning = false;
    }
}