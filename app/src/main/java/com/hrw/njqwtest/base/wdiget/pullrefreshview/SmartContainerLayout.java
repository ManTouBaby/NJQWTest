package com.hrw.njqwtest.base.wdiget.pullrefreshview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hrw.njqwtest.base.utils.LogHelper;

/**
 * @author:MtBaby
 * @date:2020/02/18 12:27
 * @desc:
 */
public class SmartContainerLayout extends ViewGroup {
    private final int mTouchSlop;
    private Context mContext;
    private BasePullUpView mPullUpView;
    private BasePullDownView mPullDownView;
    private OnPullDownListener mOnPullDownListener;
    private OnPullUpListener mOnPullUpListener;
    PullDownType mPullDownType = PullDownType.DEFAULT;
    PullUpType mPullUpType = PullUpType.DEFAULT;


    public void setPullUpType(PullUpType pullUpType) {
        mPullUpType = pullUpType;
    }

    public void setmPullDownType(PullDownType pullDownType) {
        mPullDownType = pullDownType;
    }

    public SmartContainerLayout(Context context) {
        this(context, null);
    }

    public SmartContainerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mTouchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPullDownView = mPullDownType.instance(mContext);
        mPullUpView = mPullUpType.instance(mContext);
        addView(mPullDownView, 0);
        addView(mPullUpView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }

    }

    float mOriginalX = 0;
    float mOriginalY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mOriginalY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                float moveSpaceY = moveY - mOriginalY;
                LogHelper.d("当前滑动距离:" + moveSpaceY);
                if (Math.abs(moveSpaceY) > mTouchSlop) {
                    isIntercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return isIntercept;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogHelper.d("正在滑动:");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View headerView = getChildAt(0);
        View contentView = getChildAt(1);
        View footerView = getChildAt(2);


        headerView.layout(0, headerView.getMeasuredHeight(), headerView.getMeasuredWidth(), 0);
        contentView.layout(0, 0, contentView.getMeasuredWidth(), contentView.getMeasuredHeight());
        footerView.layout(0, contentView.getMeasuredHeight(), footerView.getMeasuredWidth(), contentView.getMeasuredHeight() + footerView.getMeasuredHeight());
    }

    private void updatePullUpView() {
        mPullUpView = mPullUpType.instance(mContext);
    }

    private void updatePullDownView() {
        mPullDownView = mPullDownType.instance(mContext);
    }
}
