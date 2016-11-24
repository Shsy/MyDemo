package com.shsy.mydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.shsy.mydemo.BindingViewHolder;
import com.shsy.mydemo.R;
import com.shsy.mydemo.base.BaseDataBindingAdapter;
import com.shsy.mydemo.bean.AlbumBean;
import com.shsy.mydemo.databinding.ItemAlbumListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shsy on 2016/10/20.
 */

public class AlbumListAdapter extends BaseDataBindingAdapter<AlbumBean> {
    /**
     * 记录被选中的图片的路径
     */
    private List<String> mCheckImgList;

    public AlbumListAdapter(Context context) {
        super(context);
        mCheckImgList = new ArrayList<>();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.item_album_list;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final ItemAlbumListBinding dataBinding = (ItemAlbumListBinding) holder.getmBinding();
        setImageViewSize(dataBinding.imageView);
        dataBinding.setItem(mList.get(position));
        dataBinding.setPresenter(new Presenter());
        dataBinding.executePendingBindings();
    }

    /**
     * 设置图片宽高
     *
     * @param imageView
     */
    private void setImageViewSize(ImageView imageView) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = mContext.getResources().getDisplayMetrics().widthPixels / 3;
        layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels / 3;
        imageView.setLayoutParams(layoutParams);
    }

    public void setMCheckImgList(List<String> mCheckImgList) {
        this.mCheckImgList = mCheckImgList;
    }

    public List<String> getMCheckImgList() {
        return mCheckImgList;
    }

    public class Presenter {
        /**
         * imageView的点击事件
         *
         * @param albumBean 点击imageview对应的bean数据
         */
        public void onImageViewClick(AlbumBean albumBean) {
            albumBean.setIsChecked(!albumBean.getIsChecked());
        }

        /**
         * checkbox的选中监听
         *
         * @param imageView 当前改变对应的imageview
         * @param isChecked 是否被选中
         * @param path      被选中的imageView对应的路径
         */
        public void onCheckedChanged(ImageView imageView, boolean isChecked, String path) {
            if (isChecked) {
                imageView.setColorFilter(Color.parseColor("#77000000"));
                mCheckImgList.add(path);
            } else {
                imageView.setColorFilter(null);
                mCheckImgList.remove(path);
            }
        }
    }
}
