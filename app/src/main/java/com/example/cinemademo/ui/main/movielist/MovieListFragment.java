package com.example.cinemademo.ui.main.movielist;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinemademo.R;
import com.example.cinemademo.databinding.FragmentMovieListBinding;
import com.example.cinemademo.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MovieListFragment extends DaggerFragment {

    private static final String TAG = "MovieListFragment";

    private FragmentMovieListBinding fragmentMovieListBinding;
    private MovieListViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Inject
    MovieListAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMovieListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);
        viewModel = new ViewModelProvider(this, providerFactory).get(MovieListViewModel.class);

        recyclerView = fragmentMovieListBinding.recyclerView;
        swipeRefreshLayout = fragmentMovieListBinding.mainSwipeRefresh;

        /**
         * setup recyclerview
         */
        initRecyclerView();

        /**
         * pull to refresh function
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        /**
         * observe movie data and update to recyclerview
         */
        viewModel.getMoviesLiveData().observe(getActivity(), movies -> {
            adapter.submitList(movies);
        });

        return fragmentMovieListBinding.getRoot();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}