package com.skosc.pokedex.mini.typecalc

import android.app.Application
import android.content.Context
import com.skosc.pokedex.core.network.NetworkModule
import com.skosc.pokedex.domain.pokemon.PokemonDomainModule
import com.skosc.pokedex.domain.settings.SettingsDomainModule
import com.skosc.pokedex.feature.settings.SettingsFeatureModule
import com.skosc.pokedex.feature.typedetails.TypeDetailsModule
import org.kodein.di.*

class PokedexMiniTypeCalcApplication : Application(), DIAware {

    override val di: DI = DI.lazy {
        importOnce(NetworkModule)
        importOnce(SettingsFeatureModule)

        importOnce(PokemonDomainModule)
        importOnce(SettingsDomainModule)

        importOnce(TypeDetailsModule)

        bind<Context>() with instance(this@PokedexMiniTypeCalcApplication)
    }
}