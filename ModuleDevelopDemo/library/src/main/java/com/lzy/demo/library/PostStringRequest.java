package com.lzy.demo.library;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhuofeng on 2015/4/8.
 * <p/>
 * Volley自定义POST请求
 */
public class PostStringRequest extends StringRequest {
    /**
     * 参数列表
     */
    private Map<String, String> params;

    /**
     * 构造函数，初始化请求对象
     */
    public PostStringRequest(String url, Map<String, String> params, Listener<String> listener,
                             ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(AppConfig.SOCKET_TIMEOUT, 0, 1.0f));
        this.params = params;
    }

    /**
     * 重写参数集合获取方法
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params == null ? super.getParams() : params;
    }

    /**
     * 重写参数编码方法
     */
    @Override
    protected String getParamsEncoding() {
        return AppConfig.ENCODEING;
    }

    /**
     * 重写请求头获取方法
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Charsert", getParamsEncoding());
        headers.put("Accept", "text/plain");
        headers.put("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Accept-Encoding", "gzip,deflate");
        return headers;
    }

}
