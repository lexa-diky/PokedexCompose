package com.skosc.pokedex.domain.pokemon.entity

enum class PokemonTypeExpected(val defaultName: String) {
    Normal("normal"),
    Fighting("fighting"),
    Flying("flying"),
    Poison("poison"),
    Ground("ground"),
    Rock("rock"),
    Bug("bug"),
    Ghost("ghost"),
    Steel("steel"),
    Fire("fire"),
    Water("water"),
    Grass("grass"),
    Electric("electric"),
    Psychic("psychic"),
    Ice("ice"),
    Dragon("dragon"),
    Dark("dark"),
    Fairy("fairy"),
    Shadow("shadow"),
    Unknown("unknown");

    companion object {

        fun from(string: String): PokemonTypeExpected {
            return values().firstOrNull { it.defaultName == string } ?: Unknown
        }
    }
}