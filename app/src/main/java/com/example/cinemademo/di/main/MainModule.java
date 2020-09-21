package com.example.cinemademo.di.main;

import com.example.cinemademo.network.main.MainApi;
import com.example.cinemademo.ui.main.movielist.MovieListAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MovieListAdapter provideAdapter() {
        return new MovieListAdapter();
    }

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
