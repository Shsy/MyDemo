package com.shsy.mydemo.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.shsy.mydemo.utils.ImageLoader;

/**
 * Created by Shsy on 2016/10/20.
 */

public class DataBindingAdapter {
    @BindingAdapter({"app:url"})
    public static void showImg(ImageView imageView, String url) {
        ImageLoader.getInstence().loadImage(url, imageView);
    }
}
