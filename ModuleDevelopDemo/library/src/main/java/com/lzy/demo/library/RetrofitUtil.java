package com.lzy.demo.library;

import android.content.Context;
import android.util.Log;
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
import retrofit2.converter.gson.GsonConverterFactory;

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


    public void doGet(String url, Map<String,String> map, final CallRequestListener listener){
        Retrofit build = new Retrofit.Builder().
                baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        InterfaceManager interfaceManageService = build.create(InterfaceManager.class);

        Log.d("LZY", "doGet: "+url);
        Call<String> stringCall = interfaceManageService.get(url+"／", map);

        list.add(stringCall);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                listener.success(response.message());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (t instanceof TimeoutException){
                    Toast.makeText(mContext, "请求超时，请稍后", Toast.LENGTH_SHORT).show();
                }else{
                    listener.error(t);
                }
            }
        });


    }


    //取消所有的网络访问请求
    private void cancelAllRequest(){
        for (Call request:list){
            if (!request.isCanceled()){
                request.cancel();
            }
        }



    }




}
