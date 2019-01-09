package com.nyinyi.nw.themovie.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.adapter.NowplayingAdapter;
import com.nyinyi.nw.themovie.event.DataEvent;
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowplayingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout refresh;

    @BindView(R.id.rv_nowplaying_movie)
    RecyclerView recyclerView;

    private NowplayingAdapter nadapter;


    public NowplayingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nadapter = new NowplayingAdapter(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nowplaying, container, false);
        ButterKnife.bind(this, view);
        refresh.setVisibility(View.VISIBLE);

        setupRecycler();
        RetrofitDataAgentImpl.getInstance().loadNowPlayingMovies();

        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        refresh.post(() -> {
            refresh.setRefreshing(true);
            // Fetching data from server
            RetrofitDataAgentImpl.getInstance().loadNowPlayingMovies();
        });
        return recyclerView;

    }

    public void setupRecycler() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(nadapter);

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
    public void nowplayingMovieLoadedEvent(DataEvent.NowplayingMovieDataLoadedEvent event) {

        nadapter.setNowplayingVOList(event.getNowplayingVOList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void errorEvent(DataEvent.ErrorLoadedEvent event) {

        Toast.makeText(getContext(), event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        RetrofitDataAgentImpl.getInstance().loadNowPlayingMovies();
    }
}
