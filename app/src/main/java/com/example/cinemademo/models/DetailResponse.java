package com.example.cinemademo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailResponse {

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("title")
    private String title;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("overview")
    private String overview;

    @SerializedName("genres")
    private List<Genres> genres;

    @SerializedName("runtime")
    private Integer runtime;

    public DetailResponse(String poster_path, String title, String original_language, String overview, List<Genres> genres, Integer runtime) {
        this.poster_path = poster_path;
        this.title = title;
        this.original_language = original_language;
        this.overview = overview;
        this.genres = genres;
        this.runtime = runtime;
    }

    public DetailResponse() {
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
}
