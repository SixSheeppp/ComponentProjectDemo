package com.lzy.demo.modulea;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lzy.demo.library.CallRequestListener;
import com.lzy.demo.library.RetrofitUtil;
import com.lzy.demo.library.base.BaseActivity;
import com.lzy.demo.modulea.databinding.ModuleaActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class ModuleAMainActivity extends BaseActivity {

    private RetrofitUtil mRetrofitUtil;
    private ModuleaActivityMainBinding mMainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.modulea_activity_main);
        mMainActivity = DataBindingUtil.setContentView(this, R.layout.modulea_activity_main);
        init();

    }

    @Override
    public void instantiation() {
        mRetrofitUtil = new RetrofitUtil(this);

    }

    @Override
    public void eventbind() {

    }

    @Override
    public void databind() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "5437f942f2b037cf64deb8d501418bb7");
        mRetrofitUtil.doGet("http://op.juhe.cn/onebox/exchange/query", map, new CallRequestListener() {
            @Override
            public void success(String result) {
                Log.d("LZY", "success: " + result);
                mMainActivity.activityMain.setVisibility(View.GONE);
            }

            @Override
            public void error(Throwable error) {
                Log.d("LZY", "error: " + error.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRetrofitUtil.cancelAllRequest();

    }
}
