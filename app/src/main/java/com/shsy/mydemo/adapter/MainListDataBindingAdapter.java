package com.shsy.mydemo.adapter;

import android.content.Context;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseDataBindingAdapter;
import com.shsy.mydemo.bean.MainListBean;


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
}
