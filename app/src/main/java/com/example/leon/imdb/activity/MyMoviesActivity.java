package com.example.leon.imdb.activity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.leon.imdb.R;
import com.example.leon.imdb.adapter.CursorRecyclerViewAdapter;
import com.example.leon.imdb.adapter.MyListCursorAdapter;
import com.example.leon.imdb.fragment.DeleteMovieDialog;
import com.example.leon.imdb.listener.RecyclerItemClickListener;
import com.example.leon.imdb.loader.MoviesLoader;
import com.example.leon.imdb.model.Movie;

/**
 * Created by Leon on 26.12.2015.
 */
public class MyMoviesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        RecyclerItemClickListener.OnItemClickListener {


    private CursorRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getLoaderManager().initLoader(R.id.movies_loader, Bundle.EMPTY, this);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == R.id.movies_loader) {
            return new MoviesLoader(getApplicationContext());
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // TODO: 27.12.2015 onLoadFinished() initializing adapter ??
        mAdapter = new MyListCursorAdapter(this, cursor);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (mAdapter != null) {
            mAdapter.changeCursor(null);
        }
    }

    @Override
    public void onItemClick(View childView, int position) {
//        final Cursor cursor = getContentResolver().query(Movie.URI, null, null, null, null);
//
//        Intent intent = new Intent(this, MovieActivity.class);
//        intent.putExtra("POSITION", position);
//        startActivity(intent);
//        cursor.close();
    }

    @Override
    public void onItemLongPress(View childView, int position) {
        DeleteMovieDialog dialog = new DeleteMovieDialog();
        dialog.setPosition(position);
        dialog.show(getFragmentManager(), DeleteMovieDialog.class.getName());
    }


}

