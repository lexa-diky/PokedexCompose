package com.skosc.pokedex.uikit.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColoristic = compositionLocalOf<Coloristic> { error("No color pallet set") }

data class Coloristic(
    val textPrimaryAccent: Color,
    val textSecondaryAccent: Color,
    val textLink: Color,
    val accentPrimary: Color,
    val accentSecondary: Color,
    val accentShadow: Color,
    val background: Color,
    val backgroundAccent: Color,
    val backgroundShadow: Color,
    val type: Type
) {

    fun from(def: ColorDef): Color = when(def) {
        ColorDef.TextPrimaryAccent -> textSecondaryAccent
        ColorDef.TextSecondaryAccent -> textSecondaryAccent
        ColorDef.TextLink -> textLink
        ColorDef.AccentPrimary -> accentPrimary
        ColorDef.AccentSecondary -> accentSecondary
        ColorDef.AccentShadow -> accentShadow
        ColorDef.Background -> background
        ColorDef.BackgroundAccent -> backgroundAccent
        ColorDef.BackgroundShadow -> backgroundShadow
        ColorDef.TypeNormal -> type.normal
        ColorDef.TypeFighting -> type.fighting
        ColorDef.TypeFlying -> type.flying
        ColorDef.TypePoison -> type.poison
        ColorDef.TypeGround -> type.ground
        ColorDef.TypeRock -> type.rock
        ColorDef.TypeBug -> type.bug
        ColorDef.TypeGhost -> type.ghost
        ColorDef.TypeSteel -> type.steel
        ColorDef.TypeFire -> type.fire
        ColorDef.TypeWater -> type.water
        ColorDef.TypeGrass -> type.grass
        ColorDef.TypeElectric -> type.electric
        ColorDef.TypePsychic -> type.psychic
        ColorDef.TypeIce -> type.ice
        ColorDef.TypeDragon -> type.dragon
        ColorDef.TypeDark -> type.dark
        ColorDef.TypeFairy -> type.fairy
        ColorDef.TypeShadow -> type.shadow
        ColorDef.TypeUnknown -> type.unknown
        is ColorDef.Value -> def.color
    }

    class Type(
        val normal: Color,
        val fighting: Color,
        val flying: Color,
        val poison: Color,
        val ground: Color,
        val rock: Color,
        val bug: Color,
        val ghost: Color,
        val steel: Color,
        val fire: Color,
        val water: Color,
        val grass: Color,
        val electric: Color,
        val psychic: Color,
        val ice: Color,
        val dragon: Color,
        val dark: Color,
        val fairy: Color,
        val shadow: Color,
        val unknown: Color
    )
}