package com.david.filmobil.searchresult

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentSearchResultBinding
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.searchresult.adapter.SearchResultAdapter
import com.david.filmobil.searchresult.viewmodel.SearchResultViewModel
import com.david.filmobil.utils.repeatOnLifecycleStarted
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultFragment : Fragment(R.layout.fragment_search_result) {

    private val binding by viewBinding(FragmentSearchResultBinding::bind)
    private val searchResultViewModel: SearchResultViewModel by viewModels()

    @Inject
    lateinit var searchResultAdapter: SearchResultAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchResultList.adapter = searchResultAdapter

        repeatOnLifecycleStarted {
            searchResultViewModel.searchData.collect { result ->
                when (result) {
                    is ApiResult.Success -> searchResultAdapter.submitList(result.data.searchResultModels)
                    else -> {}
                }
            }
        }

        searchResultAdapter.onItemClick = { searchResultModel ->
            searchResultModel.id?.let {
                findNavController().navigate(
                    SearchResultFragmentDirections.openMovieDetails(it)
                )
            }
        }
    }
}