package com.example.cinemademo.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.cinemademo.models.Movie;
import com.example.cinemademo.network.main.MainApi;

import io.reactivex.disposables.CompositeDisposable;

/**
 * class extend paging library function to create and get data from data source
 */
public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<PageKeyedDataSource<Integer, Movie>> movieMutableLiveData = new MutableLiveData<>();
    private MainApi mainApi;
    private CompositeDisposable compositeDisposable;

    public MovieDataSourceFactory(MainApi mainApi, CompositeDisposable compositeDisposable) {
        this.mainApi = mainApi;
        this.compositeDisposable = compositeDisposable;
    }

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        MovieDataSource movieDataSource = new MovieDataSource(mainApi, compositeDisposable);
        movieMutableLiveData.postValue(movieDataSource);

        return movieDataSource;
    }
}
