package majestic.users.profile.permissions.detail

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class BreadCrumbTabColors(
    val tint: Color,
    val background: Color,
    val label: Color
)

@Composable
internal fun BreadCrumbTab(
    modifier: Modifier,
    icon: DrawableResource,
    label: String,
    colors: BreadCrumbTabColors,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        imageVector = vectorResource(icon),
        contentDescription = null,
        tint = colors.tint,
        modifier = Modifier
            .background(color = colors.background, shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
            .size(40.dp)
    )
    if (orientation is Landscape) Text(
        text = label,
        color = colors.label,
        fontSize = 16.sp,
    )
}