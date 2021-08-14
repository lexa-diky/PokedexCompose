package com.skosc.pokedex

import android.app.Application
import android.content.Context
import com.skosc.pokedex.core.network.NetworkModule
import com.skosc.pokedex.di.RepositoryModule
import com.skosc.pokedex.di.ServiceModule
import com.skosc.pokedex.di.UseCaseModule
import com.skosc.pokedex.di.ViewModelModule
import com.skosc.pokedex.repository.ColorPalletResolver
import com.skosc.pokedex.core.resources.ResourceResolver
import com.skosc.pokedex.domain.pokemon.PokemonDomainModule
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsModule
import org.kodein.di.*

class PokedexApplication : Application(), DIAware {

    override val di: DI = DI.lazy {
        // Core
        importOnce(NetworkModule)

        // Feature
        importOnce(PokemonDetailsModule)

        // Domin
        importOnce(PokemonDomainModule)

        // App
        importOnce(ViewModelModule)
        importOnce(RepositoryModule)
        importOnce(UseCaseModule)
        importOnce(ServiceModule)

        bind<Context>() with instance(this@PokedexApplication)
        bind<ResourceResolver>() with singleton { ResourceResolver(instance()) }
        bind<ColorPalletResolver>() with singleton { ColorPalletResolver() }
    }
}