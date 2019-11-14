package com.saiferwp.lastfmalbums.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.databinding.ListItemSearchBinding
import com.saiferwp.lastfmalbums.domain.model.Artist

class SearchAdapter(
    private val eventListener: SearchEventListener
) : ListAdapter<Artist, ArtistViewHolder>(
    object : DiffUtil.ItemCallback<Artist>() {
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
            return oldItem.name == newItem.name
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArtistViewHolder(
            ListItemSearchBinding.bind(
                inflater.inflate(R.layout.list_item_search, parent, false)
            ),
            eventListener
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(getItem(position))
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