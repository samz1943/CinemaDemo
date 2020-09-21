package com.example.cinemademo.network.main;

import com.example.cinemademo.models.DetailResponse;
import com.example.cinemademo.models.Discover;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {

    @GET("3/discover/movie")
    Single<Discover> getMovies(
            @Query("api_key") String key, @Query("primary_release_date.lte") String date, @Query("sort_by") String sort_by, @Query("page") int page
    );

    @GET("3/movie/{id}")
    Single<DetailResponse> getDetail(
            @Path("id") int id, @Query("api_key") String key
    );


}
