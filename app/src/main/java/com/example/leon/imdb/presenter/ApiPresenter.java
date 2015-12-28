package com.example.leon.imdb.presenter;

import com.example.leon.imdb.AppDelegate;
import com.example.leon.imdb.contract.MainContract;
import com.example.leon.imdb.model.Movie;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Leon on 25.12.2015.
 */
public class ApiPresenter implements MainContract.UserActionListener {

    private final MainContract.View mView;

    public ApiPresenter(MainContract.View view) {
        mView = view;
    }


    @Override
    public void loadMovie(String title) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ImdbApi.ENDPOINT)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ImdbApi imdbApi = retrofit.create(ImdbApi.class);
//        Call<Movie> call = imdbApi.getMovie(title, "full");
        Call<Movie> call = AppDelegate.getImdbApi().getMovie(title, "full");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Response<Movie> response, Retrofit retrofit) {
                Movie movie = response.body();
                mView.putData(movie);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
