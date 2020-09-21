package com.example.cinemademo.di.main;

import androidx.lifecycle.ViewModel;

import com.example.cinemademo.di.ViewModelKey;
import com.example.cinemademo.ui.main.moviedetail.MovieDetailViewModel;
import com.example.cinemademo.ui.main.movielist.MovieListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    public abstract ViewModel bindMovieListViewModel(MovieListViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel.class)
    public abstract ViewModel bindMovieDetailViewModel(MovieDetailViewModel viewModel);
}
