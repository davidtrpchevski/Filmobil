package com.david.filmobil.watchmoviecollection.watched

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentWatchedBinding
import com.david.filmobil.utils.repeatOnLifecycleCreated
import com.david.filmobil.watchmoviecollection.WatchCollectionFragmentDirections
import com.david.filmobil.watchmoviecollection.watched.adapter.WatchedAdapter
import com.david.filmobil.watchmoviecollection.watched.viewmodel.WatchedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WatchedFragment : Fragment(R.layout.fragment_watched) {

    private val binding by viewBinding(FragmentWatchedBinding::bind)
    private val watchedViewModel by viewModels<WatchedViewModel>()

    @Inject
    lateinit var watchedAdapter: WatchedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.watchedMoviesList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.watchedMoviesList.adapter = watchedAdapter

        repeatOnLifecycleCreated {
            watchedViewModel.watchedMoviesData.collect {
                watchedAdapter.submitData(it)
            }
        }

        watchedAdapter.onItemClick = { watchedMovieModel ->
            watchedMovieModel.id?.let {
                findNavController().navigate(
                    WatchCollectionFragmentDirections.openMovieDetails(it)
                )
            }
        }
    }
}