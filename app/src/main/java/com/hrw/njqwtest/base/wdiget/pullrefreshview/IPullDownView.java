package com.hrw.njqwtest.base.wdiget.pullrefreshview;

/**
 * @author:MtBaby
 * @date:2020/02/18 12:22
 * @desc:下拉刷新基础方法
 */
public interface IPullDownView extends BaseView {
    //正在下拉中
    void onPullDowning();

    //松开手
    void onLosePullDown();

    //正在加载数据
    void onPullDown();

    //下拉刷新成功
    void onPullComplete();
}
