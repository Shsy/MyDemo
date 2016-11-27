package com.shsy.mydemo.db.daoimpl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shsy.mydemo.bean.CountDownDayItemBean;
import com.shsy.mydemo.db.dao.CountDownDayItemDao;
import com.shsy.mydemo.db.dbhelper.CountDownDayItemDBHealper;
import com.shsy.mydemo.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shsy on 2016/11/27.
 * 倒计时日数据库操作实现类
 */
@SuppressWarnings("all")
public class CountDownDayItemDaoImpl implements CountDownDayItemDao {

    private static CountDownDayItemDaoImpl instence;
    private CountDownDayItemDBHealper dbHealper;

    public static CountDownDayItemDaoImpl getInstence() {
        if (instence == null) {
            synchronized (CountDownDayItemDaoImpl.class) {
                if (instence == null) {
                    instence = new CountDownDayItemDaoImpl();
                }
            }
        }
        return instence;
    }

    private CountDownDayItemDaoImpl() {
        dbHealper = new CountDownDayItemDBHealper(AppUtil.getContext());
    }


    @Override
    public void add(CountDownDayItemBean itemBean) {
        SQLiteDatabase db = dbHealper.getWritableDatabase();

        db.execSQL("insert into count_down_day(itemId, name, time, isShow) values(?,?,?,?)",
                new String[]{itemBean.getItemId(), itemBean.getName(), itemBean.getTime(), itemBean.getIsShow()});

        db.close();
    }

    @Override
    public void remove(String itemId) {
        SQLiteDatabase db = dbHealper.getWritableDatabase();

        db.execSQL("delete from count_down_day where itemId = ?",
                new String[]{itemId});

        db.close();
    }

    @Override
    public void update(String itemId, CountDownDayItemBean itemBean) {
        SQLiteDatabase db = dbHealper.getWritableDatabase();

        db.execSQL("update count_down_day set itemId = ?, name = ?, time = ?, isShow = ?",
                new String[]{itemId, itemBean.getName(), itemBean.getTime(),itemBean.getIsShow()});

        db.close();
    }

    @Override
    public CountDownDayItemBean select(String itemId) {
        SQLiteDatabase db = dbHealper.getWritableDatabase();

        Cursor c = db.rawQuery("select * from count_down_day where itemId = ?",
                new String[]{itemId});

        CountDownDayItemBean itemBean = null;
        if (c.moveToNext()) {
            itemBean = new CountDownDayItemBean(
                    c.getString(c.getColumnIndex("itemId")),
                    c.getString(c.getColumnIndex("name")),
                    c.getString(c.getColumnIndex("time")),
                    c.getString(c.getColumnIndex("isShow"))
            );
        }

        c.close();
        db.close();
        return itemBean;
    }

    @Override
    public List<CountDownDayItemBean> selectAll() {
        SQLiteDatabase db = dbHealper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from count_down_day", null);
        List<CountDownDayItemBean> itemBeanList = new ArrayList<>();
        CountDownDayItemBean itemBean = null;

        while (c.moveToNext()) {
            itemBean = new CountDownDayItemBean(
                    c.getString(c.getColumnIndex("itemId")),
                    c.getString(c.getColumnIndex("name")),
                    c.getString(c.getColumnIndex("time")),
                    c.getString(c.getColumnIndex("isShow"))
            );
            itemBeanList.add(itemBean);
            itemBean = null;
        }
        c.close();
        db.close();
        return itemBeanList;
    }
}
