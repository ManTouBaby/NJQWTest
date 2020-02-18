package com.hrw.njqwtest.base.wdiget.recyclerview;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:MtBaby
 * @date:2020/02/15 19:34
 * @desc:
 */
public class SmartViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding mDataBinding;

    public SmartViewHolder(@NonNull ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        mDataBinding = viewDataBinding;
    }

    public <VDB extends ViewDataBinding> VDB getViewDataBinding() {
        return (VDB) mDataBinding;
    }
}
