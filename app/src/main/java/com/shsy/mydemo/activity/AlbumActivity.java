package com.shsy.mydemo.activity;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.databinding.ActivityAlbumBinding;

/**
 * Created by 申尚宇 on 2016/10/20.
 */

public class AlbumActivity extends BaseActivity<ActivityAlbumBinding> {
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doBusiness() {
        mBinding.toolbar.setTitle("相册");
    }
}
