package com.example.leon.imdb;

import android.app.Application;

import com.example.leon.imdb.api.ImdbApi;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Leon on 25.12.2015.
 */
public class AppDelegate extends Application {

    //private static AppDelegate instance;

    private static ImdbApi imdbApi;

    public static ImdbApi getImdbApi() {
        return imdbApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //instance = this;
        imdbApi = new Retrofit.Builder()
                .baseUrl(ImdbApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImdbApi.class);

    }
}
