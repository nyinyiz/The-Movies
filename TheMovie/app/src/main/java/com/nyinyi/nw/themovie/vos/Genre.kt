package com.nyinyi.nw.themovie.vos

import com.google.gson.annotations.SerializedName

class Genre {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null

}
