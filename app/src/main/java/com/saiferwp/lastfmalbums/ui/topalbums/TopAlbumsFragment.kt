package com.saiferwp.lastfmalbums.ui.topalbums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.util.Result
import com.saiferwp.lastfmalbums.util.successOr
import com.saiferwp.lastfmalbums.util.viewModelProvider
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

            (topAlbumsRecycler.adapter as TopAlbumsAdapter)
                .submitList(result.successOr(emptyList()))
        })

        if (savedInstanceState == null) {
            TopAlbumsFragmentArgs.fromBundle(arguments ?: Bundle.EMPTY).run {
                viewModel.loadTopAlbums(artistMbId)
            }
        }
    }
}
