package com.shsy.mydemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.shsy.mydemo.BindingViewHolder;
import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseDataBindingAdapter;
import com.shsy.mydemo.bean.MainListBean;
import com.shsy.mydemo.databinding.ItemMainListBinding;


/**
 * Created by 申尚宇 on 2016/10/19.
 */

public class MainListDataBindingAdapter extends BaseDataBindingAdapter<MainListBean> {

    public MainListDataBindingAdapter(Context context) {
        super(context);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.item_main_list;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final ItemMainListBinding dataBinding = (ItemMainListBinding) holder.getmBinding();
        dataBinding.setItem(mList.get(position));
        dataBinding.setPresenter(new Presenter());
        dataBinding.executePendingBindings();
    }

    public class Presenter {
        public void startNextAct(Class cls) {
            mContext.startActivity(new Intent(mContext, cls));
        }
    }
}
