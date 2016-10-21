package com.shsy.mydemo.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shsy.mydemo.utils.ImageLoader;

/**
 * Created by Shsy on 2016/10/20.
 */

public class DataBindingAdapter {
    @BindingAdapter({"url"})
    public static void showImg(ImageView imageView, String url) {
//        ImageLoader.getInstence().loadImage(url, imageView);

        int size = imageView.getContext().getResources().getDisplayMetrics().heightPixels / 3;
        Glide.with(imageView.getContext())
                .load(url)
                .override(size, size)
                .centerCrop()
                .into(imageView);
    }
}
