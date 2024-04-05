package ro.pub.cs.systems.eim.colocviu1_2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class Colocviu1_2Service extends Service {
    private static final int BROADCAST_INTERVAL = 2000; // 2 second
    private boolean isServiceRunning = false;
    private int sumGeneral = 0;
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
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d("BROADCAST_ACTION", "sunt in serviciu");
        if (intent != null) {
            sumGeneral = intent.getIntExtra("SUM_GENERAL", 0);
            Log.d(":BROADCAST_ACTION", "SUM_GENERAL:" + sumGeneral);
        }

        if (isServiceRunning) {
            onDestroy();
        }

        startService();

        activityNotification();

        return START_REDELIVER_INTENT;
    }

    void startService() {
        isServiceRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isServiceRunning) {
                    sendBroadcastMessage();
                    SystemClock.sleep(BROADCAST_INTERVAL);
                }
            }
        }).start();
    }

    private void sendBroadcastMessage() {
        Intent intent = new Intent();

        intent.setAction("ACTION");
        intent.putExtra("ACTION_TIME", String.valueOf(new Date(System.currentTimeMillis())));
        intent.putExtra("ACTION_SUM", String.valueOf(sumGeneral));


        sendBroadcast(intent);
        Log.d("BROADCAST_ACTION", String.valueOf(new Date(System.currentTimeMillis())) + " " + String.valueOf(sumGeneral));
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