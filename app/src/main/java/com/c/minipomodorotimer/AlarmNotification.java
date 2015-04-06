package com.c.minipomodorotimer;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import java.lang.reflect.Field;

/**
 * Created by C on 2015/4/6.
 */
public class AlarmNotification {

    private static AlarmNotification alarmNotification = null;

    public static AlarmNotification get(){
        if (alarmNotification == null){
            alarmNotification = new AlarmNotification(MyApplication.getContext());
        }
        return alarmNotification;
    }

    protected AlarmNotification(Context context)
    {
        this.context = context;
        notificationMgr = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        appIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        notification = new NotificationCompat.Builder(this.context)
                .setContentTitle("番茄计时器")
                .setContentText("ContentText")
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("正在运行")
                .setContentInfo("")
                .setLargeIcon(appIcon)
                .setSound(null)
                .setNumber(FLAG_NORMAL)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notificationMgr.notify(FLAG_NORMAL, notification);
    }

    Context context;
    NotificationManager notificationMgr;
    Notification notification;
    Bitmap appIcon;

    static int FLAG_NORMAL = 1001;

    public void cancelAll(){
        notificationMgr.cancelAll();
    }

    public void showStartNotify()
    {

    }

    public void showEndNotify()
    {

    }

    public void showMinuteNotify(int minute)
    {
        notification = new NotificationCompat.Builder(this.context)
                .setContentTitle("番茄计时器")
                .setContentText("ContentText")
                .setSmallIcon(getMinuteIcon(minute))
                .setTicker("剩余时间")
                .setContentInfo("剩余时间")
                .setLargeIcon(appIcon)
                .setSound(null)
                .setNumber(FLAG_NORMAL)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
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
