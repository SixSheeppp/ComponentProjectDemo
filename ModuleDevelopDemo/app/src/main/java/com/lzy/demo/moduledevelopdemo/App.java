package com.lzy.demo.moduledevelopdemo;

import android.app.Application;
import android.util.Log;

/**
 * Created by LZY on 2017/4/28.
 * ＊ Description ${TODO}
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("LZY", "onCreate: "+AppConfig.DEBUG);
    }
}
