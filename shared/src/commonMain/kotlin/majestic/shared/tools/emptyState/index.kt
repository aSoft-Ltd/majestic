package majestic.shared.tools.emptyState

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.button.appearence.plusButton
import majestic.button.basic.PlusButton
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun EmptyState(
    icon: DrawableResource,
    title: String,
    description: String,
    colors: EmptyStateColors,
    action: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    actionContent: @Composable (() -> Unit)? = null
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    HaloGradientIcon(icon = icon, colors = colors, modifier = Modifier.size(86.dp))

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = colors.foreground,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = description,
            color = colors.foreground.copy(alpha = 0.5f),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(360.dp)
        )
    }

    if (actionContent != null) actionContent()
    else if (action != null) PlusButton(
        modifier = Modifier.plusButton(colors = colors.button, onClick = action)
    )
}