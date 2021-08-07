package com.skosc.pokedex.enity.domain

sealed class SearchResultIcon {

    class Pokemon(val imageUrl: String): SearchResultIcon()
}