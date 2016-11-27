package com.shsy.mydemo.utils;

import android.content.Context;

import com.shsy.mydemo.base.BaseApplication;

/**
 * Created by Shsy on 2016/11/27.
 * 关于App的工具类
 */

public class AppUtil {
    public static Context getContext() {
        return BaseApplication.mContext;
    }
}
