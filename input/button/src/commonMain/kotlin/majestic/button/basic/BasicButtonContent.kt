package majestic.button.basic

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair

@Composable
private fun LoadingWrapper(
    loading: Boolean,
    color: Color,
    size: Dp = 18.dp,
    content: @Composable () -> Unit
) = Box(contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.graphicsLayer { alpha = if (loading) 0f else 1f }) {
            content()
        }

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(size),
                color = color,
                strokeWidth = 2.dp
            )
        }
    }

// overload for text only button
@Composable
fun BasicButtonContent(
    text: String,
    colors: ColorPair,
    loading: Boolean = false,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    alpha: Float = 1f,
) = LoadingWrapper(
        loading = loading,
        color = colors.foreground
    ) {
        Text(
            text = text,
            color = colors.foreground.copy(alpha = alpha),
            fontWeight = fontWeight,
            fontSize = fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }

// overload for icon only button
@Composable
fun BasicButtonContent(
    icon: ImageVector,
    colors: ColorPair,
    loading: Boolean = false,
    rotation: Float? = null,
    alpha: Float = 1f,
) = LoadingWrapper(
        loading = loading,
        color = colors.foreground
    ) {
        val angle by animateFloatAsState(
            targetValue = rotation ?: 0f,
            animationSpec = tween(300, easing = FastOutSlowInEasing),
            label = "Basic Button Icon Rotation"
        )
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colors.foreground.copy(alpha = alpha),
            modifier = Modifier
                .padding(8.dp)
                .size(18.dp)
                .graphicsLayer { rotationZ = angle }
        )
    }

// overload for text with icons either leading or trailing
@Composable
fun BasicButtonContent(
    text: String,
    colors: ColorPair,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    loading: Boolean = false,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    leadingIconAlpha: Float = 1f,
    textAlpha: Float = 1f,
) = LoadingWrapper(
        loading = loading,
        color = colors.foreground
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            leadingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = colors.foreground.copy(alpha = leadingIconAlpha)
                )
                Spacer(Modifier.width(8.dp))
            }

            Text(
                text = text,
                color = colors.foreground.copy(alpha = textAlpha),
                fontWeight = fontWeight,
                fontSize = fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            trailingIcon?.let {
                Spacer(Modifier.width(8.dp))
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = colors.foreground
                )
            }
        }
    }

// overload for button with status beacon
@Composable
fun BasicButtonContent(
    text: String,
    colors: ColorPair,
    statusBeacon: Color,
    loading: Boolean = false,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textAlpha: Float = 1f,
) = LoadingWrapper(
    loading = loading,
    color = colors.foreground
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
    ) {
        Text(
            text = text,
            color = colors.foreground.copy(alpha = textAlpha),
            fontWeight = fontWeight,
            fontSize = fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(statusBeacon)
        )
    }
}