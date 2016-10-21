package com.shsy.mydemo.bean;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.shsy.mydemo.base.BaseBean;

/**
 * Created by 申尚宇 on 2016/10/20.
 */

public class AlbumBean extends BaseBean {

    private String path;
    private boolean isChecked;

    @Bindable
    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
        notifyPropertyChanged(com.shsy.mydemo.BR.isChecked);
    }

    @Bindable
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        notifyPropertyChanged(com.shsy.mydemo.BR.path);
    }
}
