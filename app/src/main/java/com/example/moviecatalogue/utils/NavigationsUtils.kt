package com.example.moviecatalogue.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.moviecatalogue.ui.detail.DetailActivity

object NavigationsUtils {

    fun navigateToDetail(context: Context, id: Int, type: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(KEY_ID, id)
        intent.putExtra(KEY_TYPE, type)
        context.startActivity(intent)
    }
}