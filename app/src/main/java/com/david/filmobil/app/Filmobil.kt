package com.david.filmobil.app

import android.app.Application
import com.david.filmobil.common.ThemingModeUtil
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Filmobil : Application() {

    @Inject
    lateinit var themingModeUtil: ThemingModeUtil

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        themingModeUtil.changeAppTheme()
    }
}