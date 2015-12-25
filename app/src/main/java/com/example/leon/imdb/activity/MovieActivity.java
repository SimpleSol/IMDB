package com.example.leon.imdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.imdb.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 25.12.2015.
 */
public class MovieActivity extends AppCompatActivity {

    private static final String SPACE = " ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        ((TextView) findViewById(R.id.m_title)).append(SPACE + intent.getStringExtra(MainActivity.MOVIE_TITLE));
        ((TextView) findViewById(R.id.m_year)).append(SPACE + intent.getStringExtra(MainActivity.YEAR));
        ((TextView) findViewById(R.id.m_director)).append(SPACE + intent.getStringExtra(MainActivity.DIRECTOR));
        ((TextView) findViewById(R.id.m_actors)).append(SPACE + intent.getStringExtra(MainActivity.ACTORS));
        ((TextView) findViewById(R.id.m_imdb_rating)).append(SPACE + intent.getStringExtra(MainActivity.IMDB_RATING));
        ((TextView) findViewById(R.id.m_plot)).append(SPACE + intent.getStringExtra(MainActivity.PLOT));

        Picasso.with(getApplicationContext()).load(intent.getStringExtra(MainActivity.POSTER)).into((ImageView) findViewById(R.id.m_poster));
    }
}
