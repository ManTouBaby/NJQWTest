package com.hrw.njqwtest.ui.main;

import android.view.View;

import com.hrw.njqwtest.R;
import com.hrw.njqwtest.base.BaseViewModelActivity;
import com.hrw.njqwtest.base.net.ResponseListener;
import com.hrw.njqwtest.base.utils.SPHelper;
import com.hrw.njqwtest.base.wdiget.recyclerview.OnItemChildClickListener;
import com.hrw.njqwtest.base.wdiget.recyclerview.OnItemClickListener;
import com.hrw.njqwtest.base.wdiget.recyclerview.SmartMultipleAdapter;
import com.hrw.njqwtest.base.wdiget.recyclerview.SmartViewHolder;
import com.hrw.njqwtest.bean.BarrierItemBO;
import com.hrw.njqwtest.bean.LoginUserBO;
import com.hrw.njqwtest.bean.TodayLeaderBO;
import com.hrw.njqwtest.databinding.ActivityMainBinding;
import com.hrw.njqwtest.databinding.ItemClassListBinding;
import com.hrw.njqwtest.databinding.ItemTest1Binding;
import com.hrw.njqwtest.databinding.ItemTestBinding;
import com.hrw.njqwtest.ui.ClassListItem;
import com.hrw.njqwtest.viewmodel.model.HomeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseViewModelActivity<HomeModel, ActivityMainBinding> {
    private SmartMultipleAdapter<ClassListItem> mSmartAdapter;

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

        Map<Integer, Integer> itemTypeCache = new HashMap<>();
        itemTypeCache.put(1, R.layout.item_class_list);
        itemTypeCache.put(2, R.layout.item_test);
        itemTypeCache.put(3, R.layout.item_test1);
        recyclerView.setAdapter(mSmartAdapter = new SmartMultipleAdapter<ClassListItem>(itemTypeCache, R.layout.item_class_list) {

            @Override
            protected void onBindView(SmartViewHolder holder, ClassListItem classListItem, int position) {
                switch (classListItem.getItemType()) {
                    case 1:
                        ItemClassListBinding listBinding = holder.getViewDataBinding();
                        listBinding.setItemBean(classListItem);
                        addChildViewClickListener(listBinding.tvSignBarrierEquipmentShow, classListItem, position);
                        break;
                    case 2:
                        ItemTestBinding testBinding = holder.getViewDataBinding();
                        testBinding.setItemBean(classListItem);
                        break;
                    case 3:
                        ItemTest1Binding itemTest1Binding = holder.getViewDataBinding();
                        itemTest1Binding.setItemBean(classListItem);
                        break;
                }

            }
        });

        List<ClassListItem> listItems = new ArrayList<>();
        listItems.add(new ClassListItem("张三", "23", 1));

        listItems.add(new ClassListItem("李四", "23", 1));
        listItems.add(new ClassListItem("王五", "23", 2));
        listItems.add(new ClassListItem("赵柳", "23", 1));
        listItems.add(new ClassListItem("赵柳", "23", 2));
        listItems.add(new ClassListItem("赵柳", "23", 3));
        mSmartAdapter.setDates(listItems);

        mSmartAdapter.setOnItemChildClickListener(new OnItemChildClickListener<ClassListItem>() {
            @Override
            public void itemChildClick(View view, ClassListItem data, int position) {
                int random = (int) (Math.random() * 3) + 1;
                System.out.println(random);
                mSmartAdapter.addData(new ClassListItem("新增数据", "56",random));
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
