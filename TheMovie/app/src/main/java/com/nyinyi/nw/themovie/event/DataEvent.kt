package com.nyinyi.nw.themovie.event

import com.nyinyi.nw.themovie.vos.MovieVO
import com.nyinyi.nw.themovie.vos.NowplayingVO
import com.nyinyi.nw.themovie.vos.PopularVO
import com.nyinyi.nw.themovie.vos.UpcomingVO

/**
 * Created by User on 9/14/2017.
 */

class DataEvent {

    class UpcomingMovieDataLoadedEvent(val upcomingVOList: List<UpcomingVO>)

    class NowplayingMovieDataLoadedEvent(val nowplayingVOList: List<NowplayingVO>)

    class PopularMovieDataLoadedEvent(val popularVOList: List<PopularVO>)

    class SearchMovieLoadEvent(val searchVOList: List<UpcomingVO>)

    class MovieDetail(val moviedetailList: MovieVO)

    class ErrorLoadedEvent(val message: String)


}
