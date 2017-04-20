package com.lzy.demo.library;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by LZY on 2016/10/31.
 * ＊ Description ${TODO}
 */

public class RetrofitUtil {
    private Context mContext;
    private List<Call> list = new ArrayList<>();

    public RetrofitUtil(Context context) {
        mContext = context;
    }

    //设置retrofit的超时时间
    OkHttpClient client = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS).build();



    //get请求
    public void doGet(String url, Map<String, String> map, final CallRequestListener listener) {
        //这边的baseurl必需已／结尾，不然retrofit会抛错
        Retrofit build = new Retrofit.Builder().
                baseUrl(url + "/").addConverterFactory(StringConverterFactory.create()).client(client).build();
        InterfaceManager interfaceManageService = build.create(InterfaceManager.class);
        Call<String> stringCall = interfaceManageService.get(url, map);
        list.add(stringCall);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String message = response.message();
                // 如果说返回的message是ok的话就回调给页面
                if (TextUtils.equals("OK", response.message())) {
                    listener.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //这边返回的数据可以在包裹一层
                if (t instanceof TimeoutException) {
                    Toast.makeText(mContext, "请求超时，请稍后", Toast.LENGTH_SHORT).show();
                } else {
                    listener.error(t);
                }
            }
        });
    }

    //post请求
    public void doPost(String url, Map<String, String> map, final CallRequestListener listener){
        

    }

    //取消所有的网络访问请求
    public  void cancelAllRequest() {
        for (Call request : list) {
            if (!request.isCanceled()) {
                request.cancel();
            }
        }
    }


}
