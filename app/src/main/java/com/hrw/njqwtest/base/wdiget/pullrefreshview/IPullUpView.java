package com.hrw.njqwtest.base.wdiget.pullrefreshview;

/**
 * @author:MtBaby
 * @date:2020/02/18 12:22
 * @desc:上拉加载基础方法
 */
public interface IPullUpView extends BaseView {
    //正在上拉中
    void onPullUpping();

    //松开手
    void onLosePullUp();

    //数据加载中
    void onPullUp();

    //上拉加载结束
    void onPullComplete();
}
