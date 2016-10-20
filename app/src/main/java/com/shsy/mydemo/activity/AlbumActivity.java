package com.shsy.mydemo.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseActivity;
import com.shsy.mydemo.bean.AlbumBean;
import com.shsy.mydemo.databinding.ActivityAlbumBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 申尚宇 on 2016/10/20.
 */

public class AlbumActivity extends BaseActivity<ActivityAlbumBinding> {
    private ProgressDialog mProgressDialog;
    private List<AlbumBean> mAlbumBeanList;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    protected void initToolBar() {
        mBinding.toolbar.setTitle("相册");
    }

    @Override
    protected void initData() {
        // TODO: 2016/10/20  have Bug (permission)
        mAlbumBeanList = new ArrayList<>();
        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            Snackbar.make(mBinding.getRoot(), "asdf", Snackbar.LENGTH_SHORT).show();

            scanImages();
        }
    }

    @Override
    protected void doBusiness() {
    }

    /**
     * 扫描手机中的图片
     */
    private void scanImages() {
        if (!TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
            Snackbar.make(mBinding.getRoot(), "没有储存卡啊亲", Snackbar.LENGTH_SHORT).show();
            return;
        }

        new Thread() {
            @Override
            public void run() {
                Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = AlbumActivity.this.getContentResolver();
                Cursor cursor = cr.query(mImgUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "?or" + MediaStore.Images.Media.MIME_TYPE + "?",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) continue;

                    AlbumBean bean = new AlbumBean();
                    bean.setPath(path);
                    mAlbumBeanList.add(bean);
                }
            }
        }.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scanImages();
                } else {
                    //用户拒绝授权
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case REQUEST_CODE:
//                if(grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    //用户同意授权
//                    callPhone();
//                }else{
//                    //用户拒绝授权
//                }
//                break;
//        }
//    }
}
