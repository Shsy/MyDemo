package com.shsy.mydemo.adapter;

import android.content.Context;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.DataBindingBaseAdapter;
import com.shsy.mydemo.bean.MainListBean;


/**
 * Created by 申尚宇 on 2016/10/19.
 */

public class MainListAdapter extends DataBindingBaseAdapter<MainListBean> {

    public MainListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.item_main_list;
    }
}
