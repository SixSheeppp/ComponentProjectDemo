package com.lzy.demo.modulea;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.lzy.demo.library.CallRequestListener;
import com.lzy.demo.library.RetrofitUtil;
import com.lzy.demo.library.base.BaseActivity;
import com.lzy.demo.modulea.databinding.ModuleaActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class ModuleAMainActivity extends BaseActivity {
    private String RSA2_PRIVATE ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZfO9Xg9bsl84pyplE+vPxSlQ5PsI8+ofT2MGlFT2W1ZgBvXrawlFAaI2yKhJ0AUnHmD5vHGS0U2mTHfSHg8Ex+v5M4GOJD93VFm/Y4xId0d5BeMx3WuaB7/odAib11TSIGt/q7Br+Nc0Y4xMlb6X8O/bVZcFAgqkruAzEBONzALB9DbA+JFI/DycfWwMb4u4dd+Erm2UqXi8gCois//Zm9wmWNBXBBCzJaTTj+o57J7qxebpaqsuYfw991mNIodWkX0euYuTojyZy1y+bzKAzXk8bAWat/wlvOVJCnrTB1SIvyoPtmS7aqZaVA/1qSsWSz7rO6n8SCCcOgc4DzogfAgMBAAECggEAba2wHfpQ54nmwUhL09X74U06+BkXlZl02/YFEiEmfJUu5PdwRcgbZYoQzuJvg0O0ZBMvchh5Ab6lL4J1TSziLgPsGiUyGywiWXWKARLpzDM+vcxDV3q2Qt2n/XifPIXY3vVNUsLa2xK493K0KMkqdB6pWH445Q85Mct4G85edbtB3i6Hgqt4KjAo9D1vKGCzq64UCWJh/ovDItdUjRYGE0wtJf6Ka9ddnremNcIV9OAEic+GB6CTx02i4liuFvb4F84AV0/H6VVeWZr/S/T/J87ipEQWbvFPFfNSGTBzwDQJUVphVSqxNa0GjWO/bSVqiYb5gF7P1T1gPkBuhmxXUQKBgQD8EvYpp5hmc8/aQ+gPJvsEFSpDE2ghQ8MggWHqXVC2itA6iGhi7pKr0qzIAcU/SpxIML9xoWXUr6Pz3wh+dxGu8Hu+nUKw3VpYBnss6jwZ9er/wJfNqmhVPvPOjvv/XGYMeAiRf3FVh93pLh4g9pWCGdwZZbr8v8DJq/Rkd+LTpwKBgQCb4OdAySOIzFKHzMfHhVNA4EYNs2hEGRWYWXj4dkC3duZ+UDLTmjM4yL7FVZIl2jlSg5d9q8qOpJU8CVvmNYL4oe+5juXFYrCmnHT59V0Ol1Ru1Ago3SZg+J2AnabkCFMl+njUS9CwhnJrb5O2TVb9mxvbqkSBMIcVsFjw4AoWyQKBgEsCoXn3w2QJZnq+5llL8jpeyfq31a7AaVpM5gRFzHTljd09JkL6V28F/CBwVm/lFehtiSqZKqJFufYiEnb/m6WBLnGkfmn/FJlJJHJTKZtYEJwWhZXP9cgYOZjn95kft8ZSGcqiL+mRyrlO3exVNFxIsbRlD2WS8h2RK+KRwk/VAoGBAI8n8bnR/8x2uaT2MNPQYjFGx491oOBJmUedhDfLgov7qpt+0+75wuj3lBxvBhW1YMejCtHjn9d414WXosq9glcNgbgMwICSrmGVdujIqnnoCSbvm77Lu597NEsWflsWOia67ZUrO7GjCBobErFuPeITPXZz3Q44nxcQKd6uIxXhAoGAWfgLK8yeSKCa9RkKVQdzYwIsGW6O29mOxFWvDJHGn5e2xTi91gSMRzRI3w0rfqyAONIJTnrw9lWG/X1xbbXV/G2Fiqs5oc1hS1AOEYLaNr2HWYWBeWf9zC9CV10VX8XucfQyxfC8nhziyIKWsXS44XlS7oouDke1sKOQgEj4pLQ=";

    private RetrofitUtil mRetrofitUtil;
    private ModuleaActivityMainBinding mMainActivity;
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            Log.d("LZY", "handleMessage: "+resultInfo);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//                ViewHelper.setAlpha(mMainActivity.activityMain,0);
                getAlipay();
            }

            @Override
            public void error(Throwable error) {
                Log.d("LZY", "error: " + error.toString());
            }
        });
      
    }

    private void getAlipay() {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap("2016080200148589", rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey =  RSA2_PRIVATE ;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;


        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ModuleAMainActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = 200;
                msg.obj = result;
                mhandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRetrofitUtil.cancelAllRequest();

    }
}
