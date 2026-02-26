package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_info_circle
import org.jetbrains.compose.resources.painterResource

data class HeaderColors(
    val background: Color,
    val title: Color,
    val icon: Color
)

internal fun Modifier.toHeader(
    orientation: ScreenOrientation,
    colors: RoleColors
) = when (orientation) {
    is Landscape -> this.padding(bottom = 8.dp, start = 16.dp, end = 16.dp, top = 16.dp)
    is Portrait -> this
        .fillMaxWidth()
        .background(colors.header.background)
        .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
}

@Composable
internal fun Header(
    modifier: Modifier,
    orientation: ScreenOrientation,
    colors: HeaderColors,
    header: String
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    Text(
        text = header,
        color = colors.title,
        fontSize = 16.sp,
    )
    if (orientation is Landscape) Icon(
        painter = painterResource(Res.drawable.ic_info_circle),
        contentDescription = "Info",
        tint = colors.icon,
        modifier = Modifier.size(16.dp)
    )
}