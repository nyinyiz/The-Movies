package com.nyinyi.nw.themovie.network.responses;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.themovie.vos.UpcomingVO;

import java.util.List;

/**
 * Created by User on 9/14/2017.
 */

public class MovieResponse<T> {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<T> movieList;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getMovieList() {
        return movieList;
    }
}
