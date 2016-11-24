package com.shsy.mydemo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.shsy.mydemo.R;
import com.shsy.mydemo.adapter.MainListDataBindingAdapter;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.bean.MainListBean;
import com.shsy.mydemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 申尚宇 on 2016/10/20.
 * 主页面
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private MainListDataBindingAdapter adapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolBar() {
        mBinding.toolbar.setTitle(R.string.app_name);
    }

    @Override
    protected void initData() {
        adapter = new MainListDataBindingAdapter(this);
        List<MainListBean> beanList = new ArrayList<>();
        beanList.add(new MainListBean("相册", AlbumActivity.class));
        beanList.add(new MainListBean("二维码", QRCodeActivity.class));
        adapter.setList(beanList);
    }

    @Override
    protected void doBusiness() {
        mBinding.mainRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBinding.mainRv.setAdapter(adapter);
    }

    public class Presenter {
        public void asdf() {
            Toast.makeText(MainActivity.this, "aaaa", Toast.LENGTH_SHORT).show();
        }
    }
}
