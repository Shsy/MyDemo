package com.shsy.mydemo.bean;

import android.databinding.Bindable;

import com.shsy.mydemo.base.BaseBean;

/**
 * Created by 申尚宇 on 2016/10/19.
 */

public class MainListBean extends BaseBean {

    private String name;
    private Class nextAct;

    public MainListBean(String name) {
        this.name = name;
    }

    public MainListBean(String name, Class nextAct) {
        this.name = name;
        this.nextAct = nextAct;
    }

    @Bindable
    public Class getNextAct() {
        return nextAct;
    }

    public void setNextAct(Class nextAct) {
        this.nextAct = nextAct;
        notifyPropertyChanged(com.shsy.mydemo.BR.nextAct);
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
