package majestic.button.expandable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair

@Composable
internal fun ExpandableButtonContent(
    text: String,
    icon: ImageVector,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    isHovered: Boolean,
    orientation: ScreenOrientation,
    forceExpanded: Boolean = false,
    colors: ColorPair
) {
    val foreground = colors.foreground
    val shouldShowText = when {
        forceExpanded -> true
        orientation is Portrait -> false
        orientation is Landscape -> isHovered
        else -> true
    }
    val rotation by animateFloatAsState(
        targetValue = if (shouldShowText) 180f else 0f,
        animationSpec = tween(
            durationMillis = 450,
            easing = FastOutSlowInEasing
        ),
        label = "iconRotation"
    )

    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = foreground,
        modifier = Modifier
            .padding(9.dp)
            .size(18.dp)
            .graphicsLayer { rotationZ = rotation }
    )

    if (shouldShowText) Spacer(modifier = Modifier.width(8.dp))

    AnimatedVisibility(visible = shouldShowText) {
        Text(
            text = text,
            color = foreground,
            fontWeight = fontWeight,
            fontSize = fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    if (shouldShowText) Spacer(modifier = Modifier.width(8.dp))
}