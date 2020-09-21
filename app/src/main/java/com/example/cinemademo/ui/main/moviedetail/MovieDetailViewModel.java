package com.example.cinemademo.ui.main.moviedetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemademo.models.Detail;
import com.example.cinemademo.models.DetailResponse;
import com.example.cinemademo.network.main.MainApi;
import com.example.cinemademo.utils.Constants;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailViewModel extends ViewModel {

    private static final String TAG = "MovieDetailViewModel";

    private MainApi mainApi;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Detail> detailMutableLiveData = new MutableLiveData<>();

    private String poster;
    private String title;
    private String synopsis;
    private String genres = "";
    private String language;
    private String duration;

    @Inject
    public MovieDetailViewModel(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    public MutableLiveData<Detail> getDetailMutableLiveData() {
        return detailMutableLiveData;
    }

    /**
     * get movie detail based on movie id
     */
    public void getMovieDetail(int id) {
        compositeDisposable.add(mainApi.getDetail(id, Constants.API_KEY)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<DetailResponse>() {

            @Override
            public void onSuccess(DetailResponse detailResponse) {
                poster = detailResponse.getPoster_path();
                title = detailResponse.getTitle();
                synopsis = detailResponse.getOverview();
                language = detailResponse.getOriginal_language().toUpperCase();
                duration = detailResponse.getRuntime().toString() + " mins";

                for (int x = 0; x< detailResponse.getGenres().size(); x++) {
                    genres += detailResponse.getGenres().get(x).getName();
                    if (x != detailResponse.getGenres().size() - 1)
                        genres += ", ";
                }
                Detail detail = new Detail(poster, title, language, synopsis, genres, duration);
                detailMutableLiveData.postValue(detail);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
