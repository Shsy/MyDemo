package com.shsy.mydemo.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shsy.mydemo.BindingViewHolder;
import com.shsy.mydemo.listener.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 申尚宇 on 2016/10/19.
 */

public abstract class BaseDataBindingAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {

    protected List<T> mList;
    protected LayoutInflater inflater;
    protected Context mContext;
    private onItemClickListener onItemClickListener;

    public BaseDataBindingAdapter(Context context) {
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = new ArrayList<>();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = bindLayoutId();
        // 如果没设置layoutId跑出异常
        if (layoutId == 0) throw new RuntimeException("LayoutId is 0");
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, bindLayoutId(), parent, false);
        return new BindingViewHolder<>(binding, onItemClickListener);
    }

    /**
     * 设置条目的Layout
     *
     * @return
     */
    protected abstract int bindLayoutId();

    @Override
    public abstract void onBindViewHolder(BindingViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 设置数据列表
     *
     * @param list
     */
    public void setList(List<T> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加数据列表到末尾
     *
     * @param list
     */
    public void addList(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加条目到末尾
     *
     * @param item
     */
    public void addItem(T item) {
        mList.add(item);
        notifyDataSetChanged();
    }

    /**
     * 添加条目到指定角标
     *
     * @param index
     * @param item
     */
    public void addItem(int index, T item) {
        mList.add(index, item);
        notifyDataSetChanged();
    }

    /**
     * 根据Obj删除条目
     *
     * @param item
     */
    public void removeItem(T item) {
        mList.remove(item);
    }

    /**
     * 根据角标删除条目
     *
     * @param index
     */
    public void removeItem(int index) {
        mList.remove(index);
    }

    /**
     * 设置条目点击监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
