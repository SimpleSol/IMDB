package com.example.leon.imdb.contract;

import com.example.leon.imdb.model.Movie;

/**
 * Created by Leon on 25.12.2015.
 */
public interface MainContract {

    interface View {
        void initialize(Movie movie);
    }

    interface UserActionListener {
        void loadMovie(String title);
    }

}
