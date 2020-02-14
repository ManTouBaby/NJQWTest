package com.hrw.njqwtest.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hrw.njqwtest.R;
import com.hrw.njqwtest.base.BaseActivity;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.base.utils.SPUtil;
import com.hrw.njqwtest.bean.BarrierItemBO;
import com.hrw.njqwtest.bean.LoginUserBO;
import com.hrw.njqwtest.bean.TodayLeaderBO;
import com.hrw.njqwtest.databinding.ActivityMainBinding;
import com.hrw.njqwtest.viewmodel.model.HomeModel;

import java.util.List;

public class MainActivity extends BaseActivity {
    private HomeModel mHomeModel;
    private ActivityMainBinding mMainBinding;


    @Override
    protected void init() {
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mHomeModel = ViewModelProviders.of(this).get(HomeModel.class);

        mHomeModel.getTodayLeaderData().observe(this, new Observer<List<TodayLeaderBO>>() {
            @Override
            public void onChanged(List<TodayLeaderBO> todayLeaderBOS) {
//                mMainBinding.tvDutyLeader.setText(todayLeaderBOS.get(0).getPOLICENAME());
                System.out.println("领导数据加载成功");
            }
        });

        mHomeModel.getBarrierItem().observe(this, new Observer<List<BarrierItemBO>>() {
            @Override
            public void onChanged(List<BarrierItemBO> barrierItemBOS) {

            }
        });
    }

    @Override
    protected void getDates() {
        LoginUserBO userBO = SPUtil.MANAGER().getObject("LOGIN_USER", LoginUserBO.class);
        mMainBinding.setLoginUser(userBO);
        mHomeModel.getBarrierItemDates(userBO.getPoliceNum(), new ResponseListener<List<BarrierItemBO>>() {
            @Override
            public void onDataSuccess(List<BarrierItemBO> data, String msg) {
                mHomeModel.getBarrierItem().setValue(data);
            }

            @Override
            public void onNetFault(String errorMsg, int errorCode) {

            }

            @Override
            public void onDataFault(String errorMsg, int errorCode) {

            }
        });

        mHomeModel.getZbldInfoByDeptId(userBO.getDepartmentId(), new ResponseListener<List<TodayLeaderBO>>() {
            @Override
            public void onDataSuccess(List<TodayLeaderBO> data, String msg) {
                mHomeModel.getTodayLeaderData().setValue(data);
            }

            @Override
            public void onNetFault(String errorMsg, int errorCode) {

            }

            @Override
            public void onDataFault(String errorMsg, int errorCode) {

            }
        });
    }


}
