package com.shsy.mydemo.db.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shsy on 2016/11/27.
 * 倒计时数据库创建
 */

public class CountDownDayItemDBHealper extends SQLiteOpenHelper {

    private static final String NAME = "MyDemoDB";
    private static final int VERSION = 1;

    public CountDownDayItemDBHealper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS count_down_day " +
                "(id integer primary key autoincrement, " +
                "itemId varchar(20), " +
                "name varchar(20), " +
                "time varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
