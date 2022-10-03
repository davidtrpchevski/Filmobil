package com.david.filmobil.watchmoviecollection.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class WatchCollectionAdapter @AssistedInject constructor(
    @Assisted(FRAGMENT_KEY) private val fragment: Fragment,
    @Assisted(FRAGMENT_LIST_KEY) private val fragmentList: List<KClass<out Fragment>>
) : FragmentStateAdapter(fragment) {

    private companion object {
        private const val FRAGMENT_KEY = "ASSISTED_FRAGMENT_KEY"
        private const val FRAGMENT_LIST_KEY = "ASSISTED_FRAGMENT_LIST_KEY"
    }

    @AssistedFactory
    interface WatchCollectionAdapterFactory {
        fun create(
            @Assisted(FRAGMENT_KEY) fragment: Fragment,
            @Assisted(FRAGMENT_LIST_KEY) fragmentList: List<KClass<out Fragment>>
        ): WatchCollectionAdapter
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position].createInstance()

}