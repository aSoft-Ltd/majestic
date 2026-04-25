package majestic.shared.loaders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.icons.Res
import majestic.icons.ic_google_gemini
import majestic.loaders.RevolvingLoader
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class LoaderColors(
    val revolver: ColorPair,
    val icon: ColorPair,
    val title: Color,
    val subtitle: Color
) {
    companion object {
        val default = LoaderColors(
            revolver = ColorPair(
                foreground = Color.White,
                background = Color.White.copy(.35f)
            ),
            icon = ColorPair(
                foreground = Color.White,
                background = Color.White.copy(.35f)
            ),
            title = Color.White,
            subtitle = Color.White.copy(.5f)
        )
    }
}

@Composable
fun Loader(
    colors: LoaderColors,
    title: String,
    subtitle: String,
    resource: DrawableResource? = null,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {
        Icon(
            imageVector = vectorResource(resource ?: Res.drawable.ic_google_gemini),
            contentDescription = null,
            tint = colors.icon.foreground,
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize()
                .background(color = colors.icon.background, shape = CircleShape)
                .padding(16.dp)
        )
    },
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
) {
    RevolvingLoader(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .size(100.dp),
        color = ColorPair(
            foreground = colors.revolver.foreground,
            background = colors.revolver.background
        )
    ) {
        icon()
    }
    Text(
        text = title,
        fontSize = 17.sp,
        textAlign = TextAlign.Center,
        color = colors.title
    )
    Text(
        text = subtitle,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center,
        color = colors.subtitle,
        modifier = Modifier.widthIn(350.dp)
    )
}
