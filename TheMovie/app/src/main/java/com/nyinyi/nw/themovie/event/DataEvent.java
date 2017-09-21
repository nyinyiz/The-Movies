package com.nyinyi.nw.themovie.event;

import com.nyinyi.nw.themovie.model.upcoming.Upcoming;
import com.nyinyi.nw.themovie.vos.MovieVO;
import com.nyinyi.nw.themovie.vos.NowplayingVO;
import com.nyinyi.nw.themovie.vos.PopularVO;
import com.nyinyi.nw.themovie.vos.UpcomingVO;

import java.util.List;

/**
 * Created by User on 9/14/2017.
 */

public class DataEvent {

    public static class UpcomingMovieDataLoadedEvent {

        private List<UpcomingVO> upcomingVOList;

        public UpcomingMovieDataLoadedEvent(List<UpcomingVO> upcomingVOList) {
            this.upcomingVOList = upcomingVOList;
        }

        public List<UpcomingVO> getUpcomingVOList() {
            return upcomingVOList;
        }
    }

    public static class NowplayingMovieDataLoadedEvent {

        private List<NowplayingVO> nowplayingVOList;

        public NowplayingMovieDataLoadedEvent(List<NowplayingVO> nowplayingVOList) {
            this.nowplayingVOList = nowplayingVOList;
        }

        public List<NowplayingVO> getNowplayingVOList() {
            return nowplayingVOList;
        }
    }

    public static class PopularMovieDataLoadedEvent{
        private List<PopularVO> popularVOList;

        public PopularMovieDataLoadedEvent(List<PopularVO> popularVOList) {
            this.popularVOList = popularVOList;
        }

        public List<PopularVO> getPopularVOList() {
            return popularVOList;
        }
    }

    public static class SearchMovieLoadEvent{
        private List<UpcomingVO> searchVOList;

        public SearchMovieLoadEvent(List<UpcomingVO> searchVOList) {
            this.searchVOList = searchVOList;
        }

        public List<UpcomingVO> getSearchVOList() {
            return searchVOList;
        }
    }

    public static class MovieDetail{
        private MovieVO moviedetailList;

        public MovieDetail(MovieVO moviedetailList) {
            this.moviedetailList = moviedetailList;
        }

        public MovieVO getMoviedetailList() {
            return moviedetailList;
        }
    }

    public static class ErrorLoadedEvent {

        private String message;

        public ErrorLoadedEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }


}
