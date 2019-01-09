package com.nyinyi.nw.themovie.network;

/**
 * Created by User on 9/14/2017.
 */

public interface RetrofitDataAgent {

    void loadNowPlayingMovies();

    void loadUpcomingMovies();

    void loadPopularMovies();

    void loadMovieDetail(String movie_id);

}
