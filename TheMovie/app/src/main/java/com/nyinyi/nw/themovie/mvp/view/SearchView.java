package com.nyinyi.nw.themovie.mvp.view;

import com.nyinyi.nw.themovie.vos.UpcomingVO;

import java.util.List;

/**
 * Created by User on 9/20/2017.
 */

public interface SearchView {
    void onSearchSuccess(List<UpcomingVO> movieList);
}
