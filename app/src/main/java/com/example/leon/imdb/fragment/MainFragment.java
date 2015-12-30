package com.example.leon.imdb.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.leon.imdb.R;
import com.example.leon.imdb.activity.MyMoviesActivity;
import com.example.leon.imdb.constants.Constants;
import com.example.leon.imdb.contract.MainContract;
import com.example.leon.imdb.model.Movie;
import com.example.leon.imdb.presenter.ApiPresenter;

/**
 * Created by Leon on 29.12.2015.
 */
public class MainFragment extends Fragment implements View.OnClickListener, MainContract.View {

    // TODO: 30.12.2015 margin top

    private EditText mEditTextTitle;

    private MainContract.UserActionListener mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mEditTextTitle = (EditText) view.findViewById(R.id.movie_title);
        (view.findViewById(R.id.search_button)).setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
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
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getActivity(), MyMoviesActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void putData(Movie movie) {
        final MovieFragment movieFragment = new MovieFragment();
        final Bundle bundle = new Bundle();

        bundle.putString(Constants.MOVIE_TITLE, movie.getTitle());
        bundle.putString(Constants.YEAR, movie.getYear());
        bundle.putString(Constants.DIRECTOR, movie.getDirector());
        bundle.putString(Constants.ACTORS, movie.getActors());
        bundle.putString(Constants.IMDB_RATING, movie.getImdbRating());
        bundle.putString(Constants.PLOT, movie.getPlot());
        bundle.putString(Constants.POSTER, movie.getPoster());

        movieFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .addToBackStack(MovieFragment.class.getName())
                .replace(R.id.frame_main, movieFragment)
                .commit();
        mEditTextTitle.setText("");
    }
}
