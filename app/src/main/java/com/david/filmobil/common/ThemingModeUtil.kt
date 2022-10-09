package com.david.filmobil.common

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.david.filmobil.di.qualifiers.DefaultSharedPref
import javax.inject.Inject

class ThemingModeUtil @Inject constructor(@DefaultSharedPref private val sharedPreferences: SharedPreferences) {

    private val getChosenThemeMode get() = sharedPreferences.getString(THEMING_PREF_KEY, "3")

    fun changeAppTheme(themeMode: String? = getChosenThemeMode) {
        AppCompatDelegate.setDefaultNightMode(
            when (themeMode) {
                "1" -> AppCompatDelegate.MODE_NIGHT_YES
                "2" -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )

    }
}