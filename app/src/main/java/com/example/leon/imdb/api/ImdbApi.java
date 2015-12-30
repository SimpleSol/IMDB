package com.example.leon.imdb.api;

import com.example.leon.imdb.model.Movie;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Leon on 25.12.2015.
 */
public interface ImdbApi {

    String ENDPOINT = "http://www.omdbapi.com";

    @GET("/")
    Call<Movie> getMovie(@Query("t") String movie, @Query("plot") String plot);

}
