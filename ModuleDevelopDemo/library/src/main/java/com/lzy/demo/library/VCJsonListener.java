package com.lzy.demo.library;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONObject;


/**
 * 响应监听类，对正常返回进行后续处理（Listener<String>子类） 对返回信息进行预处理
 * Created by zhuofeng on 2015/4/8.
 */
public class VCJsonListener implements Response.Listener<JSONObject> {

    private RequestListener requestListener;
    private Context         context;

    public VCJsonListener(RequestListener requestListener, Context context) {
        this.requestListener = requestListener;
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject arg0) {

        if (HttpUtil.isOpenProgressbar) {
            VCProgressDialog.dismiss();
        }
        // 获得请求结果
        if (arg0 == null || "".equals(arg0.toString())) {
            this.requestListener.onError(HttpUtil.NETERROE_UNUNITED);
        } else if (JsonUtils.getJSONObjectKeyVal(arg0.toString(), "resCode").equals("0")){
            this.requestListener.onSuccess(JsonUtils.getJSONObjectKeyVal(arg0.toString(), "content"));
            CommonUtils.LOG_D(getClass(), "onSuccess = " + arg0.toString());
        }else{
            this.requestListener.onError(JsonUtils.getJSONObjectKeyVal(arg0.toString(), "resMsg"));
            CommonUtils.LOG_D(getClass(), "JSON error = " + arg0.toString());
            if (JsonUtils.getJSONObjectKeyVal(arg0.toString(), "resCode").equals("99") && arg0.toString().contains("访问令牌已过期")) {
                //访问令牌已过期，跳到登录页面
             //   context.startActivity(new Intent(context, LoginActivity.class));
                //getToken();
                //TooltipUtils.showToastS((Activity) context,"请重新进入本页面");
            }
        }
    }

}
