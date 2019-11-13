package com.saiferwp.lastfmalbums.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.databinding.ListItemSearchBinding
import com.saiferwp.lastfmalbums.domain.model.Artist

class SearchAdapter : ListAdapter<Artist,
        ArtistViewHolder>(object :
    DiffUtil.ItemCallback<Artist>() {
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
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ArtistViewHolder(private val binding: ListItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(artist: Artist) {
        binding.artist.text = artist.name
    }
}