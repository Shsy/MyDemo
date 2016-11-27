package com.shsy.mydemo.bean;

import com.shsy.mydemo.base.BaseBean;

/**
 * Created by Shsy on 2016/11/27.
 */

public class CountDownDayItemBean extends BaseBean {
    /**
     * 条目ID
     */
    private String itemId;
    /**
     * 名字
     */
    private String name;
    /**
     * 时间,为设置时间的时间戳
     */
    private String time;
    /**
     * 是否显示
     * 0:不显示
     * 1:显示
     */
    private String isShow;

    public CountDownDayItemBean() {
    }

    public CountDownDayItemBean(String itemId, String name, String time, String isShow) {
        this.itemId = itemId;
        this.name = name;
        this.time = time;
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "CountDownDayItemBean{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", isShow='" + isShow + '\'' +
                '}';
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
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
