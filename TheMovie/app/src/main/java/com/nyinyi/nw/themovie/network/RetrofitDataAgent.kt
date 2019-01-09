package com.nyinyi.nw.themovie.network

/**
 * Created by User on 9/14/2017.
 */

interface RetrofitDataAgent {

    fun loadNowPlayingMovies()

    fun loadUpcomingMovies()

    fun loadPopularMovies()

    fun loadMovieDetail(movie_id: String)

}
