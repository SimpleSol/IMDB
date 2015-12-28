package com.example.leon.imdb.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.imdb.R;
import com.example.leon.imdb.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 25.12.2015.
 */
public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO: 27.12.2015 parent activity/back stack

    private static final String SPACE = " ";

    private Button mButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mButton = (Button) findViewById(R.id.add_button);
        mButton.setOnClickListener(this);
        if (getIntent().hasExtra("POSITION")) {
            mButton.setVisibility(View.GONE);
            fillFields(getIntent().getIntExtra("POSITION", 0)); // TODO: 27.12.2015 default position
        } else {
            fillFields();
        }

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

    private void fillFields(int position) {
        final Cursor cursor = getContentResolver().query(Movie.URI, null, null, null, null);
        // TODO: 27.12.2015 cursor NPE
        if (cursor.moveToPosition(position)) {
            ((TextView) findViewById(R.id.m_title)).append(SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.TITLE)));
            ((TextView) findViewById(R.id.m_year)).append(SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.YEAR)));
            ((TextView) findViewById(R.id.m_director)).append(SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.DIRECTOR)));
            ((TextView) findViewById(R.id.m_actors)).append(SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.ACTORS)));
            ((TextView) findViewById(R.id.m_imdb_rating)).append(SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.IMDB_RATING)));
            ((TextView) findViewById(R.id.m_plot)).append(SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.PLOT)));

            Picasso.with(this)
                    .load(cursor.getString(cursor.getColumnIndex(Movie.Columns.POSTER)))
                    .into((ImageView) findViewById(R.id.m_poster));
        }
        cursor.close();
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Movie.Columns.TITLE, getIntent().getStringExtra(MainActivity.MOVIE_TITLE));
        contentValues.put(Movie.Columns.YEAR, getIntent().getStringExtra(MainActivity.YEAR));
        contentValues.put(Movie.Columns.DIRECTOR, getIntent().getStringExtra(MainActivity.DIRECTOR));
        contentValues.put(Movie.Columns.ACTORS, getIntent().getStringExtra(MainActivity.ACTORS));
        contentValues.put(Movie.Columns.IMDB_RATING, getIntent().getStringExtra(MainActivity.IMDB_RATING));
        contentValues.put(Movie.Columns.PLOT, getIntent().getStringExtra(MainActivity.PLOT));
        contentValues.put(Movie.Columns.POSTER, getIntent().getStringExtra(MainActivity.POSTER));
        getContentResolver().insert(Movie.URI, contentValues);

    }
}
