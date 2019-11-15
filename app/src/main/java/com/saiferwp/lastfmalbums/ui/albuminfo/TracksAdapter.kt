package com.saiferwp.lastfmalbums.ui.albuminfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.databinding.ListItemTrackBinding
import com.saiferwp.lastfmalbums.domain.model.TrackInfo
import com.saiferwp.lastfmalbums.util.convertDurationToString

class TracksAdapter : ListAdapter<TrackInfo, TrackViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrackViewHolder(
            ListItemTrackBinding.bind(
                inflater.inflate(R.layout.list_item_track, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrackInfo>() {
            override fun areItemsTheSame(
                oldItem: TrackInfo,
                newItem: TrackInfo
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: TrackInfo,
                newItem: TrackInfo
            ): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}

class TrackViewHolder(
    private val binding: ListItemTrackBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(trackInfo: TrackInfo) {
        binding.track.text = trackInfo.name
        binding.duration.text = convertDurationToString(trackInfo.duration)
    }
}