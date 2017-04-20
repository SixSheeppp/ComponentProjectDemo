package com.lzy.demo.library;

/**
 * Created by LZY on 2016/10/31.
 * ＊ Description ${TODO}
 */

public interface CallRequestListener<T> {
    void success(String result);


    void error(Throwable error);

}
