package com.nyinyi.nw.themovie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.adapter.MovieListAdapter;
import com.nyinyi.nw.themovie.event.DataEvent;
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_upcoming_movie)
    RecyclerView recyclerView;

    @BindView(R.id.upcoming_fresh)
    ProgressBar swipeRefreshLayout;

    private MovieListAdapter mAapter;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAapter = new MovieListAdapter(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, rootView);

        RetrofitDataAgentImpl.getInstance().loadUpcomingMovies();
        setupRecycler();
        return recyclerView;

    }

    public void setupRecycler() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mAapter);

    }

    @Override
    public void onRefresh() {
        RetrofitDataAgentImpl.getInstance().loadUpcomingMovies();
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
    public void upcomingMovieLoadedEvent(DataEvent.UpcomingMovieDataLoadedEvent event) {
        swipeRefreshLayout.setVisibility(View.GONE);
        mAapter.setUpcomingVOList(event.getUpcomingVOList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void errorEvent(DataEvent.ErrorLoadedEvent event) {
        swipeRefreshLayout.setVisibility(View.GONE);
        Toast.makeText(getContext(), event.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
