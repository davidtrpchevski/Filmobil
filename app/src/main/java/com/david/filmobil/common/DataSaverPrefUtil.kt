package com.david.filmobil.common

import android.content.SharedPreferences
import com.david.filmobil.constants.DATA_SAVER_PREF_KEY
import com.david.filmobil.di.qualifiers.DefaultSharedPref
import javax.inject.Inject

class DataSaverPrefUtil @Inject constructor(@DefaultSharedPref private val sharedPreferences: SharedPreferences) {
    val isDataSavingEnabled get() = sharedPreferences.getBoolean(DATA_SAVER_PREF_KEY, false)
}