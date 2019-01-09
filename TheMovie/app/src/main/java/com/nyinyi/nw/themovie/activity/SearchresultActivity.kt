package com.nyinyi.nw.themovie.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.nyinyi.nw.themovie.R
import com.nyinyi.nw.themovie.adapter.RecyclerAdapter
import com.nyinyi.nw.themovie.event.DataEvent
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SearchresultActivity : AppCompatActivity() {

    @BindView(R.id.rv_movie)
    internal var recycler: RecyclerView? = null

    private var search_name: String? = null
    private var radapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchresult)

        ButterKnife.bind(this, this)
        search_name = intent.getStringExtra("search_name")

        if (supportActionBar != null) supportActionBar!!.setTitle(search_name)


        radapter = RecyclerAdapter(applicationContext)
        setupRecycler()
        RetrofitDataAgentImpl.instance.loadsearchMovie(search_name.toString())


    }

    private fun setupRecycler() {
        recycler!!.setHasFixedSize(true)
        recycler!!.layoutManager = GridLayoutManager(applicationContext, 3)
        recycler!!.adapter = radapter
    }

    public override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)

    }

    public override fun onStop() {
        super.onStop()

        EventBus.getDefault().unregister(this)

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun searchMovieLoadedEvent(event: DataEvent.SearchMovieLoadEvent) {
        radapter!!.setsearchdatalist(event.searchVOList)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun errorEvent(event: DataEvent.ErrorLoadedEvent) {
        Toast.makeText(applicationContext, event.message, Toast.LENGTH_SHORT).show()
    }

}
