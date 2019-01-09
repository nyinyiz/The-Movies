package com.nyinyi.nw.themovie.vos

import com.google.gson.annotations.SerializedName

/**
 * Created by User on 9/14/2017.
 */

class NowplayingVO {

    @SerializedName("adult")
    var adult: Boolean? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @SerializedName("genre_ids")
    var genreIds: List<Long>? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("original_language")
    var originalLanguage: String? = null
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("popularity")
    var popularity: Double? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("video")
    var video: Boolean? = null
    @SerializedName("vote_average")
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    var voteCount: Long? = null

}
