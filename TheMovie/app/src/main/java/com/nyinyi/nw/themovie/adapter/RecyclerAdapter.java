package com.nyinyi.nw.themovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.activity.MovieDetailActivity;
import com.nyinyi.nw.themovie.util.MovieConstants;
import com.nyinyi.nw.themovie.vos.UpcomingVO;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 9/15/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder> {

    private List<UpcomingVO> upcomingVOList;
    private Context context;

    public RecyclerAdapter(Context context) {
        this.context = context;
        upcomingVOList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MovieViewHolder holder, final int position) {

        if (upcomingVOList.size() != 0) {
            holder.bind(upcomingVOList.get(position));
            holder.ivMovie.setOnClickListener(v -> {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", upcomingVOList.get(position).getId().toString());
                intent.putExtra("title", upcomingVOList.get(position).getTitle());
                intent.putExtra("vote_average", upcomingVOList.get(position).getVoteAverage().toString());
                intent.putExtra("poster_path", upcomingVOList.get(position).getPosterPath());
                intent.putExtra("original_language", upcomingVOList.get(position).getOriginalLanguage());
                intent.putExtra("original_title", upcomingVOList.get(position).getOriginalTitle());
                intent.putExtra("backdrop_path", upcomingVOList.get(position).getBackdropPath());
                intent.putExtra("adult", upcomingVOList.get(position).getAdult());
                intent.putExtra("overview", upcomingVOList.get(position).getOverview());
                intent.putExtra("release_date", upcomingVOList.get(position).getReleaseDate());
                context.startActivity(intent);

            });

        } else {
            Toast.makeText(context, "NO result found " + getItemCount(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return upcomingVOList.size();
    }

    public void setsearchdatalist(List<UpcomingVO> upcomingVOList) {
        this.upcomingVOList = upcomingVOList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_movie)
        ImageView ivMovie;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(UpcomingVO data) {

            Glide.with(context)
                    .load(MovieConstants.IMAGE_URL + data.getPosterPath())
                    .apply(new RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                    .into(ivMovie);


        }
    }
}
