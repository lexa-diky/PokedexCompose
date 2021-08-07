package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.skosc.pokedex.core.util.BrowserUtils
import com.skosc.pokedex.uikit.util.formatLocal
import kotlinx.datetime.Instant

@Composable
fun NewsBlock(title: String, time: Instant, image: String, url: String) {
    val context = LocalContext.current

    Box(modifier = Modifier
        .padding(horizontal = 16.dp)
        .clickable {
            BrowserUtils.launch(context, url)
        }) {
        Column(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Text(
                        text = time.formatLocal(),
                        fontSize = 12.sp,
                    )
                }

                Image(
                    painter = rememberImagePainter(
                        data = image,
                        builder = {
                            crossfade(true)
                            transformations(RoundedCornersTransformation(16f))
                        }
                    ),
                    contentDescription = title,
                    modifier = Modifier
                        .size(64.dp * 1.6f, 64.dp)
                        .weight(0.5f)
                )
            }

            Divider(modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 8.dp))
        }
    }
}
