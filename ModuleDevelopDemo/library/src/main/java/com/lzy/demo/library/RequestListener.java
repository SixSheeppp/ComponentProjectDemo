package com.lzy.demo.library;

/**
 * 响应监听类（负责Response返回后针对业务对象进行逻辑处理）
 * Created by zhuofeng on 2015/4/8.
 */
public interface RequestListener {
    /** 请求成功后的回调函数 */
    void onSuccess(String result);
    /** 请求出错后的回调函数 */
    void onError(String printMe);
}
