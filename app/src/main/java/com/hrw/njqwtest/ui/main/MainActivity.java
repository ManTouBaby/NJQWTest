package com.hrw.njqwtest.ui.main;

import com.hrw.njqwtest.R;
import com.hrw.njqwtest.base.BaseViewModelActivity;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.base.utils.SPHelper;
import com.hrw.njqwtest.bean.BarrierItemBO;
import com.hrw.njqwtest.bean.LoginUserBO;
import com.hrw.njqwtest.bean.TodayLeaderBO;
import com.hrw.njqwtest.databinding.ActivityMainBinding;
import com.hrw.njqwtest.viewmodel.model.HomeModel;

import java.util.List;

import androidx.lifecycle.Observer;

public class MainActivity extends BaseViewModelActivity<HomeModel, ActivityMainBinding> {

    @Override
    protected int getResLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mViewModel.getTodayLeaderData().observe(this, new Observer<List<TodayLeaderBO>>() {
            @Override
            public void onChanged(List<TodayLeaderBO> todayLeaderBOS) {
//                mMainBinding.tvDutyLeader.setText(todayLeaderBOS.get(0).getPOLICENAME());
                System.out.println("领导数据加载成功");
            }
        });

        mViewModel.getBarrierItem().observe(this, new Observer<List<BarrierItemBO>>() {
            @Override
            public void onChanged(List<BarrierItemBO> barrierItemBOS) {

            }
        });
    }

    @Override
    protected void getDates() {
        LoginUserBO userBO = SPHelper.getInstance().manager.getObject("LOGIN_USER", LoginUserBO.class);
        mBindingUtil.setLoginUser(userBO);
        mViewModel.getBarrierItemDates(userBO.getPoliceNum(), new ResponseListener<List<BarrierItemBO>>() {
            @Override
            public void onDataSuccess(List<BarrierItemBO> data, String msg) {
                mViewModel.getBarrierItem().setValue(data);
            }

            @Override
            public void onNetFault(String errorMsg, int errorCode) {

            }

            @Override
            public void onDataFault(String errorMsg, int errorCode) {

            }
        });

        mViewModel.getZbldInfoByDeptId(userBO.getDepartmentId(), new ResponseListener<List<TodayLeaderBO>>() {
            @Override
            public void onDataSuccess(List<TodayLeaderBO> data, String msg) {
                mViewModel.getTodayLeaderData().setValue(data);
            }

            @Override
            public void onNetFault(String errorMsg, int errorCode) {

            }

            @Override
            public void onDataFault(String errorMsg, int errorCode) {

            }
        });
    }


    @Override
    protected Class<HomeModel> returnClass() {
        return HomeModel.class;
    }
}
