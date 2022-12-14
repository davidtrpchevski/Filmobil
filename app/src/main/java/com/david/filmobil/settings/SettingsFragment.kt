package com.david.filmobil.settings

import android.os.Bundle
import android.view.View
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.crazylegend.kotlinextensions.preferences.preference
import com.david.filmobil.R
import com.david.filmobil.common.ThemingModeUtil
import com.david.filmobil.constants.THEMING_PREF_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val themingPref by preference<ListPreference>(THEMING_PREF_KEY)

    @Inject
    lateinit var themingModeUtil: ThemingModeUtil

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_main, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themingPref.setOnPreferenceChangeListener { _, newValue ->
            themingModeUtil.changeAppTheme(newValue as String)
            true
        }
    }

}