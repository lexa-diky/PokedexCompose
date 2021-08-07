package com.skosc.pokedex.core.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object BrowserUtils {

    fun launch(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}