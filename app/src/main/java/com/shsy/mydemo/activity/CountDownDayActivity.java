package com.shsy.mydemo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.databinding.ActivityCountDownDayBinding;
import com.shsy.mydemo.fragment.AddDayDialogFragment;
import com.shsy.mydemo.utils.zxing.CaptureActivity;

/**
 * Created by Shsy on 2016/11/26.
 * 倒计时
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
        setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownDayActivity.this.finish();
            }
        });
    }

    @Override
    protected void initData() {
        mBinding.setPresenter(new Presenter());
    }

    @Override
    protected void doBusiness() {
    }

    public class Presenter {
        /**
         * 添加一个倒计时的条目
         */
        public void addDay() {
            AddDayDialogFragment addDayDialogFragment = new AddDayDialogFragment();
            addDayDialogFragment.setOnDissmissListener(new AddDayDialogFragment.OnDismissListener() {
                @Override
                public void onDissmiss() {
                    Snackbar.make(mBinding.getRoot(), "添加成功啦", Snackbar.LENGTH_SHORT).show();
                }
            });
            addDayDialogFragment.show(getSupportFragmentManager(), "0");
        }
    }
}
