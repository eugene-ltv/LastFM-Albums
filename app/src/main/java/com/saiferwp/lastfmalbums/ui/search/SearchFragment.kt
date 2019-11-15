package com.saiferwp.lastfmalbums.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.saiferwp.lastfmalbums.R
import com.saiferwp.lastfmalbums.util.Result
import com.saiferwp.lastfmalbums.util.hideKeyboard
import com.saiferwp.lastfmalbums.util.successOr
import com.saiferwp.lastfmalbums.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
        viewModel.openTopAlbumsAction.observe(this, Observer { artist ->
            if (artist == null) return@Observer
            hideKeyboard(searchInput)
            findNavController().navigate(
                SearchFragmentDirections.toTopAlbums(artist.mbId)
            )
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchRecycler.adapter = SearchAdapter(viewModel)
        searchButton.setOnClickListener {
            val searchString = searchInput.text.toString()
            if (searchString.isNotBlank()) {
                hideKeyboard(searchInput)
                viewModel.search(searchString)
            }
        }

        viewModel.artists.observe(viewLifecycleOwner, Observer { result ->
            searchProgress.visibility = if (result is Result.Loading) {
                View.VISIBLE
            } else {
                View.GONE
            }

            (searchRecycler.adapter as SearchAdapter)
                .submitList(result.successOr(emptyList()))
        })
    }
}
