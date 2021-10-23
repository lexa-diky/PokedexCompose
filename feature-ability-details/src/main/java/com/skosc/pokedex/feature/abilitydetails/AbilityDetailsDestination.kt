package com.skosc.pokedex.feature.abilitydetails

import com.skosc.pokedex.navigation.Destination

object AbilityDetailsDestination : Destination {

    const val ARG_ABILITY_LINK = "ability_link"

    override val path: String = "/ability/details"
}