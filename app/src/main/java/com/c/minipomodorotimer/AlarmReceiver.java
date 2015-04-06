package com.c.minipomodorotimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent srvIntent = new Intent(context, ForeService.class);
        context.startService(srvIntent);
    }
}
