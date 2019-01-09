package com.nyinyi.nw.themovie.network.responses

import com.google.gson.annotations.SerializedName

/**
 * Created by User on 9/14/2017.
 */

class MovieResponse<T> {

    @SerializedName("page")
    val page: Int = 0

    @SerializedName("total_results")
    val totalResults: Int = 0

    @SerializedName("total_pages")
    val totalPages: Int = 0

    @SerializedName("results")
    val movieList: List<T>? = null
}
