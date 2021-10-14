package com.skosc.pokedex.domain.pokemon.entity

class PokemonWeaknessRelation(
    val type: PokemonTypeExpected,
    val other: PokemonTypeExpected,
    val magnitude: Magnitude
) {

    enum class Magnitude(val modifier: Float, val isTo: Boolean) {
        DOUBLE_FROM(2f, false),
        DOUBLE_TO(2f, true),
        HALF_FROM(0.5f, false),
        HALF_TO(0.5f, true),
        NO_FROM(0f, false),
        NO_TO(0f, false),

        QUAD_FROM(4f, false),
        QUAD_TO(4f, true),
        QUARTER_FROM(0.25f, false),
        QUARTER_TO(0.25f, true);

        operator fun plus(other: Magnitude?): Magnitude? {
            if (other == null) {
                return this
            }

            return if (isTo) {
                when(modifier * other.modifier) {
                    4f -> QUAD_TO
                    0.25f -> QUARTER_TO
                    0f -> NO_TO
                    else -> null
                }
            } else {
                when(modifier * other.modifier) {
                    4f -> QUAD_FROM
                    0.25f -> QUARTER_FROM
                    0f -> NO_FROM
                    else -> null
                }
            }
        }
    }
}