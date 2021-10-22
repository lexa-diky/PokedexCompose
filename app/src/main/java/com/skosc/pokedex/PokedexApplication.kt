package com.skosc.pokedex

import android.app.Application
import android.content.Context
import com.skosc.pokedex.core.network.NetworkModule
import com.skosc.pokedex.core.resources.ApplicationStaticConfiguration
import com.skosc.pokedex.di.RepositoryModule
import com.skosc.pokedex.di.ServiceModule
import com.skosc.pokedex.di.UseCaseModule
import com.skosc.pokedex.di.ViewModelModule
import com.skosc.pokedex.domain.ignition.IgnitionModule
import com.skosc.pokedex.domain.pokemon.PokemonDomainModule
import com.skosc.pokedex.domain.settings.SettingsDomainModule
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsModule
import com.skosc.pokedex.feature.settings.SettingsFeatureModule
import com.skosc.pokedex.feature.typedetails.TypeDetailsModule
import org.kodein.di.*

class PokedexApplication : Application(), DIAware {

    override val di: DI = DI.lazy {
        // Core
        importOnce(NetworkModule)

        // Feature
        importOnce(PokemonDetailsModule)
        importOnce(SettingsFeatureModule)
        importOnce(TypeDetailsModule)

        // Domin
        importOnce(PokemonDomainModule)
        importOnce(SettingsDomainModule)
        importOnce(IgnitionModule)

        // App
        importOnce(ViewModelModule)
        importOnce(RepositoryModule)
        importOnce(UseCaseModule)
        importOnce(ServiceModule)

        bind<Context>() with instance(this@PokedexApplication)
        bind<ApplicationStaticConfiguration>() with instance(
            ApplicationStaticConfiguration(
                newsApiKey = BuildConfig.NEWS_API_KEY
            )
        )
    }
}