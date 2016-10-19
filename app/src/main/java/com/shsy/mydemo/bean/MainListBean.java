package com.shsy.mydemo.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by 申尚宇 on 2016/10/19.
 */

public class MainListBean extends BaseObservable {

    private String name;

    public MainListBean(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.shsy.mydemo.BR.name);
    }
}
