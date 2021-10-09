package com.skosc.pokedex.uikit.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

sealed interface ColorDef {

    object TextPrimaryAccent : ColorDef
    object TextSecondaryAccent : ColorDef
    object TextLink : ColorDef
    object AccentPrimary : ColorDef
    object AccentSecondary : ColorDef
    object AccentShadow : ColorDef
    object Background : ColorDef
    object BackgroundAccent : ColorDef
    object BackgroundShadow : ColorDef
    object TypeNormal : ColorDef
    object TypeFighting : ColorDef
    object TypeFlying : ColorDef
    object TypePoison : ColorDef
    object TypeGround : ColorDef
    object TypeRock : ColorDef
    object TypeBug : ColorDef
    object TypeGhost : ColorDef
    object TypeSteel : ColorDef
    object TypeFire : ColorDef
    object TypeWater : ColorDef
    object TypeGrass : ColorDef
    object TypeElectric : ColorDef
    object TypePsychic : ColorDef
    object TypeIce : ColorDef
    object TypeDragon : ColorDef
    object TypeDark : ColorDef
    object TypeFairy : ColorDef
    object TypeShadow : ColorDef
    object TypeUnknown : ColorDef

    class Value(val color: Color) : ColorDef
}