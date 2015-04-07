package com.c.minipomodorotimer;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.reflect.Field;

/**
 * Created by C on 2015/4/6.
 */
public class AlarmNotification {

    private static AlarmNotification alarmNotification = null;

    public static AlarmNotification get(Context context){
        if (alarmNotification == null){
            alarmNotification = new AlarmNotification(context);
        }
        return alarmNotification;
    }

    protected AlarmNotification(Context context)
    {
        this.context = context;
        notificationMgr = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        showStartNotify();
    }

    Context context;
    NotificationManager notificationMgr;
    Notification notification;
    Bitmap appIcon;

    static int FLAG_NORMAL = 1001;

    public void cancelAll(){
        notificationMgr.cancelAll();
    }

    private void showStartNotify() {
        appIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        notification = new Notification.Builder(this.context)
                .setContentTitle("番茄时间")
                .setContentText("ContentText")
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("番茄时间")
                .setContentInfo("")
                .setLargeIcon(appIcon)
                .setSound(null)
                .setNumber(FLAG_NORMAL)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setVibrate(null)
                .setSound(null)
                .setDefaults(notification.FLAG_ONGOING_EVENT)
                .build();
        notificationMgr.notify(FLAG_NORMAL, notification);
    }

    //休息提示
    public void showBreakNotify()
    {

    }

    //运行提示
    public void showMinuteNotify(int minute) {
        notification = new Notification.Builder(this.context)
                .setContentTitle("番茄时间正在运行")
                .setContentText("ContentText")
                .setSmallIcon(getMinuteIcon(minute))
                .setTicker("番茄时间正在运行")
                .setContentInfo("剩余时间")
                .setLargeIcon(appIcon)
                .setSound(null)
                .setNumber(FLAG_NORMAL)
                .setAutoCancel(true)
                .setVibrate(null)
                .setSound(null)
                .setDefaults(Notification.DEFAULT_ALL | notification.FLAG_ONGOING_EVENT)
                .build();
        notificationMgr.notify(FLAG_NORMAL, notification);
    }

    private int getMinuteIcon(int minute) {
        String imageName = "m" + minute;
        try {
            Field field = Class.forName("com.c.minipomodorotimer.R$drawable").getField(imageName);
            return field.getInt(field);
        } catch (Exception ex) {

        }

        return R.drawable.ic_launcher;
    }

}
