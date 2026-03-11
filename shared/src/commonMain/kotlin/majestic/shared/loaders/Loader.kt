package majestic.shared.loaders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.ThemeColor
import majestic.icons.Res
import majestic.icons.ic_google_gemini
import majestic.loaders.RevolvingLoader
import org.jetbrains.compose.resources.painterResource

@Composable
fun Loader(
    theme: ThemeColor,
    title: String = "Submitting…",
    subtitle: String = "Please wait while we complete your request.",
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    RevolvingLoader(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .size(120.dp),
        color = ColorPair(
            foreground = theme.dominant.contra.color,
            background = theme.dominant.actual.color
        )
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_google_gemini),
            contentDescription = null,
            tint = theme.surface.contra.color,
            modifier = Modifier.size(32.dp)
        )
    }
    Text(
        text = title,
        fontSize = 17.sp,
        textAlign = TextAlign.Center,
        color = theme.surface.contra.color
    )
    Text(
        text = subtitle,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        textAlign = TextAlign.Center,
        color = theme.surface.contra.color.copy(alpha = 0.5f)
    )
}
