package com.nyinyi.nw.themovie.network

import android.util.Log
import com.nyinyi.nw.themovie.event.DataEvent
import com.nyinyi.nw.themovie.network.responses.MovieResponse
import com.nyinyi.nw.themovie.util.MovieConstants
import com.nyinyi.nw.themovie.vos.MovieVO
import com.nyinyi.nw.themovie.vos.NowplayingVO
import com.nyinyi.nw.themovie.vos.PopularVO
import com.nyinyi.nw.themovie.vos.UpcomingVO
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by User on 6/13/2017.
 */

class RetrofitDataAgentImpl private constructor() : RetrofitDataAgent {

    private val apiService: ApiService
    private val searchapiService: ApiService

    init {

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(MovieConstants.BASE_URL)
                .callFactory(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val searchretrofit = Retrofit.Builder()
                .baseUrl(MovieConstants.SEARCH_BASE_URL)
                .callFactory(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit.create(ApiService::class.java)
        searchapiService = searchretrofit.create(ApiService::class.java)

    }


    override fun loadNowPlayingMovies() {

        val call = apiService.loadNowPlayingMovies(MovieConstants.API_KEY)
        call.enqueue(object : Callback<MovieResponse<NowplayingVO>> {
            override fun onResponse(call: Call<MovieResponse<NowplayingVO>>, response: Response<MovieResponse<NowplayingVO>>) {
                val movieResponse = response.body()

                if (movieResponse != null) {
                    EventBus.getDefault().post(DataEvent.NowplayingMovieDataLoadedEvent(movieResponse.movieList!!))
                } else {
                    EventBus.getDefault().post(DataEvent.ErrorLoadedEvent("Response is null"))
                }
            }

            override fun onFailure(call: Call<MovieResponse<NowplayingVO>>, t: Throwable) {
                EventBus.getDefault().post(DataEvent.ErrorLoadedEvent(t.message!!))
            }
        })


    }

    override fun loadUpcomingMovies() {

        val call = apiService.loadUpcomingMovies(MovieConstants.API_KEY)
        call.enqueue(object : Callback<MovieResponse<UpcomingVO>> {
            override fun onResponse(call: Call<MovieResponse<UpcomingVO>>, response: Response<MovieResponse<UpcomingVO>>) {

                val movieResponse = response.body()

                if (movieResponse != null) {

                    EventBus.getDefault().post(DataEvent.UpcomingMovieDataLoadedEvent(movieResponse.movieList!!))

                } else {

                    EventBus.getDefault().post(DataEvent.ErrorLoadedEvent("Response is null"))

                }

            }

            override fun onFailure(call: Call<MovieResponse<UpcomingVO>>, t: Throwable) {
                EventBus.getDefault().post(DataEvent.ErrorLoadedEvent(t.message!!))
            }
        })

    }

    override fun loadPopularMovies() {

        val call = apiService.loadPopularMovies(MovieConstants.API_KEY)
        call.enqueue(object : Callback<MovieResponse<PopularVO>> {
            override fun onResponse(call: Call<MovieResponse<PopularVO>>, response: Response<MovieResponse<PopularVO>>) {

                val movieResponse = response.body()

                if (movieResponse != null) {

                    EventBus.getDefault().post(DataEvent.PopularMovieDataLoadedEvent(movieResponse.movieList!!))

                } else {

                    EventBus.getDefault().post(DataEvent.ErrorLoadedEvent("Response is null"))

                }

            }

            override fun onFailure(call: Call<MovieResponse<PopularVO>>, t: Throwable) {
                EventBus.getDefault().post(DataEvent.ErrorLoadedEvent(t.message!!))
            }
        })
    }

    override fun loadMovieDetail(movie_id: String) {
        val call = apiService.loadMovieDetails(movie_id, MovieConstants.API_KEY)
        call.enqueue(object : Callback<MovieVO> {
            override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {

                val movielist = response.body()
                if (movielist != null) {
                    EventBus.getDefault().post(DataEvent.MovieDetail(movielist))

                } else {
                    EventBus.getDefault().post(DataEvent.ErrorLoadedEvent("Response is null"))
                }
            }

            override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                EventBus.getDefault().post(DataEvent.ErrorLoadedEvent(t.message!!))
            }
        })
    }

    fun loadsearchMovie(query: String) {
        val call = searchapiService.loadsearchMovies(MovieConstants.API_KEY, query)
        call.enqueue(object : Callback<MovieResponse<UpcomingVO>> {
            override fun onResponse(call: Call<MovieResponse<UpcomingVO>>, response: Response<MovieResponse<UpcomingVO>>) {
                val movieResponse = response.body()

                Log.d("Response1", query)
                Log.d("Response", response.body()!!.toString() + "")
                if (response.body() != null) {
                    if (movieResponse != null) {
                        EventBus.getDefault().post(DataEvent.SearchMovieLoadEvent(movieResponse.movieList!!))
                    }

                } else {

                    EventBus.getDefault().post(DataEvent.ErrorLoadedEvent("Response is null"))
                }
            }

            override fun onFailure(call: Call<MovieResponse<UpcomingVO>>, t: Throwable) {

                EventBus.getDefault().post(DataEvent.ErrorLoadedEvent(t.message!!))
            }
        })
    }

    companion object {

        private var objInstance: RetrofitDataAgentImpl? = null

        val instance: RetrofitDataAgentImpl
            get() {
                if (objInstance == null) {
                    objInstance = RetrofitDataAgentImpl()
                }
                return objInstance as RetrofitDataAgentImpl
            }
    }

}

