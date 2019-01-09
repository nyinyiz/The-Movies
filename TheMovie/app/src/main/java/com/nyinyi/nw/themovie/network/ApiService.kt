package com.nyinyi.nw.themovie.network

import com.nyinyi.nw.themovie.network.responses.MovieResponse
import com.nyinyi.nw.themovie.util.MovieConstants.API_GET_MOVIE_DETAIL
import com.nyinyi.nw.themovie.util.MovieConstants.PARAM_API_KEY
import com.nyinyi.nw.themovie.util.MovieConstants.PARAM_MOVIE_ID
import com.nyinyi.nw.themovie.util.MovieConstants.PARAM_QUERY
import com.nyinyi.nw.themovie.vos.MovieVO
import com.nyinyi.nw.themovie.vos.NowplayingVO
import com.nyinyi.nw.themovie.vos.PopularVO
import com.nyinyi.nw.themovie.vos.UpcomingVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by User on 6/13/2017.
 */

interface ApiService {

    @GET("upcoming")
    fun loadUpcomingMovies(
            @Query(PARAM_API_KEY) apiKey: String
    ): Call<MovieResponse<UpcomingVO>>

    @GET("now_playing")
    fun loadNowPlayingMovies(
            @Query(PARAM_API_KEY) apiKey: String
    ): Call<MovieResponse<NowplayingVO>>

    @GET("popular")
    fun loadPopularMovies(
            @Query(PARAM_API_KEY) apiKey: String
    ): Call<MovieResponse<PopularVO>>

    @GET("search/movie")
    fun loadsearchMovies(
            @Query(PARAM_API_KEY) apiKey: String,
            @Query(PARAM_QUERY) query: String
    ): Call<MovieResponse<UpcomingVO>>

    @GET(API_GET_MOVIE_DETAIL)
    fun loadMovieDetails(
            @Path(PARAM_MOVIE_ID) movieId: String,
            @Query(PARAM_API_KEY) apiKey: String
    ): Call<MovieVO>


}
