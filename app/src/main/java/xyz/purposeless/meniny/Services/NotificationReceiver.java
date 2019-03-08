package xyz.purposeless.meniny.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import xyz.purposeless.meniny.MainActivity;
import xyz.purposeless.meniny.R;
import xyz.purposeless.meniny.csvParser;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String DEBUG_TAG = "NotificationReceiver";

    private int MID=0;
    private csvParser cp;

    /**
     * Will push notification whenever receives broadcast.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(DEBUG_TAG, "onReceive()");
        cp = csvParser.getParser();

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, MID,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //TODO - Add condition if the time has already past today
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(context,"default")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon_cal)
                .setContentTitle("Meniny")
                .setContentText("Dnes má meniny: " + cp.parseName())
                .setSound(alarmSound)
                .setAutoCancel(true).setWhen(when)
                .setVibrate(new long[]{1000, 1000, 1000});

        notificationManager.notify(MID, mNotifyBuilder.build());
        MID++;

    }
}
