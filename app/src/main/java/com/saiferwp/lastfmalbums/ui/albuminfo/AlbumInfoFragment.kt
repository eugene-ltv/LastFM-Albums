package com.saiferwp.lastfmalbums.ui.albuminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.util.successOr
import com.saiferwp.lastfmalbums.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.album_info_fragment.*
import javax.inject.Inject

class AlbumInfoFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AlbumInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        if (savedInstanceState == null) {
            AlbumInfoFragmentArgs.fromBundle(arguments ?: Bundle.EMPTY).run {
                viewModel.loadAlbumInfo(albumMbId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.album_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tracksRecycler.adapter = TracksAdapter()

        viewModel.albumInfo.observe(viewLifecycleOwner, Observer { result ->
            val albumInfo = result.successOr(null) ?: return@Observer

            Glide.with(albumCover.context)
                .load(albumInfo.coverUrl)
                .into(albumCover)

            name.text = albumInfo.name
            artist.text = albumInfo.artist

            (tracksRecycler.adapter as TracksAdapter)
                .submitList(albumInfo.tracks)

            buttonSave.setOnClickListener {
                viewModel.saveAlbum(albumInfo)
            }
        })
    }
}
