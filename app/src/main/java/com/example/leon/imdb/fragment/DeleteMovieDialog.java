package com.example.leon.imdb.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import com.example.leon.imdb.R;
import com.example.leon.imdb.model.Movie;

/**
 * Created by Leon on 28.12.2015.
 */
public class DeleteMovieDialog extends DialogFragment {

    private int mPosition;

    // TODO: 28.12.2015 setPosition?
    public void setPosition(int position) {
        mPosition = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.are_you_sure)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Cursor cursor = getActivity().getContentResolver().query(Movie.URI, null, null, null, null);
                        if (cursor.moveToPosition(mPosition)) {
                            getActivity().getContentResolver().delete(Movie.URI, Movie.Columns._ID + "=?",
                                    new String[]{cursor.getString(cursor.getColumnIndex(Movie.Columns._ID))});
                        }
                        cursor.close();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();

    }
}
