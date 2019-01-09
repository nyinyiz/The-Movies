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
import com.nyinyi.nw.themovie.vos.PopularVO
import java.util.*

/**
 * Created by User on 9/15/2017.
 */

class PopularAdapter(private val context: Context) : RecyclerView.Adapter<PopularAdapter.MovieViewHolder>() {

    private var popularVOList: List<PopularVO>? = null

    init {
        popularVOList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.MovieViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.upcomingmovie, parent, false)
        return MovieViewHolder(view)

    }

    override fun onBindViewHolder(holder: PopularAdapter.MovieViewHolder, position: Int) {

        holder.bind(popularVOList!![position])
        holder.imageView!!.setOnClickListener { v ->
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("id", popularVOList!![position].id!!.toString())
            intent.putExtra("title", popularVOList!![position].title)
            intent.putExtra("vote_average", popularVOList!![position].voteAverage!!.toString())
            intent.putExtra("poster_path", popularVOList!![position].posterPath)
            intent.putExtra("original_language", popularVOList!![position].originalLanguage)
            intent.putExtra("original_title", popularVOList!![position].originalTitle)
            intent.putExtra("backdrop_path", popularVOList!![position].backdropPath)
            intent.putExtra("adult", popularVOList!![position].adult)
            intent.putExtra("overview", popularVOList!![position].overview)
            intent.putExtra("release_date", popularVOList!![position].releaseDate)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularVOList!!.size
    }

    fun setPopularVOList(popularVOList: List<PopularVO>) {
        this.popularVOList = popularVOList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.iv_movie)
        }

        fun bind(data: PopularVO) {
            Glide.with(context)
                    .load(MovieConstants.IMAGE_URL + data.posterPath!!)
                    .apply(RequestOptions().placeholder(R.drawable.images).error(R.drawable.images))
                    .into(imageView!!)
        }
    }
}
