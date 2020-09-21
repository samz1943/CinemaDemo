package com.example.cinemademo.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.cinemademo.models.Discover;
import com.example.cinemademo.models.Movie;
import com.example.cinemademo.network.main.MainApi;
import com.example.cinemademo.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * class required for paging library to get data source
 */
public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    private static final String TAG = "MovieDataSource";

    private CompositeDisposable compositeDisposable;
    private MainApi mainApi;

    public MovieDataSource(MainApi mainApi, CompositeDisposable compositeDisposable) {
        this.mainApi = mainApi;
        this.compositeDisposable = compositeDisposable;
    }

    /**
     * getting movie data on page 1
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        compositeDisposable.add(
                mainApi.getMovies(Constants.API_KEY, "2016-12-31", "release_date.desc", 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Discover>() {
                        @Override
                        public void onSuccess(Discover discover) {
                            callback.onResult(discover.getResults(),null, 2);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                        }
                    })
        );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    /**
     * getting movie data according to current next page
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        compositeDisposable.add(
                mainApi.getMovies(Constants.API_KEY, "2016-12-31", "release_date.desc", params.key)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Discover>() {
                            @Override
                            public void onSuccess(Discover discover) {
                                callback.onResult(discover.getResults(),params.key + 1);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                            }
                        })
        );
    }
}
