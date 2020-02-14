package com.hrw.njqwtest.base.net;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 16:11
 * @desc:
 */
public abstract class ResponseNetErrorListener<T> implements ResponseListener<T>{
    @Override
    public void onNetFault(String errorMsg, int errorCode) {
        //Todo 根据errorCode，统一处理网络问题导致的请求错误
    }
}
