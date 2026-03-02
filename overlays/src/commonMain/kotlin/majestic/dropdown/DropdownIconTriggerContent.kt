package majestic.dropdown

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

// icon-only dropdown trigger content similar to icon-only BasicButtonContent
@Composable
internal fun DropdownIconTriggerContent(
    icon: ImageVector,
    colors: DropdownColors,
    loading: Boolean = false,
    rotationTarget: Float? = null,
) {
    val foregroundColor = colors.triggerText.copy(alpha = 1f)

    if (loading) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(8.dp)
                .size(18.dp),
            color = foregroundColor,
            strokeWidth = 2.dp
        )
    }
    else {
        val rotation by animateFloatAsState(
            targetValue = rotationTarget ?: 0f,
            animationSpec = tween(300, easing = FastOutSlowInEasing),
            label = "Dropdown Icon Button Rotation"
        )
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = foregroundColor,
            modifier = Modifier
                .padding(8.dp)
                .size(18.dp)
                .graphicsLayer { rotationZ = rotation }
        )
    }
}