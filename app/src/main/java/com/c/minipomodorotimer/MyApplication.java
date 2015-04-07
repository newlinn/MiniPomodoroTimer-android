package com.c.minipomodorotimer;

import android.app.Application;
import android.content.Context;

/**
 * Created by C on 2015/4/6.
 */
public class MyApplication extends Application {

    public static Context getContext()
    {
      return context;
    }

    private static Context context;

    public static Context getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(Context mainActivity) {
        MyApplication.mainActivity = mainActivity;
    }

    private static Context mainActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
