package com.nyinyi.nw.themovie.mvp.presenter

import android.content.Context

import com.nyinyi.nw.themovie.event.DataEvent
import com.nyinyi.nw.themovie.mvp.view.SearchView
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl

import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by User on 9/20/2017.
 */

class SearchPresenter(private val mContext: Context, private val mSearchView: SearchView) : BasePresenter() {

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun searchMovieLoadedEvent(event: DataEvent.SearchMovieLoadEvent) {
        mSearchView.onSearchSuccess(event.searchVOList)
    }

    fun searchMovie(query: String) {
        RetrofitDataAgentImpl.instance.loadsearchMovie(query)
    }
}
