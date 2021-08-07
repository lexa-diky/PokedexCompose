package com.skosc.pokedex.core.resources

import android.content.Context
import androidx.annotation.StringRes

class ResourceResolver(private val context: Context) {

    fun getString(@StringRes resId: Int): String = context.getString(resId)
}
