package xyz.purposeless.meniny.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

    /**
     * Starts NotificationService when device has booted up
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,NotificationService.class);
        context.startService(i);
    }
}
