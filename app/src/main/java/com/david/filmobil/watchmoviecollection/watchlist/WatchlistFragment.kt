package com.david.filmobil.watchmoviecollection.watchlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentWatchlistBinding
import com.david.filmobil.watchmoviecollection.watchlist.viewmodel.WatchlistViewModel

class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {
    private val binding by viewBinding(FragmentWatchlistBinding::bind)
    private val watchlistViewModel by viewModels<WatchlistViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}