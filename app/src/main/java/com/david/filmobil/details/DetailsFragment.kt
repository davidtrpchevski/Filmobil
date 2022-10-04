package com.david.filmobil.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentDetailsBinding
import com.david.filmobil.details.model.MovieDetailsModel
import com.david.filmobil.details.viewmodel.DetailsViewModel
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.utils.loadImageFromUrl
import com.david.filmobil.utils.repeatOnLifecycleStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repeatOnLifecycleStarted {
            detailsViewModel.movieDetails.collect { result ->
                binding.loadingProgressBar.isVisible = result is ApiResult.Loading
                when (result) {
                    is ApiResult.Success -> fillMovieDetails(result.data)
                    else -> detailsViewModel.showErrorToast(result)
                }
            }
        }

        repeatOnLifecycleStarted {
            detailsViewModel.isMovieInFavorites.collect { isInFavorites ->
                binding.addToFavorites.icon = AppCompatResources.getDrawable(
                    requireContext(),
                    if (isInFavorites) R.drawable.ic_baseline_favorite else R.drawable.ic_baseline_favorite_border
                )
            }
        }

        binding.addToFavorites.setOnClickListener {
            detailsViewModel.handleMovieIntoDatabase()
        }

        binding.addToWatched.setOnClickListener {
            detailsViewModel.insertMovieToWatchedList()
        }
    }

    private fun fillMovieDetails(movie: MovieDetailsModel) {
        binding.movieDetailsBackdrop.load(loadImageFromUrl(movie.backdropPath))
        binding.movieDetailsPoster.load(loadImageFromUrl(movie.posterPath))
        binding.movieDetailsSummary.text = movie.overview
        binding.movieDetailsTagline.text = movie.tagline
        binding.movieDetailsYear.text = movie.releaseDate
        binding.movieDetailsTitle.text = movie.title
    }
}