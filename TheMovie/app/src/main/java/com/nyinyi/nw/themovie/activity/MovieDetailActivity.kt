package com.nyinyi.nw.themovie.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nyinyi.nw.themovie.R
import com.nyinyi.nw.themovie.event.DataEvent
import com.nyinyi.nw.themovie.network.RetrofitDataAgentImpl
import com.nyinyi.nw.themovie.util.MovieConstants
import com.nyinyi.nw.themovie.vos.MovieVO
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MovieDetailActivity : AppCompatActivity() {

    internal var cover: ImageView? = null
    internal var poster: ImageView? = null
    internal var movie_title: TextView? = null
    internal var c_adult: CardView? = null
    internal var story: TextView? = null
    internal var rate: RatingBar? = null
    internal var ori_lan: TextView? = null
    internal var ori_title: TextView? = null
    internal var tx_release: TextView? = null
    internal var cover_picture: ImageView? = null

    internal lateinit var movieVO: MovieVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        cover = findViewById(R.id.iv_backdrop)
        poster = findViewById(R.id.iv_poster)
        movie_title = findViewById(R.id.movie_title)
        c_adult = findViewById(R.id.adult)
        story = findViewById(R.id.overview)
        rate = findViewById(R.id.rating)
        ori_lan = findViewById(R.id.or_lan)
        ori_title = findViewById(R.id.or_title)
        tx_release = findViewById(R.id.tx_release)
        cover_picture = findViewById(R.id.cover_picture)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setTitle("")
        val collapsingToolbarLayout = findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout

        val id = intent.getStringExtra("id")

        RetrofitDataAgentImpl.instance.loadMovieDetail(id)

        val vote_count = intent.getStringExtra("vote_count")
        val vote_average = intent.getStringExtra("vote_average")
        val title = intent.getStringExtra("title")
        val poster_path = intent.getStringExtra("poster_path")
        val original_language = intent.getStringExtra("original_language")
        val original_title = intent.getStringExtra("original_title")
        val backdrop_path = intent.getStringExtra("backdrop_path")
        val adult = intent.getBooleanExtra("adult", false)
        val overview = intent.getStringExtra("overview")
        val release_date = intent.getStringExtra("release_date")

        Toast.makeText(applicationContext, "ID $id", Toast.LENGTH_LONG).show()

        actionBar!!.setTitle(title)
        collapsingToolbarLayout.isTitleEnabled = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            collapsingToolbarLayout.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY -> collapsingToolbarLayout.isTitleEnabled = true }
        }

        ButterKnife.bind(this, this)


        //        Toast.makeText(getApplicationContext(),movieVO.getTitle(),Toast.LENGTH_LONG).show();

        rate!!.isEnabled = false

        val rating = java.lang.Float.parseFloat(vote_average) / 2

        Glide.with(applicationContext)
                .load(MovieConstants.IMAGE_URL + backdrop_path)
                .apply(RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                .into(cover!!)

        Glide.with(applicationContext)
                .load(MovieConstants.IMAGE_URL + poster_path)
                .apply(RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                .into(poster!!)

        movie_title!!.text = title

        if (adult) {
            c_adult!!.visibility = View.VISIBLE
        } else {
            c_adult!!.visibility = View.GONE
        }

        rate!!.rating = rating

        story!!.text = overview


        ori_title!!.text = original_title
        if (original_language == "en") {
            ori_lan!!.text = "English"
        } else {
            ori_lan!!.text = original_language
        }
        tx_release!!.text = release_date

        Glide.with(applicationContext)
                .load(MovieConstants.IMAGE_URL + backdrop_path)
                .apply(RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                .into(cover_picture!!)
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
    fun movieDetailLoadedEvent(event: DataEvent.MovieDetail) {
        movieVO = event.moviedetailList
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun errorEvent(event: DataEvent.ErrorLoadedEvent) {
        Toast.makeText(applicationContext, event.message, Toast.LENGTH_SHORT).show()
    }
}
