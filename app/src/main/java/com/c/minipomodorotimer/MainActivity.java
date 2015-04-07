package com.c.minipomodorotimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    long exitTime = 0;

    private void exit()
    {
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
                if ((System.currentTimeMillis() - exitTime) > 2000) {
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
}
