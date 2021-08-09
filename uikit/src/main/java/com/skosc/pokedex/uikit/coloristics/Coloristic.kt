package com.skosc.pokedex.uikit.coloristics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.Color as AndroidColor
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import coil.Coil
import coil.ImageLoader
import coil.request.ImageRequest
import com.skosc.pokedex.uikit.theme.PokeColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object Coloristic {

    suspend fun getPokeColor(bitmap: Bitmap): Color {
        return withContext(Dispatchers.IO) {
            val palette = paletteForImage(bitmap)
            val vibrantSwatch = palette.selectCloseVibrant() ?: return@withContext PokeColor.Default
            Color(vibrantSwatch.rgb).copy(alpha = 1f)
        }
    }

    suspend fun getPokeColor(context: Context, bitmapUrl: String): Color {
        val bitmap = loadBitmap(context, bitmapUrl) ?: return PokeColor.Default
        return getPokeColor(bitmap)
    }

    private fun Palette.selectCloseVibrant(): Palette.Swatch? =
        vibrantSwatch ?: lightVibrantSwatch ?: dominantSwatch ?: swatches.firstOrNull()

    private suspend fun loadBitmap(context: Context, bitmapUrl: String): Bitmap? {
        val imageLoader = ImageLoader(context)
        return imageLoader.execute(
            ImageRequest.Builder(context)
                .data(bitmapUrl)
                .build()
        ).drawable?.toBitmap()?.copy(Bitmap.Config.RGB_565, false)
    }

    private suspend fun paletteForImage(bitmap: Bitmap): Palette = suspendCoroutine { con ->
        Palette.Builder(bitmap)
            .addTarget(Target.VIBRANT)
            .generate { palette ->
                if (palette == null) {
                    con.resumeWith(Result.failure(RuntimeException("Can't resolve colors")))
                } else {
                    con.resume(palette)
                }
            }
    }
}