package com.example.leon.imdb.fragment;

import android.app.Fragment;
import android.database.Cursor;
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
 * Created by Leon on 30.12.2015.
 */
public class MovieDatabaseFragment extends Fragment {

    public static MovieDatabaseFragment newInstance(int index) {
        MovieDatabaseFragment fragment = new MovieDatabaseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return inflater.inflate(R.layout.fmt_movie_from_database, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillFields(getArguments().getInt("index"), view);
    }

    private void fillFields(int position, View view) {
        final Cursor cursor = getActivity().getContentResolver().query(Movie.URI, null, null, null, null);
        // TODO: 27.12.2015 cursor NPE
        if (cursor.moveToPosition(position)) {
            ((TextView) view.findViewById(R.id.m_title)).append(Constants.SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.TITLE)));
            ((TextView) view.findViewById(R.id.m_year)).append(Constants.SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.YEAR)));
            ((TextView) view.findViewById(R.id.m_director)).append(Constants.SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.DIRECTOR)));
            ((TextView) view.findViewById(R.id.m_actors)).append(Constants.SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.ACTORS)));
            ((TextView) view.findViewById(R.id.m_imdb_rating)).append(Constants.SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.IMDB_RATING)));
            ((TextView) view.findViewById(R.id.m_plot)).append(Constants.SPACE + cursor.getString(cursor.getColumnIndex(Movie.Columns.PLOT)));

            Picasso.with(getActivity())
                    .load(cursor.getString(cursor.getColumnIndex(Movie.Columns.POSTER)))
                    .into((ImageView) view.findViewById(R.id.m_poster));
        }
        cursor.close();
    }
}
