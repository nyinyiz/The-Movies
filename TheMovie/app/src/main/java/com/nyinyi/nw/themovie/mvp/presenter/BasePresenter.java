package com.nyinyi.nw.themovie.mvp.presenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by User on 9/20/2017.
 */

public abstract class BasePresenter {

    public void onCreate() {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);

    }
}
