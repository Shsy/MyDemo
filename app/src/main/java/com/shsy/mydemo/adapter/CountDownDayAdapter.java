package com.shsy.mydemo.adapter;

import android.content.Context;
import android.widget.Toast;

import com.shsy.mydemo.BindingViewHolder;
import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseDataBindingAdapter;
import com.shsy.mydemo.bean.CountDownDayItemBean;
import com.shsy.mydemo.databinding.ItemCountDownDayBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shsy on 2016/11/27.
 */

public class CountDownDayAdapter extends BaseDataBindingAdapter<CountDownDayItemBean> {

    public CountDownDayAdapter(Context context) {
        super(context);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.item_count_down_day;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ItemCountDownDayBinding dataBinding = (ItemCountDownDayBinding) holder.getmBinding();
        dataBinding.setItem(mList.get(position));
        dataBinding.setPresenter(new Presenter());
        dataBinding.executePendingBindings();

    }

    public class Presenter {
        public String getItemStr(String name, String time) {

            long diff = Long.parseLong(time) - System.currentTimeMillis();
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long second = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;

            return "距离 " + name + " 还剩 " + days + "天" + hours + "小时" + minutes + "分" + second + "秒";
        }

        public void onClick(String itemId) {
            notifyDataSetChanged();
        }
    }
}
