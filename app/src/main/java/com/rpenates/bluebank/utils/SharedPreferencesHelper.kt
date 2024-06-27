package com.rpenates.bluebank.utils

import android.content.Context
import android.preference.PreferenceManager

object SharedPreferencesHelper {

    fun isFirstLaunch(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean("first_time", true)

    }

    fun setFirstLaunch(context: Context, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean("first_time", value)
            .apply()
    }


}