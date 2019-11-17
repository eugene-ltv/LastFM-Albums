package com.saiferwp.lastfmalbums.ui.savedalbums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.util.Result
import com.saiferwp.lastfmalbums.util.successOr
import com.saiferwp.lastfmalbums.util.toast
import com.saiferwp.lastfmalbums.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.saved_albums_fragment.*
import javax.inject.Inject

class SavedAlbumsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SavedAlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        viewModel.openAlbumDetailsAction.observe(this, Observer { album ->
            if (album == null) return@Observer
            findNavController().navigate(
                SavedAlbumsFragmentDirections.toAlbumInfo(album.mbId)
            )
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.saved_albums_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        albumsRecycler.layoutManager = GridLayoutManager(context, 2)
        albumsRecycler.adapter = SavedAlbumsAdapter(viewModel)
        albumsRecycler.itemAnimator = null

        viewModel.savedAlbums.observe(viewLifecycleOwner, Observer { result ->
            if (result is Result.Error) {
                result.exception.message?.let {
                    toast(it)
                }
                return@Observer
            }

            result.successOr(emptyList()).let {
                (albumsRecycler.adapter as SavedAlbumsAdapter)
                    .submitList(it)
                emptyMessage.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
            }
        })

        viewModel.loadSavedAlbums()
    }
}
