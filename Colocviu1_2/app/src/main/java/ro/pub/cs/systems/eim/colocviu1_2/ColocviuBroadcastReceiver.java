package ro.pub.cs.systems.eim.colocviu1_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ColocviuBroadcastReceiver extends BroadcastReceiver {
    public ColocviuBroadcastReceiver() {

    }    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String time = null;
        String number = null;

        if (action != null) {
            if (action.equals("ACTION")) {
                time = intent.getStringExtra("ACTION_TIME");
                number = intent.getStringExtra("ACTION_SUM");
            }

        }

        Log.d("DATA", time + " " + number);
    }
}
