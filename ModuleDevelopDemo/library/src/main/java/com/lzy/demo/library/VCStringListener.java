package com.lzy.demo.library;

import com.android.volley.Response;


/**
 * 响应监听类，对正常返回进行后续处理（Listener<String>子类） 对返回信息进行预处理
 * Created by zhuofeng on 2015/4/8.
 */
public class VCStringListener implements Response.Listener<String> {

    private RequestListener requestListener;

    public VCStringListener(RequestListener requestListener) {
        this.requestListener = requestListener;
    }

    @Override
    public void onResponse(String arg0) {
        if (HttpUtil.isOpenProgressbar){
            VCProgressDialog.dismiss();
        }
        // 判断编码格式
//        try {
//            arg0 = new String(arg0.getBytes("ISO-8859-1"), "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        // 获得请求结果
//        JResponse jResponse = JsonUtils.json2Object(arg0, JResponse.class);
//        if (jResponse.getResCode().equals("99")) {
//            // 未登录或者登录失效
//        } else if (jResponse.getResCode().equals("-1")) {
//            this.requestListener.onError(HttpUtil.NETERROE_UNUNITED);
//        } else {
//            String result = JsonUtils.getJSONObjectKeyVal(arg0, "content");
//            this.requestListener.onSuccess(result);
//        }

        this.requestListener.onSuccess(arg0);

    }
}
