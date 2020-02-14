package com.hrw.njqwtest.base.net;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 16:26
 * @desc:
 */
public class BaseSubscribe {


    public <T> void toDetachAndSubscribe(Observable<? extends BaseResponse<T>> observable, ResponseListener<T> listener) {
        Observable<BaseResponse<T>> detachedObservable = detachDataAndMessageFrom(observable);
        detachedObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<>(listener));
    }

    private <T> Observable<BaseResponse<T>> detachDataAndMessageFrom(Observable<? extends BaseResponse<T>> observable) {
        return observable.map(new Function<BaseResponse<T>, BaseResponse<T>>() {
            @Override
            public BaseResponse<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                if (!"200".equals(tBaseResponse.getHttpCode())) {
                    throw new DataFaultException(tBaseResponse.getCode(), tBaseResponse.getMsg());
                }
                return tBaseResponse;
            }
        });
    }

}
