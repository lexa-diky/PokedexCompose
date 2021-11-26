package com.skosc.pokedex.usecase

import com.skosc.pokedex.*
import com.skosc.pokedex.core.localization.text.Text
import com.skosc.pokedex.enity.domain.MenuEntry
import com.skosc.pokedex.enity.ui.BoxCard
import com.skosc.pokedex.enity.ui.BoxCardList
import com.skosc.pokedex.feature.pokemonlist.PokemonListPage
import com.skosc.pokedex.repository.MenuRepository
import com.skosc.pokedex.navigation.NoArg
import com.skosc.pokedex.navigation.Route
import com.skosc.pokedex.uikit.coloristics.ColorPicker

class LoadBoxCards(
    private val menuRepository: MenuRepository
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

    private fun MenuEntry.getDestination(): Route<NoArg> = when(this) {
        MenuEntry.Pokemon -> PokemonListPage
        MenuEntry.Moves -> PokemonListPage
        MenuEntry.Items -> PokemonListPage
        else -> PokemonListPage
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