package com.david.filmobil.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.R
import com.david.filmobil.databinding.FragmentHomeBinding
import com.david.filmobil.home.adapter.MoviesAdapter
import com.david.filmobil.home.viewmodel.HomeViewModel
import com.david.filmobil.utils.repeatOnLifecycleStarted
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesList.adapter = moviesAdapter
        repeatOnLifecycleStarted {
            homeViewModel.moviesList.collect {
                moviesAdapter.submitData(it)
            }
        }

        moviesAdapter.onItemClick = { movieModel ->
            movieModel.id?.let {
                findNavController().navigate(
                    HomeFragmentDirections.openMovieDetails(it)
                )
            }
        }

        binding.toTop.setOnClickListener {
            binding.moviesList.layoutManager?.scrollToPosition(0)
        }

        binding.moviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position =
                    (binding.moviesList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                binding.toTop.isVisible = position >= 20
            }
        })
    }
}