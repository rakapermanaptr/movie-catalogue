package com.example.moviecatalogue.presentation.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.domain.entity.TvShow
import com.example.moviecatalogue.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_poster.view.*

class TvShowsAdapter(private val onItemClick: (tvShow: TvShow) -> Unit) :
    RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>() {

    private val tvShowList = mutableListOf<TvShow>()

    fun addItems(tvShows: List<TvShow>) {
        tvShowList.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_poster, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvShowList.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShowList[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener { onItemClick(tvShow) }
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tvShow: TvShow) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + tvShow.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_tv)
                        .error(R.drawable.ic_tv))
                .into(itemView.img_poster)

            itemView.tv_title.text = tvShow.name
        }

    }

}