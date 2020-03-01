package com.hrw.njqwtest.base.wdiget.pullrefreshview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.hrw.njqwtest.base.utils.LogHelper;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private View mContentView;
    private OnPullDownListener mOnPullDownListener;
    private OnPullUpListener mOnPullUpListener;
    PullDownType mPullDownType = PullDownType.DEFAULT;
    PullUpType mPullUpType = PullUpType.DEFAULT;

    private final static int BOTTOM = 1;
    private final static int TOP = 2;


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
        if (getChildCount() > 1) {
            throw new IllegalGrammarException("this layout can only hold one childView");
        }

        mContentView = getChildAt(0);
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
                    //下拉操作
                    if (moveSpaceY > 0 && isScroll(TOP)) {
                        isIntercept = true;
                    }
                    //上拉操作
                    if (mTouchSlop < 0 && isScroll(BOTTOM)) {
                        isIntercept = true;
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return isIntercept;
    }


    private boolean isScroll(int scrollType) {
        boolean isFlag = false;
        //ListView  GridView
        if (mContentView instanceof AbsListView) {
            AbsListView absListView = ((AbsListView) mContentView);
            //上滑动操作，判断是否到顶
            if (scrollType == TOP) {
                if (absListView.getCount() == 0 ||
                        absListView.getFirstVisiblePosition() == 0 &&
                                absListView.getChildAt(0).getTop() >= absListView.getPaddingTop()) {
                    isFlag = true;
                }
            }

            //下滑操作，判断是否到底部
            if (scrollType == BOTTOM) {
                int firstVisiblePosition = absListView.getFirstVisiblePosition();
                int lastVisiblePosition = absListView.getLastVisiblePosition();
                if (absListView.getCount() == 0) {
                    isFlag = true;
                } else if (lastVisiblePosition == (absListView.getCount() - 1)) {
                    View view = absListView.getChildAt(lastVisiblePosition - firstVisiblePosition);
                    if (view != null && view.getBottom() <= absListView.getMeasuredHeight() - absListView.getPaddingBottom())
                        isFlag = true;
                }

            }

        }

        if (mContentView instanceof ScrollView || mContentView instanceof NestedScrollView) {
            FrameLayout frameLayout = (FrameLayout) this.mContentView;
            //上滑动操作，判断是否到顶
            if (scrollType == TOP) {
                if (this.mContentView.getScrollY() <= 0) isFlag = true;
            }

            //下滑操作，判断是否到底部
            if (scrollType == BOTTOM) {
                if (frameLayout.getChildCount() == 0 ||
                        frameLayout.getScrollY() >= (frameLayout.getChildAt(0).getHeight() - frameLayout.getMeasuredHeight())) {
                    isFlag = true;
                }
            }

        }

        if (mContentView instanceof WebView) {
            WebView webView = (WebView) this.mContentView;
            //上滑动操作，判断是否到顶
            if (scrollType == TOP) {
                if (webView.getScrollY() <= 0)
                    isFlag = true;
            }
            //下滑操作，判断是否到底部
            if (scrollType == BOTTOM) {
                if (webView.getScrollY() >= webView.getContentHeight() * webView.getScale() - webView.getMeasuredHeight()) {
                    isFlag = true;
                }
            }

        }

        if (mContentView instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) mContentView;
            RecyclerView.LayoutManager layout = recyclerView.getLayoutManager();
            LinearLayoutManager layoutManager = null;
            if (layout instanceof LinearLayoutManager) layoutManager = (LinearLayoutManager) layout;

            //上滑动操作，判断是否到顶
            if (scrollType == TOP) {
                if (layoutManager != null) {
                    if (layoutManager.getItemCount() == 0 || layoutManager.findFirstVisibleItemPosition() == 0 && recyclerView.getChildAt(0).getTop() >= recyclerView.getPaddingTop()) {
                        isFlag = true;
                    }
                }
            }

            //下滑操作，判断是否到底部
            if (scrollType == BOTTOM) {
                if (layoutManager != null) {
                    int count = layoutManager.getItemCount();
                    if (count == 0 || layoutManager.findLastCompletelyVisibleItemPosition() == count - 1) {
                        isFlag = true;
                    }
                }

            }

            if (mContentView instanceof LinearLayout) {
                if (scrollType == TOP) {
                    isFlag = true;
                }
            }

        }
        return isFlag;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogHelper.d("正在滑动:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogHelper.d("正在滑动:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogHelper.d("正在滑动:ACTION_UP");
                break;
        }
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
