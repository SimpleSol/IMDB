package com.example.leon.imdb.activity;

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
import com.example.leon.imdb.listener.RecyclerItemClickListener;
import com.example.leon.imdb.model.Movie;

/**
 * Created by Leon on 26.12.2015.
 */
public class MyMoviesActivity extends AppCompatActivity {

    // TODO: 26.12.2015 cursor close?

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
        Cursor cursor = getContentResolver().query(Movie.URI, null, null, null, null);
        mAdapter = new MyListCursorAdapter(this, cursor);
        mRecyclerView.setAdapter(mAdapter);




        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("listener", String.valueOf(position));
                    }
                })
        );
    }

}

