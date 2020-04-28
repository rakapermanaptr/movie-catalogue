package com.example.moviecatalogue.utils

import android.app.Activity
import android.content.Intent
import com.example.moviecatalogue.ui.detail.DetailActivity

object NavigationsUtils {

    fun navigateToDetail(activity: Activity, id: Int, type: String) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(KEY_ID, id)
        intent.putExtra(KEY_TYPE, type)
        activity.startActivity(intent)
    }
}