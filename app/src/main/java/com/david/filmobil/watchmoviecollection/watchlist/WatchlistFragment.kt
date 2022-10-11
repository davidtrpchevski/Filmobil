package com.david.filmobil.watchmoviecollection.watchlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentWatchlistBinding
import com.david.filmobil.utils.repeatOnLifecycleCreated
import com.david.filmobil.watchmoviecollection.WatchCollectionFragmentDirections
import com.david.filmobil.watchmoviecollection.watchlist.adapter.WatchlistAdapter
import com.david.filmobil.watchmoviecollection.watchlist.viewmodel.WatchlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {

    private val binding by viewBinding(FragmentWatchlistBinding::bind)
    private val watchlistViewModel by viewModels<WatchlistViewModel>()

    @Inject
    lateinit var watchlistAdapter: WatchlistAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.watchlistCollection.adapter = watchlistAdapter
        binding.watchlistCollection.layoutManager = GridLayoutManager(requireContext(), 2)

        repeatOnLifecycleCreated {
            watchlistViewModel.watchlistMoviesData.collect {
                watchlistAdapter.submitData(it)
            }
        }

        watchlistAdapter.onItemClick = { watchlistMoviesModel ->
            watchlistMoviesModel.id?.let {
                findNavController().navigate(
                    WatchCollectionFragmentDirections.openMovieDetails(it)
                )
            }
        }
    }
}