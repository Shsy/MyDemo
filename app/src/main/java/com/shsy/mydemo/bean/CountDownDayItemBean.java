package com.shsy.mydemo.bean;

import com.shsy.mydemo.base.BaseBean;

/**
 * Created by Shsy on 2016/11/27.
 */

public class CountDownDayItemBean extends BaseBean {
    private String itemId;
    private String name;
    private String time;

    public CountDownDayItemBean() {
    }

    public CountDownDayItemBean(String itemId, String name, String time) {
        this.itemId = itemId;
        this.name = name;
        this.time = time;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
