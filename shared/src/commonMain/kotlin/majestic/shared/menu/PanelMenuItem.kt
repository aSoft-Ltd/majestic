package majestic.shared.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.tools.rememberHoverBackground
import majestic.tooling.onClick

data class PanelMenuItemColors(
    val panelBackground: Color,
    val foreground: Color,
)

@Composable
fun Modifier.panelMenuItem(colors: PanelMenuItemColors, onClick: () -> Unit): Modifier {
    val (background, interaction) = rememberHoverBackground(colors.panelBackground, colors.foreground, 0.2f)

    return this
        .fillMaxWidth()
        .clip(RoundedCornerShape(7.dp))
        .onClick { onClick() }
        .background(background)
        .hoverable(interaction)
        .padding(horizontal = 13.dp, vertical = 6.dp)
}

@Composable
fun PanelMenuItem(
    icon: ImageVector,
    label: String,
    colors: PanelMenuItemColors,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(17.dp),
            tint = colors.foreground.copy(0.6f)
        )
        Text(
            text = label,
            fontSize = 13.sp,
            color = colors.foreground.copy(0.6f)
        )
    }
}