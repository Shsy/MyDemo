package com.shsy.mydemo.bean;

import android.databinding.Bindable;

import com.shsy.mydemo.BR;
import com.shsy.mydemo.base.BaseBean;

/**
 * Created by Shsy on 2016/11/27.
 * 倒计时
 */

public class CountDownDayBean extends BaseBean {
    private String day;
    private String time;
    private String thing;


    @Bindable
    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
        notifyPropertyChanged(BR.thing);
    }


    public CountDownDayBean() {
        day = "选择日期";
        time = "选择时间";
    }

    @Bindable
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
        notifyPropertyChanged(BR.day);
    }

    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }
}
