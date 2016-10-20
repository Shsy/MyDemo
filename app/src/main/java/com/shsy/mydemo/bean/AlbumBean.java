package com.shsy.mydemo.bean;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.shsy.mydemo.base.BaseBean;

/**
 * Created by 申尚宇 on 2016/10/20.
 */

public class AlbumBean extends BaseBean {

    private String path;
    private ObservableBoolean isChecked = new ObservableBoolean();

    public ObservableBoolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(ObservableBoolean isChecked) {
        this.isChecked = isChecked;
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
