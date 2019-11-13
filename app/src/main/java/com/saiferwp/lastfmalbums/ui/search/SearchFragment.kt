package com.saiferwp.lastfmalbums.ui.search

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
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = viewModelProvider(viewModelFactory)

        searchRecycler.adapter = SearchAdapter()
        searchButton.setOnClickListener {
            viewModel.search(searchInput.text.toString())
        }

        viewModel.liveData.observe(this, Observer { result ->
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
