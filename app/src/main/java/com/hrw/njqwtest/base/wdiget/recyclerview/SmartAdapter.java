package com.hrw.njqwtest.base.wdiget.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:MtBaby
 * @date:2020/02/15 21:47
 * @desc:
 */
public abstract class SmartAdapter<T, VDB extends ViewDataBinding> extends RecyclerView.Adapter<SmartViewHolder<VDB>> {
    protected List<T> mDates = new ArrayList<>();
    protected int mResLayout;
    protected OnItemClickListener<T> onItemClickListener;
    protected OnItemLongClickListener<T> onItemLongClickListener;
    protected OnItemChildClickListener<T> onItemChildClickListener;
    protected OnItemChildLongClickListener<T> onItemChildLongClickListener;



    public SmartAdapter(int resLayout) {
        this(null, resLayout);
    }

    public SmartAdapter(List<T> dates, int resLayout) {
        if (dates != null) this.mDates = dates;
        mResLayout = resLayout;
    }


    @NonNull
    @Override
    public SmartViewHolder<VDB> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mResLayout, parent, false);
        return new SmartViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartViewHolder<VDB> holder, final int position) {
        final T t = mDates.get(position);
        View root = holder.getDataBinding().getRoot();
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.itemClick(t, position);
                }
            }
        });

        root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.itemLongClick(t, position);
                }
                return false;
            }
        });
        onBindView(holder, t, position);
    }

    protected abstract void onBindView(SmartViewHolder<VDB> holder, T t, int position);


    @Override
    public int getItemCount() {
        return mDates.size();
    }

    protected void addChildViewLongClickLisener(View view, T data, int position) {
        if (onItemChildLongClickListener != null) {
            onItemChildLongClickListener.itemChildLongClick(view, data, position);

        }
    }

    protected void addChildViewClickLisener(View view, T data, int position) {
        if (onItemChildClickListener != null) {
            onItemChildClickListener.itemChildClick(view, data, position);
        }
    }

    public void setDates(List<T> dates) {
        this.mDates = dates;
        notifyDataSetChanged();
    }

    public void addDates(List<T> dates) {
        int beforeInsertCount = mDates.size();
        this.mDates.addAll(dates);
        notifyItemRangeInserted(beforeInsertCount, dates.size());
    }

    public void addData(T data) {
        int beforeInsertCount = mDates.size();
        this.mDates.add(data);
        notifyItemInserted(beforeInsertCount);
    }

    public void removeData(@IntRange(from = 0) int position) {
        mDates.remove(position);
        notifyItemRemoved(position);
    }

    public void removeDates(List<T> dates) {
        for (int i = 0; i < dates.size(); i++) {
            T removeData = dates.get(i);
            for (int j = 0; j < mDates.size(); j++) {
                T originalData = mDates.get(j);
                if (removeData.equals(originalData)) {
                    removeData(j);
                    break;
                }
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener<T> onItemChildClickListener) {
        this.onItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemChildLongClickListener(OnItemChildLongClickListener<T> onItemChildLongClickListener) {
        this.onItemChildLongClickListener = onItemChildLongClickListener;
    }
}
