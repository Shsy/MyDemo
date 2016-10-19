package com.shsy.mydemo.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.shsy.mydemo.R;
import com.shsy.mydemo.adapter.MainListDataBindingAdapter;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.bean.MainListBean;
import com.shsy.mydemo.databinding.ActivityMainBinding;
import com.shsy.mydemo.listener.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private MainListDataBindingAdapter adapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        adapter = new MainListDataBindingAdapter(getApplicationContext());
        List<MainListBean> beanList = new ArrayList<>();
        beanList.add(new MainListBean("a!!你好 a"));
        beanList.add(new MainListBean("a a"));
        adapter.setList(beanList);
        adapter.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "aaa", Snackbar.LENGTH_SHORT).show();
            }

            public void onLongClick(View v) {
                Snackbar.make(v, "bbb", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void doBusiness() {
        mBinding.mainRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBinding.mainRv.setAdapter(adapter);

    }
}
