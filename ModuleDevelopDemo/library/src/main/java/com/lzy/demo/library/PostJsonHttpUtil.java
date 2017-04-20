package com.lzy.demo.library;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * Created by LZY on 16/7/28.
 * ＊ Description 监听token过期的回调，已弃用
 */
public class PostJsonHttpUtil {


    public static PostJsonHttpUtil fx() {

        return new PostJsonHttpUtil();
    }


    /**
     * 请求队列
     */
    private RequestQueue queue;


    public Request<JSONObject> add(RequestQueue queue,String url, JSONObject jsonObject,
                                   VCJsonListener success, VCErroListenr error, Context context) {
        this.queue = queue;

        if (null == queue) {
            queue = Volley.newRequestQueue(context);
        }
        VCToken sux = new VCToken(queue,url, jsonObject, success, error,context);
        Request<JSONObject> request = queue.add(new PostJsonRequest(url, jsonObject, sux, error));
        request.setTag(context);
        return request;
    }


    public class VCToken implements Response.Listener<JSONObject> {
        String         url;
        JSONObject     jsonObject;
        VCJsonListener success;
        VCErroListenr  error;
        Context        context;
        RequestQueue queue;

        VCToken(RequestQueue queue,String url, JSONObject jsonObject,
                VCJsonListener success, VCErroListenr error, Context context) {
            EventBus.getDefault().register(this);
            this.url = url;
            this.jsonObject = jsonObject;
            this.success = success;
            this.error = error;
            this.context = context;
            this.queue = queue;
        }

        @Override
        public void onResponse(JSONObject jsonObject) {
            if (JsonUtils.getJSONObjectKeyVal(jsonObject.toString(), "resCode").equals("-1")
                    && jsonObject.toString().contains("用户信息不存在")
                    ) {
                VCProgressDialog.dismiss();

            } else {
                this.success.onResponse(jsonObject);
            }
        }









    }






}
