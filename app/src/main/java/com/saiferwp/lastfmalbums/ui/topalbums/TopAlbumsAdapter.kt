package com.saiferwp.lastfmalbums.ui.topalbums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.databinding.ListItemAlbumBinding
import com.saiferwp.lastfmalbums.domain.model.Album

class TopAlbumsAdapter(
    private val eventListener: TopAlbumsEventListener
) : ListAdapter<Album, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

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
            else -> TopAlbumViewHolder(
                ListItemAlbumBinding.bind(
                    inflater.inflate(R.layout.list_item_album, parent, false)
                ),
                eventListener
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!isLoaderVisible || (isLoaderVisible && position < super.getItemCount())) {
            (holder as TopAlbumViewHolder).bind(getItem(position))
        }
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(
                oldItem: Album,
                newItem: Album
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: Album,
                newItem: Album
            ): Boolean {
                return oldItem.mbId == newItem.mbId
            }
        }
    }

    class TopAlbumViewHolder(
        private val binding: ListItemAlbumBinding,
        private val eventListener: TopAlbumsEventListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.container.setOnClickListener {
                eventListener.openAlbumDetails(album)
            }
            binding.album.text = album.name

            Glide.with(itemView.context)
                .clear(binding.albumPicture)

            Glide.with(itemView.context)
                .load(album.coverUrl)
                .into(binding.albumPicture)
        }
    }

    class ProgressHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}