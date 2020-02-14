package com.hrw.njqwtest.base.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 16:14
 * @desc:
 */
public class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private ResponseListener<T> mListener;
    /**
     * @param listener 回调监听
     */
    public BaseObserver(ResponseListener<T> listener) {
        mListener = listener;
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse<T> t) {
        mListener.onDataSuccess(t.getData(), t.getMsg());
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof DataFaultException) {
            DataFaultException fault = (DataFaultException) e;
            mListener.onDataFault(fault.getMessage(), fault.getErrorCode());
            return;
        }
        try {
            if (e instanceof SocketTimeoutException) {//请求超时
                mListener.onNetFault("请求超时", 8);
            } else if (e instanceof ConnectException) {//网络连接超时
                mListener.onNetFault("网络连接超时", 1);
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                mListener.onNetFault("安全证书异常", 2);
            } else if (e instanceof HttpException) {//请求的地址不存在
                int code = ((HttpException) e).code();
                if (code == 504) {
                    mListener.onNetFault("网络异常，请检查您的网络状态", 3);
                } else if (code == 404) {
                    mListener.onNetFault("请求的地址不存在", 4);
                } else {
                    mListener.onNetFault("请求失败", 5);
                }
            } else if (e instanceof UnknownHostException) {
                mListener.onNetFault("域名解析失败", 6);
            } else {
                mListener.onNetFault("error:" + e.getMessage(), 7);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

}
