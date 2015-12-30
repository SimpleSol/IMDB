package com.example.leon.imdb.fragment;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.imdb.R;
import com.example.leon.imdb.constants.Constants;
import com.example.leon.imdb.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 29.12.2015.
 */
public class MovieSearchFragment extends Fragment implements View.OnClickListener {

    // TODO: 30.12.2015 same view ids

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_movie_from_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        (view.findViewById(R.id.add_button)).setOnClickListener(this);
        fillFields(view);
    }

    private void fillFields(View view) {
        Bundle bundle = getArguments();
        ((TextView) view.findViewById(R.id.m_title)).append(Constants.SPACE + bundle.getString(Constants.MOVIE_TITLE));
        ((TextView) view.findViewById(R.id.m_year)).append(Constants.SPACE + bundle.getString(Constants.YEAR));
        ((TextView) view.findViewById(R.id.m_director)).append(Constants.SPACE + bundle.getString(Constants.DIRECTOR));
        ((TextView) view.findViewById(R.id.m_actors)).append(Constants.SPACE + bundle.getString(Constants.ACTORS));
        ((TextView) view.findViewById(R.id.m_imdb_rating)).append(Constants.SPACE + bundle.getString(Constants.IMDB_RATING));
        ((TextView) view.findViewById(R.id.m_plot)).append(Constants.SPACE + bundle.getString(Constants.PLOT));

        Picasso.with(getActivity())
                .load(bundle.getString(Constants.POSTER))
                .into((ImageView) view.findViewById(R.id.m_poster));
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = getArguments();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Movie.Columns.TITLE, bundle.getString(Constants.MOVIE_TITLE));
        contentValues.put(Movie.Columns.YEAR, bundle.getString(Constants.YEAR));
        contentValues.put(Movie.Columns.DIRECTOR, bundle.getString(Constants.DIRECTOR));
        contentValues.put(Movie.Columns.ACTORS, bundle.getString(Constants.ACTORS));
        contentValues.put(Movie.Columns.IMDB_RATING, bundle.getString(Constants.IMDB_RATING));
        contentValues.put(Movie.Columns.PLOT, bundle.getString(Constants.PLOT));
        contentValues.put(Movie.Columns.POSTER, bundle.getString(Constants.POSTER));
        getActivity().getContentResolver().insert(Movie.URI, contentValues);
    }
}
