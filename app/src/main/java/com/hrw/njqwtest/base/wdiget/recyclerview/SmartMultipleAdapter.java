package com.hrw.njqwtest.base.wdiget.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author:MtBaby
 * @date:2020/02/16 12:04
 * @desc:
 */
public abstract class SmartMultipleAdapter<T extends SmartMultipleBean> extends SmartAdapter<T> {
    private Map<Integer, Integer> itemTypeCache;
    private List<ViewDataBinding> mHeaderView = new ArrayList<>();
    private List<ViewDataBinding> mFooterView = new ArrayList<>();

    public SmartMultipleAdapter(@NonNull Map<Integer, Integer> itemTypeCache, int resLayout) {
        super(resLayout);
        this.itemTypeCache = itemTypeCache;
    }

    public SmartMultipleAdapter(@NonNull Map<Integer, Integer> itemTypeCache, List<T> dates, int resLayout) {
        super(dates, resLayout);
        this.itemTypeCache = itemTypeCache;
    }

    @Override
    public int getItemViewType(int position) {
        return mDates.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @NonNull
    @Override
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int resLayout = itemTypeCache.get(viewType);
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), resLayout, parent, false);
        return new SmartViewHolder(inflate);
    }

    public void setHeaderView(Context context,int resLayout) {
//        DataBindingUtil.bind()
    }

    public void setFooterView() {

    }
}
