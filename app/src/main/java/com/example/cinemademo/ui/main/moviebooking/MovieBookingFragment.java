package com.example.cinemademo.ui.main.moviebooking;

import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.cinemademo.R;
import com.example.cinemademo.databinding.FragmentMovieBookingBinding;
import com.example.cinemademo.utils.Constants;

public class MovieBookingFragment extends Fragment {

    private FragmentMovieBookingBinding fragmentMovieDetailBinding;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMovieDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_booking, container, false);
        webView = fragmentMovieDetailBinding.webviewContent;
        webView.loadUrl(Constants.BOOKING_URL);

        /**
         * improve webview performance
         */
        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        return fragmentMovieDetailBinding.getRoot();
    }
}