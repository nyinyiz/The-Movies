package com.nyinyi.nw.themovie.network;

import com.nyinyi.nw.themovie.network.responses.MovieResponse;
import com.nyinyi.nw.themovie.util.MovieConstants;
import com.nyinyi.nw.themovie.vos.MovieVO;
import com.nyinyi.nw.themovie.vos.NowplayingVO;
import com.nyinyi.nw.themovie.vos.PopularVO;
import com.nyinyi.nw.themovie.vos.UpcomingVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 6/13/2017.
 */

public interface ApiService {

    @GET("upcoming")
    Call<MovieResponse<UpcomingVO>> loadUpcomingMovies(
            @Query(MovieConstants.PARAM_API_KEY) String apiKey
    );

    @GET("now_playing")
    Call<MovieResponse<NowplayingVO>> loadNowPlayingMovies(
            @Query(MovieConstants.PARAM_API_KEY) String apiKey
    );

    @GET("popular")
    Call<MovieResponse<PopularVO>> loadPopularMovies(
            @Query(MovieConstants.PARAM_API_KEY) String apiKey
    );

    @GET("search/movie")
    Call<MovieResponse<UpcomingVO>> loadsearchMovies(
            @Query(MovieConstants.PARAM_API_KEY) String apiKey,
            @Query(MovieConstants.PARAM_QUERY) String query
    );

    @GET(MovieConstants.API_GET_MOVIE_DETAIL)
    Call<MovieVO> loadMovieDetails(
            @Path(MovieConstants.PARAM_MOVIE_ID) String movieId,
            @Query(MovieConstants.PARAM_API_KEY) String apiKey
    );


}
