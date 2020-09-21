package com.example.cinemademo.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cinemademo.R;
import com.example.cinemademo.utils.Constants;

/**
 * Bind and convert url to image using Glide library
 */
public class ImageBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView view, final String url) {

        RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);

        Glide.with(view)
                .load(Constants.IMAGE_URL + url)
                .apply(options)
                .into(view);
    }
}
