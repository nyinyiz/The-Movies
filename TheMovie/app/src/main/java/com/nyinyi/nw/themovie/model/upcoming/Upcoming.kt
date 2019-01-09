package com.nyinyi.nw.themovie.model.upcoming

import com.google.gson.annotations.SerializedName

class Upcoming {

    @SerializedName("dates")
    var dates: Dates? = null
    @SerializedName("page")
    var page: Long? = null
    @SerializedName("results")
    var results: List<Result>? = null
    @SerializedName("total_pages")
    var totalPages: Long? = null
    @SerializedName("total_results")
    var totalResults: Long? = null

}
