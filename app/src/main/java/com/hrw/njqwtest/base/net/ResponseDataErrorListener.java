package com.hrw.njqwtest.base.net;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 16:11
 * @desc:
 */
public abstract class ResponseDataErrorListener<T> implements ResponseListener<T>{
    @Override
    public void onDataFault(String errorMsg, int errorCode) {
        //Todo 统一处理数据错误码等
    }
}
