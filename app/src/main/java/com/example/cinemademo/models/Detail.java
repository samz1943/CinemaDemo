package com.example.cinemademo.models;

public class Detail {

    private String poster;
    private String title;
    private String language;
    private String synopsis;
    private String genres;
    private String duration;

    public Detail(String poster, String title, String language, String synopsis, String genres, String duration) {
        this.poster = poster;
        this.title = title;
        this.language = language;
        this.synopsis = synopsis;
        this.genres = genres;
        this.duration = duration;
    }

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getGenres() {
        return genres;
    }

    public String getDuration() {
        return duration;
    }
}
