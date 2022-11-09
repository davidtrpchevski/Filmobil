package com.david.filmobil.explore

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentExploreBinding
import com.david.filmobil.explore.adapter.inner.HorizontalInnerAdapter
import com.david.filmobil.explore.adapter.inner.model.HorizontalInnerModel
import com.david.filmobil.explore.adapter.movie.HorizontalMoviesAdapter
import com.david.filmobil.explore.viewmodel.ExploreViewModel
import com.david.filmobil.network.result.getResultAsSuccess
import com.david.filmobil.utils.repeatOnLifecycleStarted
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private val binding by viewBinding(FragmentExploreBinding::bind)
    private val exploreViewModel: ExploreViewModel by viewModels()

    @Inject
    lateinit var popularMoviesAdapter: HorizontalMoviesAdapter

    @Inject
    lateinit var topRatedMoviesAdapter: HorizontalMoviesAdapter

    @Inject
    lateinit var trendingMoviesAdapter: HorizontalMoviesAdapter

    @Inject
    lateinit var horizontalInnerAdapter: HorizontalInnerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val horizontalInnerAdapterList = listOf(
            HorizontalInnerModel("Popular", popularMoviesAdapter),
            HorizontalInnerModel("Top Rated", topRatedMoviesAdapter),
            HorizontalInnerModel("Trending", trendingMoviesAdapter)
        )

        binding.movieExploreList.adapter = horizontalInnerAdapter
        horizontalInnerAdapter.submitList(horizontalInnerAdapterList)

        horizontalInnerAdapter.onHeaderClick = {
            Log.d("HEADER", it.title)
        }

        repeatOnLifecycleStarted {
            exploreViewModel.popularMoviesData.collect {
                popularMoviesAdapter.submitList(it.getResultAsSuccess()?.movieModels)
            }
        }

        repeatOnLifecycleStarted {
            exploreViewModel.topRatedMoviesData.collect {
                topRatedMoviesAdapter.submitList(it.getResultAsSuccess()?.movieModels)
            }
        }

        repeatOnLifecycleStarted {
            exploreViewModel.trendingMoviesData.collect {
                trendingMoviesAdapter.submitList(it.getResultAsSuccess()?.movieModels)
            }
        }
    }
}