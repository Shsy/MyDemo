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

    private SimpleDateFormat sdf;

    public CountDownDayAdapter(Context context) {
        super(context);
        sdf = new SimpleDateFormat("dd天HH小时mm分钟ss秒");
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
            return "距离 " + name + " 还剩 " + sdf.format(new Date(Long.parseLong(time) - System.currentTimeMillis()));
        }

        public void onClick(String itemId) {
            notifyDataSetChanged();
        }
    }
}
