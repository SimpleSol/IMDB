package com.example.leon.imdb.sqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.leon.imdb.BuildConfig;
import com.example.leon.imdb.model.Movie;

/**
 * Created by Leon on 26.12.2015.
 */
public class SQLiteProvider extends ContentProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "movies.db";

    private SQLiteOpenHelper mHelper;

    @Override
    public boolean onCreate() {
        mHelper = new SQLiteHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] columns, String where, String[] whereArgs, String orderBy) {
        final String tableName = uri.getLastPathSegment();
        final Cursor cursor = mHelper.getReadableDatabase().query(tableName, columns, where, whereArgs, null, null, orderBy);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final String tableName = uri.getLastPathSegment();
        final long lastRowId = mHelper.getWritableDatabase().insert(tableName, BaseColumns._ID, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, lastRowId);
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        final String tableName = uri.getLastPathSegment();
        final int affectedRows = mHelper.getWritableDatabase().delete(tableName, where, whereArgs);
        if (affectedRows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return affectedRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        final String tableName = uri.getLastPathSegment();
        final int affectedRows = mHelper.getWritableDatabase().update(tableName, values, where, whereArgs);
        if (affectedRows > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return affectedRows;
    }

    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/" + uri.getLastPathSegment();
    }

    private static class SQLiteHelper extends SQLiteOpenHelper {


        public SQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS " + Movie.TABLE +
                            "(_id INTEGER PRIMARY KEY, " +
                            "title TEXT, " +
                            "year TEXT, " +
                            "director TEXT, " +
                            "actors TEXT, " +
                            "imdb_rating TEXT, " +
                            "plot TEXT, " +
                            "poster TEXT, " +
                            "UNIQUE(title) ON CONFLICT IGNORE);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Movie.TABLE + ";");
            onCreate(db);
        }
    }
}
