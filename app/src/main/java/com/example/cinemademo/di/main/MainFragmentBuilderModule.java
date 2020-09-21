package com.example.cinemademo.di.main;

import com.example.cinemademo.ui.main.moviedetail.MovieDetailFragment;
import com.example.cinemademo.ui.main.movielist.MovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MovieListFragment contributeMovieListFragment();

    @ContributesAndroidInjector
    abstract MovieDetailFragment contributeMovieDetailFragment();
}
