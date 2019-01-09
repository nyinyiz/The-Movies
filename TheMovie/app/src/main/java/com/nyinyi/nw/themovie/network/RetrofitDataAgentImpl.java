package com.nyinyi.nw.themovie.network;

import android.util.Log;

import com.nyinyi.nw.themovie.event.DataEvent;
import com.nyinyi.nw.themovie.network.responses.MovieResponse;
import com.nyinyi.nw.themovie.util.MovieConstants;
import com.nyinyi.nw.themovie.vos.MovieVO;
import com.nyinyi.nw.themovie.vos.NowplayingVO;
import com.nyinyi.nw.themovie.vos.PopularVO;
import com.nyinyi.nw.themovie.vos.UpcomingVO;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by User on 6/13/2017.
 */

public class RetrofitDataAgentImpl implements RetrofitDataAgent {

    private static RetrofitDataAgentImpl objInstance;

    private ApiService apiService;
    private ApiService searchapiService;

    //static final String BASE_URL="http://student.newwestgroup.org/nyinyizaw/ci_bookhouse/api/";
    //static final String IMAGE_URL="http://student.newwestgroup.org/nyinyizaw/ci_bookhouse/uploads/";

    private RetrofitDataAgentImpl() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieConstants.BASE_URL)
                .callFactory(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit searchretrofit = new Retrofit.Builder()
                .baseUrl(MovieConstants.SEARCH_BASE_URL)
                .callFactory(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        searchapiService = searchretrofit.create(ApiService.class);

    }

    public static RetrofitDataAgentImpl getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgentImpl();
        }
        return objInstance;
    }


    @Override
    public void loadNowPlayingMovies() {

        Call<MovieResponse<NowplayingVO>> call = apiService.loadNowPlayingMovies(MovieConstants.API_KEY);
        call.enqueue(new Callback<MovieResponse<NowplayingVO>>() {
            @Override
            public void onResponse(Call<MovieResponse<NowplayingVO>> call, Response<MovieResponse<NowplayingVO>> response) {
                MovieResponse<NowplayingVO> movieResponse = response.body();

                if (movieResponse != null) {
                    EventBus.getDefault().post(new DataEvent.NowplayingMovieDataLoadedEvent(movieResponse.getMovieList()));
                } else {
                    EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent("Response is null"));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse<NowplayingVO>> call, Throwable t) {
                EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent(t.getMessage()));
            }
        });


    }

    @Override
    public void loadUpcomingMovies() {

        Call<MovieResponse<UpcomingVO>> call = apiService.loadUpcomingMovies(MovieConstants.API_KEY);
        call.enqueue(new Callback<MovieResponse<UpcomingVO>>() {
            @Override
            public void onResponse(Call<MovieResponse<UpcomingVO>> call, Response<MovieResponse<UpcomingVO>> response) {

                MovieResponse<UpcomingVO> movieResponse = response.body();

                if (movieResponse != null) {

                    EventBus.getDefault().post(new DataEvent.UpcomingMovieDataLoadedEvent(movieResponse.getMovieList()));

                } else {

                    EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent("Response is null"));

                }

            }

            @Override
            public void onFailure(Call<MovieResponse<UpcomingVO>> call, Throwable t) {
                EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent(t.getMessage()));
            }
        });

    }

    @Override
    public void loadTopRatedMovies() {

    }

    @Override
    public void loadPopularMovies() {

        Call<MovieResponse<PopularVO>> call = apiService.loadPopularMovies(MovieConstants.API_KEY);
        call.enqueue(new Callback<MovieResponse<PopularVO>>() {
            @Override
            public void onResponse(Call<MovieResponse<PopularVO>> call, Response<MovieResponse<PopularVO>> response) {

                MovieResponse<PopularVO> movieResponse = response.body();

                if (movieResponse != null) {

                    EventBus.getDefault().post(new DataEvent.PopularMovieDataLoadedEvent(movieResponse.getMovieList()));

                } else {

                    EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent("Response is null"));

                }

            }

            @Override
            public void onFailure(Call<MovieResponse<PopularVO>> call, Throwable t) {
                EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent(t.getMessage()));
            }
        });
    }

    @Override
    public void loadMovieDetail(final String movie_id) {
        Call<MovieVO> call = apiService.loadMovieDetails(movie_id, MovieConstants.API_KEY);
        call.enqueue(new Callback<MovieVO>() {
            @Override
            public void onResponse(Call<MovieVO> call, Response<MovieVO> response) {

                MovieVO movielist = response.body();
                if (movielist != null) {
                    EventBus.getDefault().post(new DataEvent.MovieDetail(movielist));

                } else {
                    EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent("Response is null"));
                }
            }

            @Override
            public void onFailure(Call<MovieVO> call, Throwable t) {
                EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent(t.getMessage()));
            }
        });
    }

    public void loadsearchMovie(final String query) {
        Call<MovieResponse<UpcomingVO>> call = searchapiService.loadsearchMovies(MovieConstants.API_KEY, query);
        call.enqueue(new Callback<MovieResponse<UpcomingVO>>() {
            @Override
            public void onResponse(Call<MovieResponse<UpcomingVO>> call, Response<MovieResponse<UpcomingVO>> response) {
                MovieResponse<UpcomingVO> movieResponse = response.body();

                Log.d("Response1", query);
                Log.d("Response", response.body() + "");
                if (response.body() != null) {
                    EventBus.getDefault().post(new DataEvent.SearchMovieLoadEvent(movieResponse.getMovieList()));

                } else {

                    EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent("Response is null"));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse<UpcomingVO>> call, Throwable t) {

                EventBus.getDefault().post(new DataEvent.ErrorLoadedEvent(t.getMessage()));
            }
        });
    }

}

