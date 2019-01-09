package com.nyinyi.nw.themovie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nyinyi.nw.themovie.R
import com.nyinyi.nw.themovie.adapter.MovieListAdapter
import com.nyinyi.nw.themovie.event.DataEvent
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class UpcomingFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    internal var recyclerView: RecyclerView? = null

    internal var progressBar: ProgressBar? = null

    private var mAapter: MovieListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAapter = MovieListAdapter(this.context!!)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_upcoming, container, false)
        progressBar = rootView.findViewById(R.id.upcoming_fresh)
        recyclerView = rootView.findViewById(R.id.rv_upcoming_movie)

        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar!!.visibility = VISIBLE

        RetrofitDataAgentImpl.instance.loadUpcomingMovies()
        setupRecycler()
    }

    fun setupRecycler() {

        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = GridLayoutManager(context, 3)
        recyclerView!!.adapter = mAapter

    }

    override fun onRefresh() {
        RetrofitDataAgentImpl.instance.loadUpcomingMovies()
    }

    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)

    }

    override fun onStop() {
        super.onStop()

        EventBus.getDefault().unregister(this)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun upcomingMovieLoadedEvent(event: DataEvent.UpcomingMovieDataLoadedEvent) {
        progressBar!!.visibility = View.GONE
        mAapter!!.setUpcomingVOList(event.upcomingVOList)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun errorEvent(event: DataEvent.ErrorLoadedEvent) {
        progressBar!!.visibility = View.GONE
        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
    }

}// Required empty public constructor
