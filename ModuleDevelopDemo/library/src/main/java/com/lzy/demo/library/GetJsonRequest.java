package com.lzy.demo.library;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhuofeng on 2015/4/8.
 *
 * Volley自定义GET请求
 */
public class GetJsonRequest extends JsonObjectRequest {

    /** 构造函数，初始化请求对象 */
    public GetJsonRequest(String url, Listener<JSONObject> listener,
                          ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(AppConfig.SOCKET_TIMEOUT, 0, 1.0f));
    }

    /** 重写参数编码方法 */
    @Override
    protected String getParamsEncoding() {
        return AppConfig.ENCODEING;
    }

    /** 重写请求头获取方法 */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Charsert", getParamsEncoding());
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("Accept-Encoding", "gzip,deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        return headers;
    }

}
