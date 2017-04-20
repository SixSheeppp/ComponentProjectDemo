package com.lzy.demo.library;

import com.android.volley.Response.ErrorListener;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;


/**
 * 响应监听类，对错误返回进行处理（ErrorListener子类）
 * Created by zhuofeng on 2015/4/8.
 */
public class VCErroListenr implements ErrorListener {

    private RequestListener requestListener;

    public VCErroListenr(RequestListener requestListener) {
        this.requestListener = requestListener;
    }

    @Override
    public void onErrorResponse(VolleyError arg0) {
        CommonUtils.LOG_D(getClass(), "arg0=" + arg0.toString());
        if (HttpUtil.isOpenProgressbar) {
            VCProgressDialog.dismiss();
        }
        this.requestListener.onError( arg0 instanceof TimeoutError ? HttpUtil.NETERROE_TIMEOUT :  HttpUtil.NETERROE_UNUNITED);
        if (AppConfig.DEBUG && arg0 != null && arg0.networkResponse != null && arg0.networkResponse.data != null) {
            byte[] htmlBodyBytes = arg0.networkResponse.data;
            if (htmlBodyBytes != null) {
                CommonUtils.LOG_D(getClass(), "VolleyError=" + new String(htmlBodyBytes));
            }
        }

    }
}
