package com.nyinyi.nw.themovie.mvp.view

import com.nyinyi.nw.themovie.vos.UpcomingVO

/**
 * Created by User on 9/20/2017.
 */

interface SearchView {
    fun onSearchSuccess(movieList: List<UpcomingVO>)
}
