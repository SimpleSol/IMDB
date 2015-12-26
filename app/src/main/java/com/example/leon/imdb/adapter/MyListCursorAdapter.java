package com.example.leon.imdb.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.imdb.R;
import com.example.leon.imdb.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 26.12.2015.
 */
public class MyListCursorAdapter extends CursorRecyclerViewAdapter<MyListCursorAdapter.ViewHolder> {

    private final Context mContext;

    public MyListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.li_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        Movie movie = Movie.fromCursor(cursor);
        viewHolder.mTextView.setText(movie.getTitle());
        Picasso.with(mContext)
                .load(movie.getPoster())
                .into(viewHolder.mImageView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.card_title);
            mImageView = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }
}
