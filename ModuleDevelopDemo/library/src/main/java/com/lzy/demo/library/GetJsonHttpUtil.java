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
 * ＊ Description getjson，监听token过期，已弃用
 */
public class GetJsonHttpUtil {

    public static GetJsonHttpUtil fx() {
        return new GetJsonHttpUtil();
    }


    /**
     * 请求队列
     */
    private RequestQueue queue;


    public Request<JSONObject> add(RequestQueue queue,String url,
                                   VCJsonListener success, VCErroListenr error, Context context) {
        this.queue = queue;

        if (null == queue) {
            queue = Volley.newRequestQueue(context);
        }
        VCToken sux = new VCToken(queue,url, success, error,context);
        Request<JSONObject> request = queue.add(new GetJsonRequest(url, sux, error));
        request.setTag(context);
        return request;
    }


    public class VCToken implements Response.Listener<JSONObject> {
        String         url;

        VCJsonListener success;
        VCErroListenr  error;
        Context        context;
        RequestQueue queue;

        VCToken(RequestQueue queue,String url,
                VCJsonListener success, VCErroListenr error, Context context) {
            EventBus.getDefault().register(this);
            this.url = url;

            this.success = success;
            this.error = error;
            this.context = context;
            this.queue = queue;
        }



        @Override
        public void onResponse(JSONObject jsonObject) {

            if (JsonUtils.getJSONObjectKeyVal(jsonObject.toString(), "resCode").equals("99")
                    && jsonObject.toString().contains("访问令牌已过期") || (JsonUtils.getJSONObjectKeyVal(jsonObject.toString(), "resCode").equals("-1")
                    && jsonObject.toString().contains("访问令牌"))
                    ) {

            } else {
                this.success.onResponse(jsonObject);
            }

        }






    }


}
