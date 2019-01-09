package com.nyinyi.nw.themovie.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nyinyi.nw.themovie.R
import com.nyinyi.nw.themovie.adapter.NowplayingAdapter
import com.nyinyi.nw.themovie.event.DataEvent
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * A simple [Fragment] subclass.
 */
class NowplayingFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    internal var refresh: SwipeRefreshLayout? = null

    internal var recyclerView: RecyclerView? = null

    private var nadapter: NowplayingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nadapter = NowplayingAdapter(this.context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nowplaying, container, false)
        refresh = view.findViewById(R.id.swipeContainer)
        recyclerView = view.findViewById(R.id.rv_nowplaying_movie)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh!!.visibility = View.VISIBLE

        setupRecycler()
        RetrofitDataAgentImpl.instance.loadNowPlayingMovies()

        refresh!!.setOnRefreshListener(this)
        refresh!!.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark)

        refresh!!.post {
            refresh!!.isRefreshing = true
            // Fetching data from server
            RetrofitDataAgentImpl.instance.loadNowPlayingMovies()
        }
    }

    fun setupRecycler() {

        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = GridLayoutManager(context, 3)
        recyclerView!!.adapter = nadapter

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
    fun nowplayingMovieLoadedEvent(event: DataEvent.NowplayingMovieDataLoadedEvent) {

        nadapter!!.setNowplayingVOList(event.nowplayingVOList)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun errorEvent(event: DataEvent.ErrorLoadedEvent) {

        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        RetrofitDataAgentImpl.instance.loadNowPlayingMovies()
    }
}// Required empty public constructor
