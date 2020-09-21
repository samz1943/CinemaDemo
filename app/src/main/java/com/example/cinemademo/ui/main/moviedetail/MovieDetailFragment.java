package com.example.cinemademo.ui.main.moviedetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cinemademo.R;
import com.example.cinemademo.databinding.FragmentMovieDetailBinding;
import com.example.cinemademo.models.Movie;
import com.example.cinemademo.ui.main.movielist.MovieListViewModel;
import com.example.cinemademo.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MovieDetailFragment extends DaggerFragment {

    private FragmentMovieDetailBinding fragmentMovieDetailBinding;
    private MovieDetailViewModel movieDetailViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMovieDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        movieDetailViewModel = new ViewModelProvider(this, providerFactory).get(MovieDetailViewModel.class);

        /**
         * retrieving bundle value from MovieListAdapter
         */
        Movie movieDetail = new Movie();
        movieDetail.setId(getArguments().getInt("id"));

        movieDetailViewModel.getMovieDetail(getArguments().getInt("id"));

        /**
         * observe movie detail data and update to view by data banding
         */
        movieDetailViewModel.getDetailMutableLiveData().observe(getActivity(), detail -> {
            fragmentMovieDetailBinding.setDetail(detail);
        });

        /**
         * Navigator trigger on click method in view by data binding
         */
        fragmentMovieDetailBinding.setMovieDetailNavigtor(new MovieDetailNavigator() {
            @Override
            public void onBooking() {
                Navigation.findNavController(fragmentMovieDetailBinding.getRoot()).navigate(R.id.action_movieDetailFragment_to_movieBookingFragment);
            }
        });


        return fragmentMovieDetailBinding.getRoot();
    }

}