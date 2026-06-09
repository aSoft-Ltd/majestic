package majestic.shared.tools.emptyState

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.button.appearence.plusButton
import majestic.button.basic.PlusButton
import org.jetbrains.compose.resources.DrawableResource

data class EmptyStateColors(
    val foreground: Color,
    val button: ColorPair,
    val iconBlur: Color,
    val gradient: Pair<Color, Color>,
)

@Composable
fun EmptyState(
    icon: DrawableResource,
    title: String,
    description: String,
    colors: EmptyStateColors,
    width: Dp = 224.dp,
    action: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GradientIcon(icon = icon, colors = colors, modifier = Modifier.size(83.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    color = colors.foreground,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = description,
                    color = colors.foreground.copy(alpha = 0.5f),
                    fontSize = 10.sp,
                    lineHeight = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(width)
                )
            }
        }

        if (action != null) {
            PlusButton(
                modifier = Modifier.plusButton(
                    colors = colors.button,
                    onClick = action,
                )
            )
        }
    }
}