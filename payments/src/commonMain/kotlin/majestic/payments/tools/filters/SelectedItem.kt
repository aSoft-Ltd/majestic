package majestic.payments.tools.filters

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_arrow_down

data class SelectFilterColors(
    val background: Color,
    val text: Color,
    val icon: Color
) {
    companion object {
        val Default = SelectFilterColors(
            background = Color.Transparent,
            text = Color.Transparent,
            icon = Color.Transparent
        )
    }
}

@Composable
internal fun SelectedItem(
    defaults: SelectFilterColors,
    label: String,
    icon: DrawableResource,
    isExpanded: Boolean = false,
    hasIcon: Boolean = true
) {
    val animateRotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    Row(
        modifier = Modifier.fillMaxWidth().clip(CircleShape).background(defaults.background)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 5.dp)
        ) {
            if (hasIcon) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(icon),
                    tint = defaults.text,
                    contentDescription = null,
                )
            }
            Text(
                text = label,
                fontSize = 14.sp,
                color = defaults.text,
                lineHeight = 0.1.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Icon(
            modifier = Modifier.size(10.dp).graphicsLayer { rotationX = animateRotation },
            painter = painterResource(Res.drawable.ic_arrow_down),
            tint = defaults.icon.copy(0.5f),
            contentDescription = null,
        )
    }
}
