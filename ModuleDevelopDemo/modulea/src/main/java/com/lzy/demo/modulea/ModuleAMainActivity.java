package com.lzy.demo.modulea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lzy.demo.library.CallRequestListener;
import com.lzy.demo.library.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

public class ModuleAMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modulea_activity_main);

        RetrofitUtil util= new RetrofitUtil(this);
        Map<String,String>  map= new HashMap<>();
        map.put("key","5437f942f2b037cf64deb8d501418bb7");
        util.doGet("http://op.juhe.cn/onebox/exchange/query", map, new CallRequestListener() {
            @Override
            public void success(String result) {
                Log.d("LZY", "success: "+result);
            }

            @Override
            public void error(Throwable error) {
                Log.d("LZY", "error: "+error.toString());
            }
        });

    }
}
