package com.nyinyi.nw.themovie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nyinyi.nw.themovie.R
import com.nyinyi.nw.themovie.activity.MovieDetailActivity
import com.nyinyi.nw.themovie.util.MovieConstants
import com.nyinyi.nw.themovie.vos.NowplayingVO
import java.util.*

/**
 * Created by User on 9/14/2017.
 */

class NowplayingAdapter(private val context: Context) : RecyclerView.Adapter<NowplayingAdapter.MovieViewHolder>() {

    private var nowplayingVOList: List<NowplayingVO>? = null

    init {
        nowplayingVOList = ArrayList()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowplayingAdapter.MovieViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.upcomingmovie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowplayingAdapter.MovieViewHolder, position: Int) {

        holder.bind(nowplayingVOList!![position])
        holder.ivMovie!!.setOnClickListener { v ->
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("id", nowplayingVOList!![position].id!!.toString())
            intent.putExtra("title", nowplayingVOList!![position].title)
            intent.putExtra("vote_average", nowplayingVOList!![position].voteAverage!!.toString())
            intent.putExtra("poster_path", nowplayingVOList!![position].posterPath)
            intent.putExtra("original_language", nowplayingVOList!![position].originalLanguage)
            intent.putExtra("original_title", nowplayingVOList!![position].originalTitle)
            intent.putExtra("backdrop_path", nowplayingVOList!![position].backdropPath)
            intent.putExtra("adult", nowplayingVOList!![position].adult)
            intent.putExtra("overview", nowplayingVOList!![position].overview)
            intent.putExtra("release_date", nowplayingVOList!![position].releaseDate)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return nowplayingVOList!!.size
    }

    fun setNowplayingVOList(nowplayingVOList: List<NowplayingVO>) {
        this.nowplayingVOList = nowplayingVOList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var ivMovie: ImageView? = null

        init {
            ivMovie = itemView.findViewById(R.id.iv_movie)

        }

        fun bind(data: NowplayingVO) {
            Glide.with(context)
                    .load(MovieConstants.IMAGE_URL + data.posterPath!!)
                    .apply(RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                    .into(ivMovie!!)
        }

    }
}
