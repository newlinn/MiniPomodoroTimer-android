package com.c.minipomodorotimer;

import android.app.Application;
import android.content.Context;

/**
 * Created by C on 2015/4/6.
 */
public class MyApplication extends Application {
    private static Context context;

    public static Context getContext()
    {
      return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
