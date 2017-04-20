package com.lzy.demo.library;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by LZY on 2016/10/31.
 * ＊ Description retiofit的接口类
 */

public interface InterfaceManager {


    @GET()
    Call<String> get(@Url String url, @QueryMap Map<String, String> params);
    @FormUrlEncoded

    @POST()
    Call<String> post(@Url String url, @FieldMap Map<String, String> params, @Header("Cache-Time") String time);


}
