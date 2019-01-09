package com.nyinyi.nw.themovie.mvp.presenter;

import android.content.Context;

import com.nyinyi.nw.themovie.event.DataEvent;
import com.nyinyi.nw.themovie.mvp.view.SearchView;
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by User on 9/20/2017.
 */

public class SearchPresenter extends BasePresenter {
    private Context mContext;
    private SearchView mSearchView;

    public SearchPresenter(Context mContext, SearchView mSearchView) {
        this.mContext = mContext;
        this.mSearchView = mSearchView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void searchMovieLoadedEvent(DataEvent.SearchMovieLoadEvent event) {
        mSearchView.onSearchSuccess(event.getSearchVOList());
    }

    public void searchMovie(String query) {
        RetrofitDataAgentImpl.getInstance().loadsearchMovie(query);
    }
}
