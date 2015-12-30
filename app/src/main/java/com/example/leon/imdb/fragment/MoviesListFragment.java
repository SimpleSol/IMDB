package com.example.leon.imdb.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.imdb.R;
import com.example.leon.imdb.activity.MovieDatabaseActivity;
import com.example.leon.imdb.adapter.CursorRecyclerViewAdapter;
import com.example.leon.imdb.adapter.MyListCursorAdapter;
import com.example.leon.imdb.listener.RecyclerItemClickListener;
import com.example.leon.imdb.loader.MoviesLoader;

/**
 * Created by Leon on 30.12.2015.
 */
public class MoviesListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
        RecyclerItemClickListener.OnItemClickListener {

    boolean mDualPane;
    int mCurCheckPosition;

    private CursorRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
    }

    // TODO: 30.12.2015 onActivityCreated()/onViewCreated()
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getLoaderManager().initLoader(R.id.movies_loader, Bundle.EMPTY, this);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));


        View movieLandscape = getActivity().findViewById(R.id.movie_landscape);
        mDualPane = movieLandscape != null && movieLandscape.getVisibility() == View.VISIBLE;
        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
        if (mDualPane) {
            showMovie(mCurCheckPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onItemClick(View childView, int position) {
        showMovie(position);
    }

    void showMovie(int index) {
        mCurCheckPosition = index;

        if (mDualPane) {
            MovieDatabaseFragment movieDatabaseFragment = (MovieDatabaseFragment)
                    getFragmentManager()
                    .findFragmentById(R.id.movie_landscape);

            if (movieDatabaseFragment == null || movieDatabaseFragment.getShownIndex() != index) {
                movieDatabaseFragment = MovieDatabaseFragment.newInstance(index);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.movie_landscape, movieDatabaseFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        } else {
            Intent intent = new Intent(getActivity(), MovieDatabaseActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == R.id.movies_loader) {
            return new MoviesLoader(getActivity().getApplicationContext());
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // TODO: 27.12.2015 onLoadFinished() initializing adapter ??
        if (R.id.movies_loader == loader.getId()) {
            mAdapter = new MyListCursorAdapter(getActivity(), cursor);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (mAdapter != null) {
            mAdapter.changeCursor(null);
        }
    }

    @Override
    public void onItemLongPress(View childView, int position) {
        DeleteMovieDialog dialog = new DeleteMovieDialog();
        dialog.setPosition(position);
        dialog.show(getFragmentManager(), DeleteMovieDialog.class.getName());
    }
}
