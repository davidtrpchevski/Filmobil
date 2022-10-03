package com.david.filmobil.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentSearchBinding
import com.david.filmobil.search.adapter.SearchHistoryAdapter
import com.david.filmobil.search.viewmodel.SearchViewModel
import com.david.filmobil.utils.repeatOnLifecycleStarted
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val searchViewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var searchHistoryAdapter: SearchHistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchHistoryList.adapter = searchHistoryAdapter

        repeatOnLifecycleStarted {
            searchViewModel.searchHistoryData.collect {
                searchHistoryAdapter.submitList(it)
            }
        }

        searchHistoryAdapter.onRemoveClick = {
            searchViewModel.removeFromSearchHistory(it)
        }

        searchHistoryAdapter.onItemClick = {
            navigateToSearchResults(it.searchHistoryQuery)
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.insertIntoSearchHistory(it)
                    navigateToSearchResults(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewModel.filterSearchHistory(newText)
                return true
            }
        })
    }

    private fun navigateToSearchResults(it: String) {
        findNavController().navigate(SearchFragmentDirections.openSearchResult(it))
    }
}