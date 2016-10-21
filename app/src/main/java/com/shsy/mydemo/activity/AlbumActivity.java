package com.shsy.mydemo.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.shsy.mydemo.R;
import com.shsy.mydemo.adapter.AlbumListAdapter;
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
    private AlbumListAdapter adapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            adapter.addList(mAlbumBeanList);
        }
    };

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    protected void initToolBar() {
        mBinding.toolbar.setTitle("相册");
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
        mAlbumBeanList = new ArrayList<>();
        adapter = new AlbumListAdapter(getApplicationContext());
    }

    @Override
    protected void doBusiness() {
        mBinding.albumRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        mBinding.albumRv.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        } else {
            scanImages();
        }
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
                // TODO: 2016/10/20 获取图片不完整
//                Cursor cursor = cr.query(mImgUri, null,
//                        MediaStore.Images.Media.MIME_TYPE + "=?or" + MediaStore.Images.Media.MIME_TYPE + "=?",
//                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);
                Cursor cursor = cr.query(mImgUri, null, null, null, MediaStore.Images.Media.DATE_MODIFIED);

                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) continue;

                    AlbumBean bean = new AlbumBean();
                    bean.setPath(path);
                    mAlbumBeanList.add(0, bean);
                }


                cursor.close();

                mHandler.sendEmptyMessage(0x520);
            }
        }.start();
    }
}
