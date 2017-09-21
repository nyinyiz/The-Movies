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
import com.nyinyi.nw.themovie.vos.NowplayingVO;
import com.nyinyi.nw.themovie.vos.UpcomingVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 9/14/2017.
 */

public class NowplayingAdapter extends RecyclerView.Adapter<NowplayingAdapter.MovieViewHolder> {

    Context context;
    private List<NowplayingVO> nowplayingVOList;

    public NowplayingAdapter(Context context) {
        this.context = context;
        nowplayingVOList = new ArrayList<>();

    }

    @Override
    public NowplayingAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.upcomingmovie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NowplayingAdapter.MovieViewHolder holder, final int position) {

        holder.bind(nowplayingVOList.get(position));
        holder.ivMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("id",nowplayingVOList.get(position).getId().toString());
                intent.putExtra("title",nowplayingVOList.get(position).getTitle());
                intent.putExtra("vote_average",nowplayingVOList.get(position).getVoteAverage().toString());
                intent.putExtra("poster_path",nowplayingVOList.get(position).getPosterPath());
                intent.putExtra("original_language",nowplayingVOList.get(position).getOriginalLanguage());
                intent.putExtra("original_title",nowplayingVOList.get(position).getOriginalTitle());
                intent.putExtra("backdrop_path",nowplayingVOList.get(position).getBackdropPath());
                intent.putExtra("adult",nowplayingVOList.get(position).getAdult());
                intent.putExtra("overview",nowplayingVOList.get(position).getOverview());
                intent.putExtra("release_date",nowplayingVOList.get(position).getReleaseDate());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return nowplayingVOList.size();
    }

    public void setNowplayingVOList(List<NowplayingVO> nowplayingVOList)
    {
        this.nowplayingVOList = nowplayingVOList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie)
        ImageView ivMovie;

        private NowplayingVO mData;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(NowplayingVO data) {
            mData = data;

            Glide.with(context)
                    .load(MovieConstants.IMAGE_URL + data.getPosterPath())
                    .placeholder(R.drawable.images)
                    .error(R.drawable.images)
                    .into(ivMovie);


        }

    }
}
