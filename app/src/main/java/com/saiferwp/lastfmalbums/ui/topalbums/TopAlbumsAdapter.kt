package com.saiferwp.lastfmalbums.ui.topalbums

import android.view.LayoutInflater
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
) : ListAdapter<Album, TopAlbumViewHolder>(
    object : DiffUtil.ItemCallback<Album>() {
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
            return oldItem.name == newItem.name
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TopAlbumViewHolder(
            ListItemAlbumBinding.bind(
                inflater.inflate(R.layout.list_item_album, parent, false)
            ),
            eventListener
        )
    }

    override fun onBindViewHolder(holder: TopAlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
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
            .load(album.getPictureUrl())
            .into(binding.albumPicture)
    }
}