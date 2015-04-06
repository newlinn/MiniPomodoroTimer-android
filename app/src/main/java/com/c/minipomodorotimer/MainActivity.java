package com.c.minipomodorotimer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //alarmNotification = AlarmNotification.get();
        //alarmNotification.showMinuteNotify(0);
    }

    AlarmNotification alarmNotification = null;

    public void startSrv(View view) {
        //Intent intent = new Intent(MainActivity.this, ForeService.class);
        //startService(intent);

    }

    private void foo() {
        AlarmManager alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        long trigger = SystemClock.elapsedRealtime() + 60 * 1000;
        Intent alarmRcv = new Intent(this, AlarmReceiver.class);
        PendingIntent piAlarmBroad = PendingIntent.getBroadcast(this, 0, alarmRcv, 0);
        alarmMgr.setExact(AlarmManager.ELAPSED_REALTIME, trigger, piAlarmBroad);
    }


    long exitTime = 0;

    private void exit()
    {
        finish();
        System.exit(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isWorking = true;
        if (isWorking) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("提示")
                    .setMessage("番茄时间正在进行中。是否退出？")
                    .setPositiveButton("确认",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    exit();
                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).create();
            alert.show();
        } else {
            if (KeyEvent.KEYCODE_BACK == keyCode
                    && KeyEvent.ACTION_DOWN == event.getAction()) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(MyApplication.getContext(), "再按一次退出程序！", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    exit();
                }
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
