package xyz.purposeless.meniny.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import xyz.purposeless.meniny.csvParser;

public class DayChangeReceiver extends BroadcastReceiver {

    /**
     * Changes the name of current honoree
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        //TODO - Change name whenever day changes
        Log.d(this.toString(),"day Changed");
    }
}
