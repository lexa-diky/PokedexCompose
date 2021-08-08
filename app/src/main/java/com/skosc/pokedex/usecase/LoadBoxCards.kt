package com.skosc.pokedex.usecase

import com.skosc.pokedex.*
import com.skosc.pokedex.enity.domain.MenuEntry
import com.skosc.pokedex.enity.ui.BoxCard
import com.skosc.pokedex.enity.ui.BoxCardList
import com.skosc.pokedex.repository.ColorPalletResolver
import com.skosc.pokedex.repository.MenuRepository
import com.skosc.pokedex.core.resources.ResourceResolver
import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.navigation.ParcelableVoid
import com.skosc.pokedex.navigation.root.RootDestination

class LoadBoxCards(
    private val menuRepository: MenuRepository,
    private val resources: ResourceResolver,
    private val colorPalletResolver: ColorPalletResolver
) {

    suspend operator fun invoke(): BoxCardList.Menu {
        return menuRepository.getAvailableMenuItems()
            .map(::toBoxCard)
            .let(BoxCardList::Menu)
    }

    private fun toBoxCard(entry: MenuEntry): BoxCard.Menu {
        return BoxCard.Menu(
            title = getTitle(entry),
            color = colorPalletResolver.getColor(entry),
            destination = entry.getDestination()
        )
    }

    private fun MenuEntry.getDestination(): Destination<ParcelableVoid> = when(this) {
        MenuEntry.Pokemon -> root.pokemonList
        MenuEntry.Moves -> root.moveList
        MenuEntry.Items -> root.itemList
        else -> RootDestination
    }

    private fun getTitle(entry: MenuEntry): String = when (entry) {
        MenuEntry.Pokemon -> resources.getString(R.string.menu_box_pokemon)
        MenuEntry.Items -> resources.getString(R.string.menu_box_items)
        MenuEntry.Moves -> resources.getString(R.string.menu_box_moves)
        MenuEntry.Abilities -> resources.getString(R.string.menu_box_abilities)
        MenuEntry.Locations -> resources.getString(R.string.menu_box_locations)
    }
}