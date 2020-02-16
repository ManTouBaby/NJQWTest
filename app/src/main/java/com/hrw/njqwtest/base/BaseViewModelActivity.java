package com.hrw.njqwtest.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author:MtBaby
 * @date:2020/02/15 13:17
 * @desc:
 */
public abstract class BaseViewModelActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends BaseActivity<VDB> {
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(returnClass());
        super.onCreate(savedInstanceState);
    }

    protected abstract Class<VM> returnClass();
}
