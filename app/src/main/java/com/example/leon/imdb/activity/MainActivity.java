package com.example.leon.imdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leon.imdb.BuildConfig;
import com.example.leon.imdb.R;
import com.example.leon.imdb.contract.MainContract;
import com.example.leon.imdb.model.Movie;
import com.example.leon.imdb.presenter.ApiPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.View {

    public static final String MOVIE_TITLE = BuildConfig.APPLICATION_ID + ".MOVIE_TITLE";
    public static final String YEAR = BuildConfig.APPLICATION_ID + ".YEAR";
    public static final String DIRECTOR = BuildConfig.APPLICATION_ID + ".DIRECTOR";
    public static final String ACTORS = BuildConfig.APPLICATION_ID + ".ACTORS";
    public static final String IMDB_RATING = BuildConfig.APPLICATION_ID + ".IMDB_RATING";
    public static final String PLOT = BuildConfig.APPLICATION_ID + ".PLOT";
    public static final String POSTER = BuildConfig.APPLICATION_ID + ".POSTER";

    private EditText mEditTextTitle;

    private MainContract.UserActionListener mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditTextTitle = (EditText) findViewById(R.id.movie_title);
        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        mPresenter = new ApiPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                mPresenter.loadMovie(mEditTextTitle.getText().toString());
                break;
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }
    }

    @Override
    public void initialize(Movie movie) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(MOVIE_TITLE, movie.getTitle());
        intent.putExtra(YEAR, movie.getYear());
        intent.putExtra(DIRECTOR, movie.getDirector());
        intent.putExtra(ACTORS, movie.getActors());
        intent.putExtra(IMDB_RATING, movie.getImdbRating());
        intent.putExtra(PLOT, movie.getPlot());
        intent.putExtra(POSTER, movie.getPoster());
        mEditTextTitle.setText("");
        startActivity(intent);
    }
}
