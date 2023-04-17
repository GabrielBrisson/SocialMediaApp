package com.curral.social_media.data.local.repository

import android.content.Context
import com.curral.social_media.domain.repository.SharedPref

class SharedPrefImpl(private val context: Context) : SharedPref {
    private val sharedPreferences =
        context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)


    override fun getUserId(): String? {
        return if(!sharedPreferences.contains("id")) {
            null
        } else {
            sharedPreferences.getString("id", "")
        }
    }

    override fun setUserId(id: String) {
        val editor = sharedPreferences.edit()
        editor.putString("id", id)
        editor.apply()
    }
}