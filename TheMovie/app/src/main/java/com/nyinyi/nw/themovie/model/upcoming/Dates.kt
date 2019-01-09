package com.nyinyi.nw.themovie.model.upcoming

import com.google.gson.annotations.SerializedName

class Dates {

    @SerializedName("maximum")
    var maximum: String? = null
    @SerializedName("minimum")
    var minimum: String? = null

}
