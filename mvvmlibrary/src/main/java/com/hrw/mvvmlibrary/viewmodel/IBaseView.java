package com.hrw.mvvmlibrary.viewmodel;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/22 9:37
 * @desc:
 */
public interface IBaseView {
    /**
     * 初始化接收参数
     */
    void initParam();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();
}
