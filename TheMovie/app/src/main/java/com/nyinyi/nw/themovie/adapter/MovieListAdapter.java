package com.nyinyi.nw.themovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.activity.MovieDetailActivity;
import com.nyinyi.nw.themovie.util.MovieConstants;
import com.nyinyi.nw.themovie.vos.UpcomingVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 9/14/2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Context context;
    private List<UpcomingVO> upcomingVOList;

    public MovieListAdapter(Context context) {
        this.context = context;
        upcomingVOList = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.upcomingmovie, parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.bind(upcomingVOList.get(position));
        holder.ivMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("id",upcomingVOList.get(position).getId().toString());
                intent.putExtra("title",upcomingVOList.get(position).getTitle());
                intent.putExtra("vote_average",upcomingVOList.get(position).getVoteAverage().toString());
                intent.putExtra("poster_path",upcomingVOList.get(position).getPosterPath());
                intent.putExtra("original_language",upcomingVOList.get(position).getOriginalLanguage());
                intent.putExtra("original_title",upcomingVOList.get(position).getOriginalTitle());
                intent.putExtra("backdrop_path",upcomingVOList.get(position).getBackdropPath());
                intent.putExtra("adult",upcomingVOList.get(position).getAdult());
                intent.putExtra("overview",upcomingVOList.get(position).getOverview());
                intent.putExtra("release_date",upcomingVOList.get(position).getReleaseDate());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return upcomingVOList.size();
    }

    public void setUpcomingVOList(List<UpcomingVO> upcomingVOList) {
        this.upcomingVOList = upcomingVOList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie)
        ImageView ivMovie;

        private UpcomingVO mData;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(UpcomingVO data) {
            mData = data;

            Glide.with(context)
                    .load(MovieConstants.IMAGE_URL + data.getPosterPath())
                    .placeholder(R.drawable.images)
                    .error(R.drawable.images)
                    .into(ivMovie);


        }

    }
}
