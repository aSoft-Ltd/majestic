package majestic.dropdown

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_arrow_down
import org.jetbrains.compose.resources.vectorResource

// dropdown trigger content composable similar to BasicButtonContent
@Composable
internal fun DropdownTriggerContent(
    label: String,
    leadingIcon: ImageVector?,
    colors: DropdownColors,
    loading: Boolean = false,
    expanded: Boolean = false,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    iconSize: Dp = 18.dp,
    arrowTint: Color? = null
) {
    val resolvedArrowTint = arrowTint ?: colors.triggerText

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = label,
                tint = colors.triggerText.copy(alpha = 0.7f),
                modifier = Modifier.size(iconSize)
            )
        }

        Text(
            text = label,
            color = colors.triggerText,
            fontWeight = fontWeight,
            fontSize = fontSize,
            maxLines = 1,
            lineHeight = 1.sp,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.weight(1f))

        // trailing icon area - either loading spinner or arrow
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(14.dp),
                color = colors.triggerText,
                strokeWidth = 2.dp
            )
        } else {
            val rotation by animateFloatAsState(
                targetValue = if (expanded) 180f else 0f,
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                label = "Dropdown Arrow Rotation"
            )
            Icon(
                imageVector = vectorResource(Res.drawable.ic_arrow_down),
                contentDescription = "Toggle",
                tint = resolvedArrowTint,
                modifier = Modifier
                    .size(12.dp)
                    .graphicsLayer { rotationZ = rotation }
            )
        }
    }
}
