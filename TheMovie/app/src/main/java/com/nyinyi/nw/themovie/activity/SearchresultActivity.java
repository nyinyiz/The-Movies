package com.nyinyi.nw.themovie.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.adapter.RecyclerAdapter;
import com.nyinyi.nw.themovie.event.DataEvent;
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl;
import com.nyinyi.nw.themovie.vos.UpcomingVO;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchresultActivity extends AppCompatActivity {


    String search_name;
    List<UpcomingVO> searchdata;
    RecyclerAdapter radapter;

    @BindView(R.id.rv_movie)
    RecyclerView recycler;
    @BindView(R.id.ig_visible)
    ImageView ig_visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        ButterKnife.bind(this, this);
        search_name = getIntent().getStringExtra("search_name");
        getSupportActionBar().setTitle(search_name);


        radapter = new RecyclerAdapter(getApplicationContext());
        setupRecycler();
        RetrofitDataAgentImpl.getInstance().loadsearchMovie(search_name);


    }

    private void setupRecycler() {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recycler.setAdapter(radapter);
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
    public void searchMovieLoadedEvent(DataEvent.SearchMovieLoadEvent event) {
        radapter.setsearchdatalist(event.getSearchVOList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void errorEvent(DataEvent.ErrorLoadedEvent event) {
        Toast.makeText(getApplicationContext(), event.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
