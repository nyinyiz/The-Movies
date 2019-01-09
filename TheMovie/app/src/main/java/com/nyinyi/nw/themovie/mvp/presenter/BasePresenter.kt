package com.nyinyi.nw.themovie.mvp.presenter

import org.greenrobot.eventbus.EventBus

/**
 * Created by User on 9/20/2017.
 */

abstract class BasePresenter {

    fun onCreate() {
        val eventBus = EventBus.getDefault()
        if (!eventBus.isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

    }

    fun onDestroy() {
        EventBus.getDefault().unregister(this)

    }
}
