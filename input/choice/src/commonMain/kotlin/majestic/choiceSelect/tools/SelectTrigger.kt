package majestic.choiceSelect.tools

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.choiceSelect.TriggerLabelMode
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SelectTrigger(
    orientation: ScreenOrientation,
    foreground: Color,
    arrowTint: Color,
    selected: List<String>,
    icon: DrawableResource,
    emptyLabel: String,
    arrowIcon: DrawableResource,
    isExpanded: Boolean,
    labelMode: TriggerLabelMode,
    modifier: Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(300)
    )
    val label = when (labelMode) {
        TriggerLabelMode.Default -> when {
            selected.isEmpty() -> emptyLabel
            selected.size == 1 -> selected.first()
            else -> "${selected.size} Selected"
        }
        TriggerLabelMode.LabelWithCount -> when {
            selected.isEmpty() -> emptyLabel
            else -> "$emptyLabel (${selected.size})"
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.width(16.dp),
            painter = painterResource(icon),
            tint = foreground,
            contentDescription = null
        )
        Text(
            text = label,
            fontSize = if (orientation is Landscape) 14.sp else 12.sp,
            color = foreground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 7.dp).weight(1f, fill = true)
        )
        Icon(
            modifier = Modifier
                .width(16.dp)
                .graphicsLayer { rotationX = rotation },
            painter = painterResource(arrowIcon),
            tint = arrowTint,
            contentDescription = null
        )
    }
}