package com.hrw.njqwtest.base.wdiget.pullrefreshview;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/02/19 14:53
 * @desc:
 */
public abstract class BasePullUpView extends RelativeLayout implements IPullUpView {
    protected Context mContext;
    protected View mContentView;

    public BasePullUpView(Context context) {
        super(context);
        mContext = context;
        mContentView = getViewLayout();
        addView(mContentView);
    }

    @Override
    public int getViewHeight() {
        return mContentView.getMeasuredHeight();
    }
}
