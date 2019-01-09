package com.nyinyi.nw.themovie.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.event.DataEvent;
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl;
import com.nyinyi.nw.themovie.util.MovieConstants;
import com.nyinyi.nw.themovie.vos.MovieVO;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_backdrop)
    ImageView cover;
    @BindView(R.id.iv_poster)
    ImageView poster;
    @BindView(R.id.movie_title)
    TextView movie_title;
    @BindView(R.id.adult)
    CardView c_adult;
    @BindView(R.id.overview)
    TextView story;
    @BindView(R.id.rating)
    RatingBar rate;
    @BindView(R.id.or_lan)
    TextView ori_lan;
    @BindView(R.id.or_title)
    TextView ori_title;
    @BindView(R.id.tx_release)
    TextView tx_release;

    @BindView(R.id.cover_picture)
    ImageView cover_picture;

    MovieVO movieVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        String id = getIntent().getStringExtra("id");

        RetrofitDataAgentImpl.getInstance().loadMovieDetail(id);

        String vote_count = getIntent().getStringExtra("vote_count");
        String vote_average = getIntent().getStringExtra("vote_average");
        final String title = getIntent().getStringExtra("title");
        String poster_path = getIntent().getStringExtra("poster_path");
        String original_language = getIntent().getStringExtra("original_language");
        String original_title = getIntent().getStringExtra("original_title");
        String backdrop_path = getIntent().getStringExtra("backdrop_path");
        Boolean adult = getIntent().getBooleanExtra("adult", false);
        String overview = getIntent().getStringExtra("overview");
        String release_date = getIntent().getStringExtra("release_date");

        Toast.makeText(getApplicationContext(), "ID " + id, Toast.LENGTH_LONG).show();

        actionBar.setTitle(title);
        collapsingToolbarLayout.setTitleEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            collapsingToolbarLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    collapsingToolbarLayout.setTitleEnabled(true);
                }
            });
        }

        ButterKnife.bind(this, this);


//        Toast.makeText(getApplicationContext(),movieVO.getTitle(),Toast.LENGTH_LONG).show();

        rate.setEnabled(false);

        float rating = Float.parseFloat(vote_average) / 2;

        Glide.with(getApplicationContext())
                .load(MovieConstants.IMAGE_URL + backdrop_path)
                .apply(new RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                .into(cover)
        ;

        Glide.with(getApplicationContext())
                .load(MovieConstants.IMAGE_URL + poster_path)
                .apply(new RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                .into(poster)
        ;

        movie_title.setText(title);

        if (adult) {
            c_adult.setVisibility(View.VISIBLE);
        } else {
            c_adult.setVisibility(View.GONE);
        }

        rate.setRating(rating);

        story.setText(overview);


        ori_title.setText(original_title);
        if (original_language.equals("en")) {
            ori_lan.setText("English");
        } else {
            ori_lan.setText(original_language);
        }
        tx_release.setText(release_date);

        Glide.with(getApplicationContext())
                .load(MovieConstants.IMAGE_URL + backdrop_path)
                .apply(new RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                .into(cover_picture)
        ;
    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void movieDetailLoadedEvent(DataEvent.MovieDetail event) {
        movieVO = event.getMoviedetailList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void errorEvent(DataEvent.ErrorLoadedEvent event) {
        Toast.makeText(getApplicationContext(), event.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
