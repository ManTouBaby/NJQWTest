package com.hrw.njqwtest.base.wdiget.recyclerview;

import android.view.View;

/**
 * @author:MtBaby
 * @date:2020/02/16 0:03
 * @desc:
 */
public interface OnItemChildClickListener<T> {
    void itemChildClick(View view, T data, int position);
}
