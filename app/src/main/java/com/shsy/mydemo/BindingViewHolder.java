package com.shsy.mydemo;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shsy.mydemo.listener.onItemClickListener;

/**
 * Created by 申尚宇 on 2016/10/19.
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected T mBinding;

    public BindingViewHolder(T binding, final onItemClickListener onItemClickListener) {
        super(binding.getRoot());
        final View rootView = binding.getRoot();
        if (onItemClickListener != null) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(v);
                }
            });
            rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(v);
                    return true;
                }
            });
        }
        mBinding = binding;
    }

    public T getmBinding() {
        return mBinding;
    }
}
