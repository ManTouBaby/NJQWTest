package com.hrw.njqwtest.viewmodel.model;

import androidx.lifecycle.MutableLiveData;

import com.hrw.njqwtest.base.BaseViewModel;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.bean.BarrierItemBO;
import com.hrw.njqwtest.bean.TodayLeaderBO;
import com.hrw.njqwtest.viewmodel.net.BarrierListRepository;
import com.hrw.njqwtest.viewmodel.net.TodayLeaderRepository;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 10:52
 * @desc:
 */
public class HomeModel extends BaseViewModel {
    private MutableLiveData<List<BarrierItemBO>> mBarrierItem;
    private MutableLiveData<List<TodayLeaderBO>> todayLeaderData;

    private BarrierListRepository mBarrierListRepository;
    private TodayLeaderRepository mTodayLeaderRepository;


    public HomeModel() {
        mBarrierItem = new MutableLiveData<>();

        mBarrierListRepository = new BarrierListRepository();
        mTodayLeaderRepository = new TodayLeaderRepository();
    }

    public MutableLiveData<List<TodayLeaderBO>> getTodayLeaderData() {
        if (todayLeaderData == null) {
            todayLeaderData = new MutableLiveData<>();
        }
        return todayLeaderData;
    }


    public MutableLiveData<List<BarrierItemBO>> getBarrierItem() {
        return mBarrierItem;
    }

    public void setBarrierItem(List<BarrierItemBO> barrierItem) {
        this.mBarrierItem.setValue(barrierItem);
    }

    public void serTodayLeader(List<TodayLeaderBO> todayLeaderBOS) {
        this.todayLeaderData.setValue(todayLeaderBOS);
    }

    public void getBarrierItemDates(String policeNum, ResponseListener<List<BarrierItemBO>> listener) {
        mBarrierListRepository.getBarrierItemDates(policeNum, listener);
    }

    public void getZbldInfoByDeptId(String deptId, ResponseListener<List<TodayLeaderBO>> listener) {
        mTodayLeaderRepository.getZbldInfoByDeptId(deptId, listener);
    }

}
