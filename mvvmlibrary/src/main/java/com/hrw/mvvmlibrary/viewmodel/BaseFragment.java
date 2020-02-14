package com.hrw.mvvmlibrary.viewmodel;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/22 9:41
 * @desc:
 */
public abstract class BaseFragment<T extends ViewDataBinding, VM extends ViewModel> extends RxFragment {
    protected  T mdataBinding;
    protected VM mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mdataBinding = DataBindingUtil.inflate(inflater, initLayout(inflater, container, savedInstanceState), container, false);
        return mdataBinding.getRoot();
    }

    protected abstract int initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected void goTO(Class<Activity> aClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), aClass);
        if (bundle != null) intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    protected void goTo(Class<Activity> aClass) {
        this.goTO(aClass, null);
    }

}
