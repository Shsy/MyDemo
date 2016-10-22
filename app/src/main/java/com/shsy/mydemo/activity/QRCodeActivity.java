package com.shsy.mydemo.activity;

import android.view.View;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.databinding.ActivityQrCodeBinding;

/**
 * Created by 申尚宇 on 2016/10/20.
 * 二维码页面
 */

public class QRCodeActivity extends BaseActivity<ActivityQrCodeBinding> {
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void initToolBar() {
        mBinding.toolbar.setTitle("二维码");
        mBinding.toolbar.setNavigationIcon(R.mipmap.icon_back);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doBusiness() {

    }
}
