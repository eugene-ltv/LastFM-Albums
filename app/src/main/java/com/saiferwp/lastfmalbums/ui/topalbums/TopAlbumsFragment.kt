package com.saiferwp.lastfmalbums.ui.topalbums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.util.*
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.top_albums_fragment.*
import javax.inject.Inject

class TopAlbumsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TopAlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        viewModel.openAlbumDetailsAction.observe(this, Observer { album ->
            if (album == null) return@Observer
            findNavController().navigate(
                TopAlbumsFragmentDirections.toAlbumInfo(album.mbId)
            )
        })

        if (savedInstanceState == null) {
            TopAlbumsFragmentArgs.fromBundle(arguments ?: Bundle.EMPTY).run {
                viewModel.loadTopAlbums(artistMbId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.top_albums_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        topAlbumsRecycler.adapter = TopAlbumsAdapter(viewModel)

        viewModel.topAlbums.observe(viewLifecycleOwner, Observer { result ->
            loadingProgress.visibility = if (result is Result.Loading) {
                View.VISIBLE
            } else {
                View.GONE
            }

            if (result is Result.Error) {
                result.exception.message?.let {
                    toast(it)
                }
                return@Observer
            }

            val list = result.successOr(emptyList())
            (topAlbumsRecycler.adapter as TopAlbumsAdapter).apply {
                submitList(list)
                showLoading(!viewModel.isLastPage)
            }
        })

        topAlbumsRecycler.addOnScrollListener(object :
            PaginationListener(
                topAlbumsRecycler.layoutManager!!
            ) {
            override fun loadMoreItems() {
                viewModel.loadMoreItems()
            }

            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }
        })
    }
}