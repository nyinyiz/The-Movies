package com.nyinyi.nw.themovie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nyinyi.nw.themovie.R
import com.nyinyi.nw.themovie.activity.MovieDetailActivity
import com.nyinyi.nw.themovie.util.MovieConstants
import com.nyinyi.nw.themovie.vos.UpcomingVO
import java.util.*

/**
 * Created by User on 9/15/2017.
 */

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder>() {

    private var upcomingVOList: List<UpcomingVO>? = null

    init {
        upcomingVOList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MovieViewHolder {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.search_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MovieViewHolder, position: Int) {

        if (upcomingVOList!!.size != 0) {
            holder.bind(upcomingVOList!![position])
            holder.ivMovie!!.setOnClickListener { v ->
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("id", upcomingVOList!![position].id!!.toString())
                intent.putExtra("title", upcomingVOList!![position].title)
                intent.putExtra("vote_average", upcomingVOList!![position].voteAverage!!.toString())
                intent.putExtra("poster_path", upcomingVOList!![position].posterPath)
                intent.putExtra("original_language", upcomingVOList!![position].originalLanguage)
                intent.putExtra("original_title", upcomingVOList!![position].originalTitle)
                intent.putExtra("backdrop_path", upcomingVOList!![position].backdropPath)
                intent.putExtra("adult", upcomingVOList!![position].adult)
                intent.putExtra("overview", upcomingVOList!![position].overview)
                intent.putExtra("release_date", upcomingVOList!![position].releaseDate)
                context.startActivity(intent)

            }

        } else {
            Toast.makeText(context, "NO result found $itemCount", Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount(): Int {
        return upcomingVOList!!.size
    }

    fun setsearchdatalist(upcomingVOList: List<UpcomingVO>) {
        this.upcomingVOList = upcomingVOList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var ivMovie: ImageView? = null

        init {
            ivMovie = itemView.findViewById(R.id.iv_movie)

        }

        fun bind(data: UpcomingVO) {

            Glide.with(context)
                    .load(MovieConstants.IMAGE_URL + data.posterPath!!)
                    .apply(RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                    .into(ivMovie!!)


        }
    }
}
