package com.example.moviecatalogue.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.domain.entity.Movie
import com.example.moviecatalogue.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_poster.view.*

class MoviesAdapter(private val onItemClick: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    fun addItems(movies: List<Movie>) {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_poster, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { onItemClick(movie) }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + movie.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_movies)
                    .error(R.drawable.ic_movies))
                .into(itemView.img_poster)

            itemView.tv_title.text = movie.title
        }

    }

}