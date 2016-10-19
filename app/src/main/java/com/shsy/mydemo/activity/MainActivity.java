package com.shsy.mydemo.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.shsy.mydemo.R;
import com.shsy.mydemo.adapter.MainListAdapter;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.bean.MainListBean;
import com.shsy.mydemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private MainListAdapter adapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        adapter = new MainListAdapter(getApplicationContext());
        List<MainListBean> beanList = new ArrayList<>();
        beanList.add(new MainListBean("a!!你好 a"));
        beanList.add(new MainListBean("a a"));
        beanList.add(new MainListBean("a a"));
        beanList.add(new MainListBean("a a"));
        beanList.add(new MainListBean("a a"));
        beanList.add(new MainListBean("a a"));
        adapter.setList(beanList);
    }

    @Override
    protected void doBusiness() {
        mBinding.mainRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBinding.mainRv.setAdapter(adapter);

    }
}
