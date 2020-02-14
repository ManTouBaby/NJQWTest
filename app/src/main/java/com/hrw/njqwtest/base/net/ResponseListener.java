package com.hrw.njqwtest.base.net;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 16:10
 * @desc:
 */
public interface ResponseListener<T> {
    // 请求到期望数据，返回泛型数据及服务端消息
    void onDataSuccess(T data, String msg);
    // 网络错误，返回错误信息及错误码
    void onNetFault(String errorMsg, int errorCode);
    // 未请求到期望数据，返回服务端消息及错误码
    void onDataFault(String errorMsg, int errorCode);

}
