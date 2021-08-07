package com.skosc.pokedex.uikit

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.kodein.di.compose.LocalDI
import org.kodein.di.direct
import org.kodein.di.instance

@Composable
inline fun <reified VM : ViewModel> diViewModel(): VM {
    val di = LocalDI.current.direct
    val storeOwner = LocalViewModelStoreOwner.current!!

    val provider = ViewModelProvider(storeOwner, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = di.instance<VM>() as T
    })

    return provider.get(VM::class.java)
}