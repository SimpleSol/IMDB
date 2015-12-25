package com.example.leon.imdb.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Leon on 25.12.2015.
 */
public class Movie {

    @SerializedName("Title")
    private String mTitle;

    @SerializedName("Year")
    private String mYear;

    @SerializedName("Director")
    private String mDirector;

    @SerializedName("Actors")
    private String mActors;

    @SerializedName("imdbRating")
    private String mImdbRating;

    @SerializedName("Plot")
    private String mPlot;

    @SerializedName("Poster")
    private String mPoster;

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public String getActors() {
        return mActors;
    }

    public void setActors(String actors) {
        mActors = actors;
    }

    public String getImdbRating() {
        return mImdbRating;
    }

    public void setImdbRating(String imdbRating) {
        mImdbRating = imdbRating;
    }

    public String getPlot() {
        return mPlot;
    }

    public void setPlot(String plot) {
        mPlot = plot;
    }
}
