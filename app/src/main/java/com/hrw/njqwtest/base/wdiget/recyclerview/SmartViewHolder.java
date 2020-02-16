package com.hrw.njqwtest.base.wdiget.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:MtBaby
 * @date:2020/02/15 19:34
 * @desc:
 */
public class SmartViewHolder<VDB extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private VDB mDataBinding;

    public SmartViewHolder(@NonNull View view) {
        super(view);
        mDataBinding = DataBindingUtil.bind(view);
    }

    public VDB getDataBinding() {
        return mDataBinding;
    }
}
