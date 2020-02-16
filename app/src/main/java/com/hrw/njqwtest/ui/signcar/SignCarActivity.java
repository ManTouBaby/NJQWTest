package com.hrw.njqwtest.ui.signcar;

import com.hrw.njqwtest.R;
import com.hrw.njqwtest.base.BaseViewModelActivity;
import com.hrw.njqwtest.databinding.ActivitySigncarBinding;
import com.hrw.njqwtest.viewmodel.model.SignCarModel;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/19 17:15
 * @desc:
 */
public class SignCarActivity extends BaseViewModelActivity<SignCarModel, ActivitySigncarBinding> {
    @Override
    protected int getResLayout() {
        return R.layout.activity_signcar;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void getDates() {

    }

    @Override
    protected Class<SignCarModel> returnClass() {
        return SignCarModel.class;
    }
}
