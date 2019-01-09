package com.nyinyi.nw.themovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.activity.MovieDetailActivity;
import com.nyinyi.nw.themovie.util.MovieConstants;
import com.nyinyi.nw.themovie.vos.PopularVO;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 9/15/2017.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MovieViewHolder> {

    private List<PopularVO> popularVOList;
    private Context context;

    public PopularAdapter(Context context) {
        this.context = context;
        popularVOList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PopularAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.upcomingmovie, parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.MovieViewHolder holder, final int position) {

        holder.bind(popularVOList.get(position));
        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("id", popularVOList.get(position).getId().toString());
            intent.putExtra("title", popularVOList.get(position).getTitle());
            intent.putExtra("vote_average", popularVOList.get(position).getVoteAverage().toString());
            intent.putExtra("poster_path", popularVOList.get(position).getPosterPath());
            intent.putExtra("original_language", popularVOList.get(position).getOriginalLanguage());
            intent.putExtra("original_title", popularVOList.get(position).getOriginalTitle());
            intent.putExtra("backdrop_path", popularVOList.get(position).getBackdropPath());
            intent.putExtra("adult", popularVOList.get(position).getAdult());
            intent.putExtra("overview", popularVOList.get(position).getOverview());
            intent.putExtra("release_date", popularVOList.get(position).getReleaseDate());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return popularVOList.size();
    }

    public void setPopularVOList(List<PopularVO> popularVOList) {
        this.popularVOList = popularVOList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_movie)
        ImageView imageView;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(PopularVO data) {
            Glide.with(context)
                    .load(MovieConstants.IMAGE_URL + data.getPosterPath())
                    .apply(new RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                    .into(imageView);
        }
    }
}
