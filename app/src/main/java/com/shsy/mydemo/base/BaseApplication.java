package com.shsy.mydemo.base;

import android.app.Application;
import android.content.Context;

import com.shsy.mydemo.utils.ImageLoader;

/**
 * Created by 申尚宇 on 2016/10/20.
 */

public class BaseApplication extends Application {
    public static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
