package com.example.moviecatalogue.ui.catalogue.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.utils.BASE_IMAGE_URL
import com.example.moviecatalogue.utils.MOVIE
import com.example.moviecatalogue.utils.NavigationsUtils
import kotlinx.android.synthetic.main.item_poster.view.*

class MoviesAdapter internal constructor() :
    PagedListAdapter<Movie, MoviesAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_poster, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position) as Movie
        holder.bind(movie)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + movie.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_movies)
                        .error(R.drawable.ic_movies)
                )
                .into(itemView.img_poster)

            itemView.tv_title.text = movie.title

            itemView.setOnClickListener {
                NavigationsUtils.navigateToDetail(itemView.context, movie.id!!, MOVIE)
            }
        }

    }

}