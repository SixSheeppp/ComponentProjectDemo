package com.lzy.demo.kotlinmodule;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by LZY on 2017/8/17.
 * ＊ Description ${TODO}
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pariToInt(null);
    }


    private Integer pariToInt(String num){
        return Integer.parseInt(num);
    }
}
