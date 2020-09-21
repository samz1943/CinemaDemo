package com.example.cinemademo.ui.main.movielist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.cinemademo.datasource.MovieDataSourceFactory;
import com.example.cinemademo.models.Movie;
import com.example.cinemademo.network.main.MainApi;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListViewModel extends ViewModel {

    private static final String TAG = "MovieListViewModel";

    private LiveData<PagedList<Movie>> mMoviesPagedList;
    private MovieDataSourceFactory movieDataSourceFactory;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MainApi mainApi;

    @Inject
    public MovieListViewModel(MainApi mainApi) {
        this.mainApi = mainApi;
        movieDataSourceFactory = new MovieDataSourceFactory(mainApi, compositeDisposable);
        init();
    }

    /**
     * initialize paging library setting
     */
    public void init() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .build();

        mMoviesPagedList = new LivePagedListBuilder<>(movieDataSourceFactory, config)
                .build();

    }

    public void refresh() {
        /**
         * clear paging data and fetch again
         */
        mMoviesPagedList.getValue().getDataSource().invalidate();
    }



    public LiveData<PagedList<Movie>> getMoviesLiveData() {
        Log.d(TAG, "mMoviesPagedList size:" + mMoviesPagedList);
        return mMoviesPagedList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
