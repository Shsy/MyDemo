package com.shsy.mydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.shsy.mydemo.BindingViewHolder;
import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseDataBindingAdapter;
import com.shsy.mydemo.bean.MainListBean;
import com.shsy.mydemo.databinding.ItemMainListBinding;


/**
 * Created by 申尚宇 on 2016/10/19.
 */

public class MainListDataBindingAdapter extends BaseDataBindingAdapter<MainListBean> {
    /**
     * SnackBar用
     */
    private View v;

    public MainListDataBindingAdapter(Context context) {
        super(context);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.item_main_list;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        v = holder.itemView;
        final ItemMainListBinding dataBinding = (ItemMainListBinding) holder.getmBinding();
        dataBinding.setItem(mList.get(position));
        dataBinding.setPresenter(new Presenter());
        dataBinding.executePendingBindings();
    }

    public class Presenter {
        public void startNextAct(Class cls) {
            if (cls != null)
                mContext.startActivity(new Intent(mContext, cls));
            else
                Snackbar.make(v, "正在开发，不要着急", Snackbar.LENGTH_LONG)
                        .setAction("知道了", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
        }
    }
}
