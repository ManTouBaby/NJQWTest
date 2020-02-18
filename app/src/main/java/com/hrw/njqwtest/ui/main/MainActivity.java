package com.hrw.njqwtest.ui.main;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hrw.njqwtest.R;
import com.hrw.njqwtest.base.BaseViewModelActivity;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.base.utils.SPHelper;
import com.hrw.njqwtest.base.wdiget.recyclerview.OnItemChildClickListener;
import com.hrw.njqwtest.base.wdiget.recyclerview.OnItemClickListener;
import com.hrw.njqwtest.base.wdiget.recyclerview.SmartAdapter;
import com.hrw.njqwtest.base.wdiget.recyclerview.SmartViewHolder;
import com.hrw.njqwtest.bean.BarrierItemBO;
import com.hrw.njqwtest.bean.LoginUserBO;
import com.hrw.njqwtest.bean.TodayLeaderBO;
import com.hrw.njqwtest.databinding.ActivityMainBinding;
import com.hrw.njqwtest.databinding.ItemClassListBinding;
import com.hrw.njqwtest.ui.ClassListItem;
import com.hrw.njqwtest.viewmodel.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseViewModelActivity<HomeModel, ActivityMainBinding> {
    private SmartAdapter<ClassListItem> mSmartAdapter;

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
        RecyclerView recyclerView = mBindingUtil.rvClassListContainer;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setAdapter(mSmartAdapter = new SmartAdapter<ClassListItem>(R.layout.item_class_list) {

            @Override
            protected void onBindView(SmartViewHolder holder, ClassListItem classListItem, int position) {
                ItemClassListBinding listBinding = holder.getViewDataBinding();
                listBinding.setItemBean(classListItem);
                addChildViewClickListener(listBinding.tvSignBarrierEquipmentShow, classListItem, position);
            }
        });

        List<ClassListItem> listItems = new ArrayList<>();
        listItems.add(new ClassListItem("张三", "23"));
        listItems.add(new ClassListItem("李四", "23"));
        listItems.add(new ClassListItem("王五", "23"));
        listItems.add(new ClassListItem("赵柳", "23"));
        mSmartAdapter.setDates(listItems);

        mSmartAdapter.setOnItemChildClickListener(new OnItemChildClickListener<ClassListItem>() {
            @Override
            public void itemChildClick(View view, ClassListItem data, int position) {
                mSmartAdapter.addData(new ClassListItem("新增数据", "56"));
            }
        });
        mSmartAdapter.setOnItemClickListener(new OnItemClickListener<ClassListItem>() {
            @Override
            public void itemClick(ClassListItem data, int position) {
                mSmartAdapter.removeData(position);

            }
        });
    }

    @Override
    protected void getDates() {
        LoginUserBO userBO = SPHelper.getInstance().manager.getObject("LOGIN_USER", LoginUserBO.class);
        mBindingUtil.setLoginUser(userBO);
        if (userBO != null)
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
        if (userBO != null)
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
