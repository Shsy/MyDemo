package com.shsy.mydemo.db.utils;

import com.shsy.mydemo.bean.CountDownDayItemBean;
import com.shsy.mydemo.db.daoimpl.CountDownDayItemDaoImpl;

import java.util.List;

/**
 * Created by Shsy on 2016/11/27.
 */

public class CountDownDayItemDBUtil {

    public static void add(CountDownDayItemBean itemBean) {
        CountDownDayItemDaoImpl.getInstence().add(itemBean);
    }

    public static void remove(String itemId) {
        CountDownDayItemDaoImpl.getInstence().remove(itemId);
    }

    public static void update(String itemId, CountDownDayItemBean itemBean) {
        CountDownDayItemDaoImpl.getInstence().update(itemId, itemBean);
    }

    public static CountDownDayItemBean select(String itemId) {
        return CountDownDayItemDaoImpl.getInstence().select(itemId);
    }

    public static List<CountDownDayItemBean> selectAll() {
        return CountDownDayItemDaoImpl.getInstence().selectAll();
    }
}
