package com.c.minipomodorotimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    DrawerLayout drawerLayout;
    Button btnOpenDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notification = AlarmNotification.get(MainActivity.this);
        tv = (TextView) findViewById(R.id.tvHello);

        workDownTimer = new WorkDownTimer(1000 * 200, 1000 * 2);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.circleProgressbar);
        circleProgressBar.setMaxProgress(1000 * 200);
        tvCount = (CountView) findViewById(R.id.tvCount);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnOpenDrawer = (Button) findViewById(R.id.btnOpenDrawer);
        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT))
                    drawerLayout.closeDrawer(Gravity.LEFT);
                else
                    drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    CountView tvCount;
    AlarmNotification notification;

    long exitTime = 0;
    static final int intervalBack = 2000;

    private void exit() {
        notification.cancelAll();
        finish();
        System.exit(0);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isWorking = false;
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
                if ((System.currentTimeMillis() - exitTime) > intervalBack) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    exit();
                }
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    int minute = 0;
    TextView tv;
    CircleProgressBar circleProgressBar;
    int progress = 0;
    WorkDownTimer workDownTimer;

    public void notification(View view) {
        workDownTimer.start();
    }

    private class WorkDownTimer extends CountDownTimer {
        public WorkDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            if (minute > 25)
                minute = 0;
            notification.showMinuteNotify(minute++);
            tv.setText(String.valueOf(minute));
            progress = progress + 1000 * 2;
            circleProgressBar.setProgress(progress);
            tvCount.showNumberWithAnimation(progress);
        }

        @Override
        public void onFinish() {

        }
    }

}
