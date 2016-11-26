package com.shsy.mydemo.activity;

import android.view.View;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.databinding.ActivityCountDownDayBinding;

/**
 * Created by Shsy on 2016/11/26.
 */

public class CountDownDayActivity extends BaseActivity<ActivityCountDownDayBinding> {
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_count_down_day;
    }

    @Override
    protected void initToolBar() {
        mBinding.toolbar.setTitle("CountDownDay");
        mBinding.toolbar.setNavigationIcon(R.mipmap.icon_back);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(mBinding.toolbar);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doBusiness() {

    }
}
