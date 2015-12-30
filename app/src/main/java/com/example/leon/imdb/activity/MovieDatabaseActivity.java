package com.example.leon.imdb.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.leon.imdb.R;
import com.example.leon.imdb.fragment.MovieDatabaseFragment;

/**
 * Created by Leon on 30.12.2015.
 */
public class MovieDatabaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            MovieDatabaseFragment movieDatabaseFragment = new MovieDatabaseFragment();
            movieDatabaseFragment.setArguments(getIntent().getExtras());
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, movieDatabaseFragment)
                    .commit();
        }
    }
}
