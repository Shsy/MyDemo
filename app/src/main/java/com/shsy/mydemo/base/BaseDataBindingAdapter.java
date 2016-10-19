package com.shsy.mydemo.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shsy.mydemo.BindingViewHolder;
import com.shsy.mydemo.bean.MainListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 申尚宇 on 2016/10/19.
 */

public abstract class BaseDataBindingAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {

    protected List<T> mList;
    protected LayoutInflater inflater;
    protected Context mContext;

    public BaseDataBindingAdapter(Context context) {
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = new ArrayList<>();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = bindLayoutId();
        if (layoutId == 0) throw new RuntimeException("LayoutId is 0");
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, bindLayoutId(), parent, false);
        return new BindingViewHolder<>(binding);
    }

    protected abstract int bindLayoutId();

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.getmBinding().setVariable(com.shsy.mydemo.BR.item, mList.get(position));
        holder.getmBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<T> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addList(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        mList.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(T item) {
        mList.remove(item);
        notifyDataSetChanged();
    }
}
