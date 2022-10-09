package com.david.filmobil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.crazylegend.view.setOnClickListenerCooldown
import com.crazylegend.viewbinding.viewBinding
import com.david.filmobil.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val navController get() = binding.fragmentContainer.getFragment<NavHostFragment>().navController
    private val appBarConfiguration = AppBarConfiguration(
        setOf(
            R.id.homeFragment,
            R.id.favoritesFragment,
            R.id.watchCollectionFragment,
            R.id.settingsFragment,
        )
    )

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavBar()
    }

    private fun setupNavBar() {
        binding.search.setOnClickListenerCooldown { navController.navigate(NavMainDirections.actionGlobalSearch()) }

        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _: Bundle? ->
            binding.bottomAppBar.isVisible =
                appBarConfiguration.topLevelDestinations.contains(destination.id)

            binding.search.isVisible =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
        }
    }
}