package majestic.shared.profiles.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class BreadCrumbTabColors(
    val background: Color,
    val icon: ColorPair,
    val label: Color
)

@Composable
internal fun BreadCrumbTab(
    modifier: Modifier,
    icon: DrawableResource,
    label: String,
    orientation: ScreenOrientation,
    colors: BreadCrumbTabColors,
    showLabel: Boolean
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        imageVector = vectorResource(icon),
        contentDescription = null,
        tint = colors.icon.foreground,
        modifier = Modifier
            .background(color = colors.icon.background, shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
            .size(24.dp)
    )
    if (showLabel) Text(
        text = label,
        color = colors.label,
        fontSize = 16.sp,
        maxLines = 1,
        overflow = if (orientation is Portrait) TextOverflow.MiddleEllipsis else TextOverflow.Visible
    )
}