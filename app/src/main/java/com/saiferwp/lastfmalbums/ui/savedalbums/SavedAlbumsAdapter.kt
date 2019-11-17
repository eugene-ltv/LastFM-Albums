package com.saiferwp.lastfmalbums.ui.savedalbums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.databinding.GridItemAlbumBinding
import com.saiferwp.lastfmalbums.domain.model.AlbumInfo

class SavedAlbumsAdapter(
    private val eventListener: SavedAlbumsEventListener
) : ListAdapter<AlbumInfo, SavedAlbumViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedAlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SavedAlbumViewHolder(
            GridItemAlbumBinding.bind(
                inflater.inflate(R.layout.grid_item_album, parent, false)
            ),
            eventListener
        )
    }

    override fun onBindViewHolder(holder: SavedAlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlbumInfo>() {
            override fun areItemsTheSame(
                oldItem: AlbumInfo,
                newItem: AlbumInfo
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: AlbumInfo,
                newItem: AlbumInfo
            ): Boolean {
                return oldItem.mbId == newItem.mbId
            }
        }
    }
}

class SavedAlbumViewHolder(
    private val binding: GridItemAlbumBinding,
    private val eventListener: SavedAlbumsEventListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(album: AlbumInfo) {
        binding.container.setOnClickListener {
            eventListener.openAlbumDetails(album)
        }
        binding.album.text = album.name
        binding.artist.text = album.artist

        Glide.with(itemView.context)
            .clear(binding.albumPicture)

        Glide.with(itemView.context)
            .load(album.coverUrl)
            .into(binding.albumPicture)
    }
}