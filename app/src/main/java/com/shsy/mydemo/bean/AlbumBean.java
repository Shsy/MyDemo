package com.shsy.mydemo.bean;

import android.databinding.Bindable;

import com.shsy.mydemo.base.BaseBean;

/**
 * Created by 申尚宇 on 2016/10/20.
 */

public class AlbumBean extends BaseBean {

    private String path;

    @Bindable
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        notifyPropertyChanged(com.shsy.mydemo.BR.path);
    }
}
