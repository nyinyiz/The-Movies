package com.nyinyi.nw.themovie.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nyinyi.nw.themovie.R;
import com.nyinyi.nw.themovie.adapter.PopularAdapter;
import com.nyinyi.nw.themovie.event.DataEvent;
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    @BindView(R.id.rv_nowplaying_movie)
    RecyclerView recyclerView;

    private PopularAdapter nadapter;


    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nadapter = new PopularAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.bind(this, view);
        setupRecycler();
        RetrofitDataAgentImpl.getInstance().loadPopularMovies();
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
    public void popularMovieLoadedEvent(DataEvent.PopularMovieDataLoadedEvent event) {
        nadapter.setPopularVOList(event.getPopularVOList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void errorEvent(DataEvent.ErrorLoadedEvent event) {

        Toast.makeText(getContext(), event.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
