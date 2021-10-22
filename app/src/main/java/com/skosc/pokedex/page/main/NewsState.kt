package com.skosc.pokedex.page.main

import com.skosc.pokedex.enity.ui.NewsBriefingEntry

/**
 * @author a.l.yakovlev
 */
sealed interface NewsState {

    object Loading : NewsState
    class Loaded(val entries: List<NewsBriefingEntry>) : NewsState
    object Error : NewsState
}