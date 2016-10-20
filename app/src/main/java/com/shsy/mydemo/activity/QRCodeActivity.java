package com.shsy.mydemo.activity;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.databinding.ActivityQrCodeBinding;

/**
 * Created by 申尚宇 on 2016/10/20.
 */

public class QRCodeActivity extends BaseActivity<ActivityQrCodeBinding> {
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doBusiness() {
        mBinding.toolbar.setTitle("二维码");
    }
}
