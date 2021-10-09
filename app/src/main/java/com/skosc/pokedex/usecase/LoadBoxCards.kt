package com.skosc.pokedex.usecase

import com.skosc.pokedex.*
import com.skosc.pokedex.core.localization.text.Text
import com.skosc.pokedex.enity.domain.MenuEntry
import com.skosc.pokedex.enity.ui.BoxCard
import com.skosc.pokedex.enity.ui.BoxCardList
import com.skosc.pokedex.repository.MenuRepository
import com.skosc.pokedex.core.resources.ResourceResolver
import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.navigation.root.RootDestination
import com.skosc.pokedex.uikit.coloristics.ColorPicker

class LoadBoxCards(
    private val menuRepository: MenuRepository,
    private val resources: ResourceResolver,
) {

    suspend operator fun invoke(): BoxCardList.Menu {
        return menuRepository.getAvailableMenuItems()
            .map(::toBoxCard)
            .let(BoxCardList::Menu)
    }

    private fun toBoxCard(entry: MenuEntry): BoxCard.Menu {
        return BoxCard.Menu(
            title = getTitle(entry),
            color = ColorPicker.getColorNoRepeat(NO_REPEAT_SPACE),
            destination = entry.getDestination()
        )
    }

    private fun MenuEntry.getDestination(): Destination = when(this) {
        MenuEntry.Pokemon -> root.pokemonList
        MenuEntry.Moves -> root.moveList
        MenuEntry.Items -> root.itemList
        else -> RootDestination
    }

    private fun getTitle(entry: MenuEntry): Text = when (entry) {
        MenuEntry.Pokemon -> Text.Resource(R.string.menu_box_pokemon)
        MenuEntry.Items -> Text.Resource(R.string.menu_box_items)
        MenuEntry.Moves -> Text.Resource(R.string.menu_box_moves)
        MenuEntry.Abilities -> Text.Resource(R.string.menu_box_abilities)
        MenuEntry.Locations -> Text.Resource(R.string.menu_box_locations)
    }

    companion object {

        private const val NO_REPEAT_SPACE = "LoadBoxCards"
    }
}