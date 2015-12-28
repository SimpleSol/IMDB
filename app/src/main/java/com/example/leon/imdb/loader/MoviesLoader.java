package com.example.leon.imdb.loader;

import android.content.Context;
import android.content.CursorLoader;

import com.example.leon.imdb.model.Movie;

/**
 * Created by Leon on 27.12.2015.
 */
public class MoviesLoader extends CursorLoader {
    // TODO: 27.12.2015 SQLite query
    public MoviesLoader(Context context) {
        super(context, Movie.URI, null, null, null, null);
    }
}
