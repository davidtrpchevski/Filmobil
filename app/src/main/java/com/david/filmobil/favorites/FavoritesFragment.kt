package com.david.filmobil.favorites

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.database.entities.FavoriteMovieModel
import com.david.filmobil.databinding.FragmentFavoritesBinding
import com.david.filmobil.favorites.adapter.FavoritesMovieAdapter
import com.david.filmobil.favorites.viewmodel.FavoritesViewModel
import com.david.filmobil.utils.getImdbUrl
import com.david.filmobil.utils.repeatOnLifecycleStarted
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    @Inject
    lateinit var favoritesMovieAdapter: FavoritesMovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritesList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.favoritesList.adapter = favoritesMovieAdapter

        repeatOnLifecycleStarted {
            favoritesViewModel.favoritesMovieList.collect {
                favoritesMovieAdapter.submitData(it)
            }
        }

        favoritesMovieAdapter.onFavoriteClick = { favoriteMovieModel ->
            val favoriteMovieId = favoriteMovieModel.id ?: 0
            findNavController().navigate(
                FavoritesFragmentDirections.openMovieDetails(
                    favoriteMovieId
                )
            )
        }

        favoritesMovieAdapter.onFavoriteLongClick = { favoriteMovieModel, itemView ->
            showPopupMenu(itemView, favoriteMovieModel)
        }
    }

    private fun showPopupMenu(
        itemView: View,
        favoriteMovieModel: FavoriteMovieModel
    ) {
        with(PopupMenu(requireContext(), itemView)) {
            inflate(R.menu.favorite_dropdown)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.share -> {
                        shareImdbUrl(favoriteMovieModel.imdbId)
                        true
                    }
                    R.id.delete -> {
                        favoritesViewModel.removeMovieFromFavorites(favoriteMovieModel)
                        true
                    }
                    else -> true
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                setForceShowIcon(true)
            }
            show()
        }
    }

    private fun Fragment.shareImdbUrl(imdbId: String?) {
        ShareCompat.IntentBuilder(requireContext())
            .setType("text/plain")
            .setChooserTitle(getString(R.string.share_imdb_title))
            .setText(getImdbUrl(imdbId))
            .startChooser()
    }
}