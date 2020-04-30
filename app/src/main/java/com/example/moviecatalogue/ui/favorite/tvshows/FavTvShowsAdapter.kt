package com.example.moviecatalogue.ui.favorite.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.*
import kotlinx.android.synthetic.main.item_poster.view.*

class FavTvShowsAdapter internal constructor() : PagedListAdapter<TvShow, FavTvShowsAdapter.TvShowViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_poster, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position) as TvShow
        holder.bind(tvShow)
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

            itemView.setOnClickListener {
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(KEY_ID, tvShow.id)
                    intent.putExtra(KEY_TYPE, TV_SHOW)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

}