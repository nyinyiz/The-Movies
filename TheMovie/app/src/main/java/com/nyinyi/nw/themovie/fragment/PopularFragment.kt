package com.nyinyi.nw.themovie.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyinyi.nw.themovie.R
import com.nyinyi.nw.themovie.adapter.PopularAdapter
import com.nyinyi.nw.themovie.event.DataEvent
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * A simple [Fragment] subclass.
 */
class PopularFragment : Fragment() {

    internal var recyclerView: RecyclerView? = null

    private var nadapter: PopularAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nadapter = PopularAdapter(this.context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_popular, container, false)
        recyclerView = view.findViewById(R.id.rv_nowplaying_movie)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        RetrofitDataAgentImpl.instance.loadPopularMovies()
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
    fun popularMovieLoadedEvent(event: DataEvent.PopularMovieDataLoadedEvent) {
        nadapter!!.setPopularVOList(event.popularVOList)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun errorEvent(event: DataEvent.ErrorLoadedEvent) {

        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
    }

}// Required empty public constructor
