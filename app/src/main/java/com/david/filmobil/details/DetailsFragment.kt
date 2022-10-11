package com.david.filmobil.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.common.DataSaverPrefUtil
import com.david.filmobil.databinding.FragmentDetailsBinding
import com.david.filmobil.details.model.MovieDetailsModel
import com.david.filmobil.details.viewmodel.DetailsViewModel
import com.david.filmobil.network.result.ApiResult
import com.david.filmobil.utils.loadImageFromApi
import com.david.filmobil.utils.repeatOnLifecycleStarted
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val detailsViewModel: DetailsViewModel by viewModels()

    @Inject
    lateinit var dataSaverPrefUtil: DataSaverPrefUtil

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
                binding.addToFavoritesFab.icon = AppCompatResources.getDrawable(
                    requireContext(),
                    if (isInFavorites) R.drawable.ic_baseline_favorite else R.drawable.ic_baseline_favorite_border
                )
            }
        }

        binding.addToFavoritesFab.setOnClickListener {
            detailsViewModel.handleMovieIntoDatabase()
        }

        binding.addToWatchedFab.setOnClickListener {
            detailsViewModel.handleMovieToWatchedList()
        }

        binding.addToWatchlistFab.setOnClickListener {
            detailsViewModel.handleMovieToWatchlist()
        }

        repeatOnLifecycleStarted {
            detailsViewModel.isAddToFabPressed.collect {
                binding.addToWatchlist.isVisible = it
                binding.addToFavorites.isVisible = it
                binding.addToWatched.isVisible = it
            }
        }

        binding.addToFab.setOnClickListener {
            detailsViewModel.setAddToFabPressStatus()
        }
    }

    private fun fillMovieDetails(movie: MovieDetailsModel) {
        binding.movieDetailsBackdrop.loadImageFromApi(
            movie.backdropPath,
            dataSaverPrefUtil.isDataSavingEnabled
        )
        binding.movieDetailsPoster.loadImageFromApi(
            movie.posterPath,
            dataSaverPrefUtil.isDataSavingEnabled
        )
        binding.movieDetailsSummary.text = movie.overview
        binding.movieDetailsTagline.text = movie.tagline
        binding.movieDetailsYear.text = movie.releaseDate
        binding.movieDetailsTitle.text = movie.title
    }
}