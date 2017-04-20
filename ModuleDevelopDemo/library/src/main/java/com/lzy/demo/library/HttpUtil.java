package com.lzy.demo.library;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhuofeng on 2015/4/8.
 * <p>
 * 网络通讯类
 */
public class HttpUtil {

    /**
     * 访问地址无响应（显示中文提示）
     */
    public final static String NETERROE_UNUNITED = "连接服务器失败";
    /**
     * 手机网络未开启（显示中文提示）
     */
    public final static String NETERROE_CHECKSET = "请检查网络设置";
    /**
     * 连接超时（显示中文提示）
     */
    public final static String NETERROE_TIMEOUT  = "已超时，请重新再试";
    /**
     * 请求队列
     */
    private RequestQueue queue;
    /**
     * 上下文对象
     */
    private Context      context;
    /**
     * 是否显示进度条
     */
    public static boolean isOpenProgressbar = true;
    /**
     * 进度条是否可以按回退键退出
     */
    public        boolean isCancelable      = true;

    /**
     * 构造函数
     */

    public HttpUtil(Context context) {
        queue = Volley.newRequestQueue(context);
        this.context = context;
        isOpenProgressbar = true;
        isCancelable = true;
    }

    /**
     * 返回带有公共参数的请求参数
     */
    private Map<String, String> getHashMapWithBaseParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("os", "android");
        return params;
    }

    /**
     * 发起一个带tag的postString请求
     *
     * @param url
     * @param params
     * @param requestListener
     * @return
     */
    public Request<String> doPostByString(String url, Map<String, String> params,
                                          RequestListener requestListener) {
        // 网络检查
        if (!checkNetState(context)) {
            Toast.makeText(context, NETERROE_CHECKSET, Toast.LENGTH_SHORT)
                    .show();
            return null;
        }
        // 是否显示进度条
        if (isOpenProgressbar) {
            showProgressDialog();
        }
        CommonUtils.LOG_D(getClass(), "params = " + params);
        // 加入请求队列
        if (null == queue) {
            queue = Volley.newRequestQueue(context);
        }
        Request<String> request = queue.add(new PostStringRequest(url, params,
                new VCStringListener(requestListener),
                new VCErroListenr(requestListener)));

        // 为请求添加context标记
        request.setTag(context);
        return request;
    }

    private void showProgressDialog() {
        ProgressDialog progressDialog = VCProgressDialog.show(context, null);
        progressDialog.setCancelable(isCancelable);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancelAllRequestQueue();
            }
        });
    }

    /**
     * 发起一个带tag的postJson请求
     *
     * @param url
     * @param params
     * @param requestListener
     * @return
     */
    public Request<JSONObject> doPostByJson(String url, Map<String, Object> params,
                                            RequestListener requestListener) {
        JSONObject jsonObject = new JSONObject(params);
        return doPostByJson(url, jsonObject, requestListener);
    }


    /**
     * 发起一个带tag的postJson请求
     *
     * @param url
     * @param jsonObject
     * @param requestListener
     * @return
     */
    public Request<JSONObject> doPostByJson(String url, JSONObject jsonObject,
                                            RequestListener requestListener) {

        // 网络检查
        if (!checkNetState(context)) {
            Toast.makeText(context, NETERROE_CHECKSET, Toast.LENGTH_SHORT)
                    .show();
            return null;
        }
        // 是否显示进度条
        if (isOpenProgressbar) {
            showProgressDialog();
        }

        //        CommonUtils.LOG_D(getClass(), "params = " + jsonObject.toString());
        if (null == queue) {
            queue = Volley.newRequestQueue(context);
        }
        //KKlife回调监听，现在反正到正常
        Request<JSONObject> request = PostJsonHttpUtil.fx().add(queue, url, jsonObject,
                new VCJsonListener(requestListener, context),
                new VCErroListenr(requestListener), context);

              /*  Request<JSONObject> request = queue.add(new PostJsonRequest(url, jsonObject,
                        new VCJsonListener(requestListener,context),
                        new VCErroListenr(requestListener)));*/
        // 为请求添加context标记，切换回KKLife时，记得注释tag
        // request.setTag(context);


        return request;
    }


    /**
     * 发起一个带tag的get请求
     *
     * @param url
     * @param requestListener
     * @return
     */
    public Request<String> doGetByString(String url, RequestListener requestListener) {
        // 网络检查
        if (!checkNetState(context)) {
            Toast.makeText(context, NETERROE_CHECKSET, Toast.LENGTH_SHORT)
                    .show();
            return null;
        }
        // 是否显示进度条
        if (isOpenProgressbar) {
            VCProgressDialog.show(context, null);
        }

        // 加入请求队列
        Request<String> request = queue.add(new GetStringRequest(url,
                new VCStringListener(requestListener),
                new VCErroListenr(requestListener)));

        // 为请求添加context标记
        request.setTag(context);
        return request;
    }

    /**
     * 发起一个带tag的get请求
     *
     * @param url
     * @param requestListener
     * @return
     */
    public Request<JSONObject> doGetByJson(String url, RequestListener requestListener) {
        // 网络检查
        if (!checkNetState(context)) {
            Toast.makeText(context, NETERROE_CHECKSET, Toast.LENGTH_SHORT)
                    .show();
            return null;
        }
        // 是否显示进度条
        if (isOpenProgressbar) {
            showProgressDialog();
        }
        //queue.cancelAll(context);
        // 加入请求队列
        if (null == queue) {
            queue = Volley.newRequestQueue(context);
        }
       /* Request<JSONObject> request = queue.add(new GetJsonRequest(url,
                new VCJsonListener(requestListener,context),
                new VCErroListenr(requestListener)));

        // 为请求添加context标记
        request.setTag(context);*/
        Request<JSONObject> request = GetJsonHttpUtil.fx().add(queue, url,
                new VCJsonListener(requestListener, context),
                new VCErroListenr(requestListener), context);
        return request;
    }

    /**
     * 清除当前Activity的请求队列
     */
    public void cancelAllRequestQueue() {
        if (queue != null && context != null) {
            queue.cancelAll(context);
            queue.stop();
            queue = null;
        }
    }

    /**
     * 设置ProgressDialog的显示模式
     * true 为自动模式（ProgressDialog的显示消失和请求的开始结束关联）默认
     * false 为手动模式（ProgressDialog的显示消失由开发者自己控制）
     *
     * @param isOpen
     */
    public void setIsOpenProgressbar(boolean isOpen) {
        isOpenProgressbar = isOpen;
    }

    public boolean getCancelable() {
        return isCancelable;
    }

    public void setCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }

    /**
     * 检测网络状态（true、可用 false、不可用）
     */
    public static boolean checkNetState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }

        return false;
    }

    public String getMainUrl(String str) {
//        StringBuffer stringBuffer = new StringBuffer(ConfigNet.MAIN_HOST);
//        stringBuffer.append(str);
//        return stringBuffer.toString();
        return  "";
    }



  /*      //卡卡贷的mainhost
    public String getKKCreditMainHost(String str){

        StringBuffer stringBuffer = new StringBuffer("http://www.kkcredit.cn/ccl/data/ws/rest/app/");
        stringBuffer.append(str);
        return stringBuffer.toString();

    }*/

}
