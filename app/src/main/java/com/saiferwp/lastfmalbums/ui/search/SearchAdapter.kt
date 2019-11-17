package com.saiferwp.lastfmalbums.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.databinding.ListItemSearchBinding
import com.saiferwp.lastfmalbums.domain.model.Artist

class SearchAdapter(
    private val eventListener: SearchEventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<Artist>()
    private var isLoaderVisible = false

    fun showLoading(show: Boolean) {
        isLoaderVisible = show
        notifyDataSetChanged()
    }

    fun setData(
        items: List<Artist>
    ) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size +
            if (isLoaderVisible && items.isNotEmpty()) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == items.size) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
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
        if (!isLoaderVisible || (isLoaderVisible && position < items.size)) {
            (holder as ArtistViewHolder).bind(items[position])
        }
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1
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