package com.shsy.mydemo.db.dao;

import com.shsy.mydemo.bean.CountDownDayItemBean;

import java.util.List;

/**
 * Created by Shsy on 2016/11/27.
 * 倒计时日数据库操作接口
 */

public interface CountDownDayItemDao {
    void add(CountDownDayItemBean itemBean);

    void remove(String itemId);

    void update(String itemId, CountDownDayItemBean itemBean);

    CountDownDayItemBean select(String itemId);

    List<CountDownDayItemBean> selectAll();
}
