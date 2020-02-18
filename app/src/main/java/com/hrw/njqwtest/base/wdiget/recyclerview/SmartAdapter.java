package com.hrw.njqwtest.base.wdiget.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2020/02/15 21:47
 * @desc:
 */
public abstract class SmartAdapter<T> extends RecyclerView.Adapter<SmartViewHolder> {
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
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mResLayout, parent, false);
        return new SmartViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final SmartViewHolder holder, final int position) {
        final int layoutPosition = holder.getLayoutPosition();
        final T t = mDates.get(position);
        View root = holder.getViewDataBinding().getRoot();
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.itemClick(t, layoutPosition);
                }
            }
        });

        root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.itemLongClick(t, layoutPosition);
                }
                return false;
            }
        });
        onBindView(holder, t, layoutPosition);
    }

    protected abstract void onBindView(SmartViewHolder holder, T t, int position);


    @Override
    public int getItemCount() {
        return mDates.size();
    }

    protected void addChildViewLongClickListener(final View view, final T data, final int position) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemChildLongClickListener != null) {
                    onItemChildLongClickListener.itemChildLongClick(view, data, position);
                }
                return false;
            }
        });
    }

    protected void addChildViewClickListener(final View view, final T data, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemChildClickListener != null) {
                    onItemChildClickListener.itemChildClick(view, data, position);
                }
            }
        });
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

    public void removeData(@IntRange(from = 0) final int position) {
        mDates.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDates.size());
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
