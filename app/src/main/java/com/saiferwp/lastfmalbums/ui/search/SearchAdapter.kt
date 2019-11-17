package com.saiferwp.lastfmalbums.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.databinding.ListItemSearchBinding
import com.saiferwp.lastfmalbums.domain.model.Artist

class SearchAdapter(
    private val eventListener: SearchEventListener
) : ListAdapter<Artist, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private var isLoaderVisible = false

    fun showLoading(show: Boolean) {
        isLoaderVisible = show
        notifyDataSetChanged()
    }

    override fun getItemCount() = super.getItemCount() +
            if (isLoaderVisible && super.getItemCount() > 0) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == super.getItemCount()) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LOADING -> ProgressHolder(
                inflater.inflate(R.layout.list_item_loading, parent, false)
            )
            else -> ArtistViewHolder(
                ListItemSearchBinding.bind(
                    inflater.inflate(R.layout.list_item_search, parent, false)
                ),
                eventListener
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!isLoaderVisible || (isLoaderVisible && position < super.getItemCount())) {
            (holder as ArtistViewHolder).bind(getItem(position))
        }
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(
                oldItem: Artist,
                newItem: Artist
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: Artist,
                newItem: Artist
            ): Boolean {
                return oldItem.mbId == newItem.mbId
            }
        }
    }

    class ArtistViewHolder(
        private val binding: ListItemSearchBinding,
        private val eventListener: SearchEventListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(artist: Artist) {
            binding.container.setOnClickListener {
                eventListener.openTopAlbums(artist)
            }
            binding.artist.text = artist.name
        }
    }

    class ProgressHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}