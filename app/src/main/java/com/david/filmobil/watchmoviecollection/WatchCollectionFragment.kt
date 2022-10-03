package com.david.filmobil.watchmoviecollection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentWatchCollectionBinding
import com.david.filmobil.watchmoviecollection.adapter.WatchCollectionAdapter
import com.david.filmobil.watchmoviecollection.watched.WatchedFragment
import com.david.filmobil.watchmoviecollection.watchlist.WatchlistFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WatchCollectionFragment : Fragment(R.layout.fragment_watch_collection) {
    private val binding by viewBinding(FragmentWatchCollectionBinding::bind)

    @Inject
    lateinit var watchCollectionAdapterFactory: WatchCollectionAdapter.WatchCollectionAdapterFactory

    private val fragmentList = listOf(WatchlistFragment::class, WatchedFragment::class)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.watchViewpager.adapter = watchCollectionAdapterFactory.create(this, fragmentList)
        TabLayoutMediator(binding.watchTabLayout, binding.watchViewpager) { tab, position ->
            tab.text =
                fragmentList[position].simpleName?.removeSuffix(getString(R.string.fragment_suffix))
        }.attach()
    }
}