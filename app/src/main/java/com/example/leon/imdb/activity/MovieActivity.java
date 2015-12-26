package com.example.leon.imdb.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.imdb.R;
import com.example.leon.imdb.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 25.12.2015.
 */
public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SPACE = " ";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        findViewById(R.id.add_button).setOnClickListener(this);
        fillFields();

    }

    private void fillFields() {
        Intent intent = getIntent();
        ((TextView) findViewById(R.id.m_title)).append(SPACE + intent.getStringExtra(MainActivity.MOVIE_TITLE));
        ((TextView) findViewById(R.id.m_year)).append(SPACE + intent.getStringExtra(MainActivity.YEAR));
        ((TextView) findViewById(R.id.m_director)).append(SPACE + intent.getStringExtra(MainActivity.DIRECTOR));
        ((TextView) findViewById(R.id.m_actors)).append(SPACE + intent.getStringExtra(MainActivity.ACTORS));
        ((TextView) findViewById(R.id.m_imdb_rating)).append(SPACE + intent.getStringExtra(MainActivity.IMDB_RATING));
        ((TextView) findViewById(R.id.m_plot)).append(SPACE + intent.getStringExtra(MainActivity.PLOT));

        Picasso.with(this)
                .load(intent.getStringExtra(MainActivity.POSTER))
                .into((ImageView) findViewById(R.id.m_poster));
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Movie.Columns.TITLE, getIntent().getStringExtra(MainActivity.MOVIE_TITLE));
        contentValues.put(Movie.Columns.POSTER, getIntent().getStringExtra(MainActivity.POSTER));
        getContentResolver().insert(Movie.URI, contentValues);

        final Cursor cursor = getContentResolver().query(Movie.URI, null, null, null, null);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            do {
                String s = cursor.getString(cursor.getColumnIndex(Movie.Columns.TITLE));
                Log.d("database", s);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
