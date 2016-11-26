package com.shsy.mydemo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.databinding.ActivityQrCodeBinding;
import com.shsy.mydemo.utils.zxing.CaptureActivity;

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
        setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(QRCodeActivity.this, CaptureActivity.class);
            startActivity(intent);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class Presenter {
        // 去往扫码页面
        // TODO: 2016/11/24 扫码出错
        public void goToCapture() {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(QRCodeActivity.this, CaptureActivity.class);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(QRCodeActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
            }
        }
    }
}
