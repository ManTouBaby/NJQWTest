package com.hrw.njqwtest.base.wdiget.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

/**
 * @author:MtBaby
 * @date:2020/02/16 12:04
 * @desc:
 */
public abstract class SmartMultipleAdapter<T extends SmartMultipleBean, VDB extends ViewDataBinding> extends SmartAdapter<T, VDB> {
    private Map<Integer, Integer> itemTypeCache = new HashMap<>();

    public SmartMultipleAdapter(int resLayout) {
        super(resLayout);
    }

    public SmartMultipleAdapter(List<T> dates, int resLayout) {
        super(dates, resLayout);
    }

    @Override
    public int getItemViewType(int position) {
        return mDates.get(position).getItemType();
    }

    @NonNull
    @Override
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int resLayout = itemTypeCache.get(viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(resLayout, parent, false);
        return new SmartViewHolder(view);
    }
}
