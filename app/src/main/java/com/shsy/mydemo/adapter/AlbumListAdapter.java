package com.shsy.mydemo.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.shsy.mydemo.BindingViewHolder;
import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseDataBindingAdapter;
import com.shsy.mydemo.bean.AlbumBean;
import com.shsy.mydemo.databinding.ItemAlbumListBinding;

/**
 * Created by Shsy on 2016/10/20.
 */

public class AlbumListAdapter extends BaseDataBindingAdapter<AlbumBean> {
    public AlbumListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.item_album_list;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final ItemAlbumListBinding dataBinding = (ItemAlbumListBinding) holder.getmBinding();
        dataBinding.setItem(mList.get(position));
        dataBinding.setPresenter(new Presenter());
        dataBinding.executePendingBindings();
    }

    public class Presenter {
        public void aaa(String str) {
            Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
        }
    }
}
