package majestic.shared.button

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

@Composable
fun Modifier.buttonHoverEffect(
    variant: ButtonVariant,
    enabled: Boolean,
    isHovered: Boolean,
    theme: ThemeColor
): Modifier {
    val tintColor = when (variant) {
        is ButtonVariant.Primary.Destructive,
        is ButtonVariant.Secondary.Opaque.Destructive,
        is ButtonVariant.Secondary.Transparent.Destructive,
        is ButtonVariant.Secondary.Outlined.Destructive -> Color(theme.destructive.value)

        is ButtonVariant.Primary.Success,
        is ButtonVariant.Secondary.Opaque.Success,
        is ButtonVariant.Secondary.Transparent.Success,
        is ButtonVariant.Secondary.Outlined.Success -> Color(theme.success.value)

        else -> when (theme) {
            is Light -> theme.dominant.actual.color
            is Dark -> theme.surface.contra.color
        }
    }
    val primaryHoverAlpha by animateFloatAsState(
        targetValue = if (isHovered && enabled) 0.8f else 1f,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
    )
    val secondaryFilledAlpha by animateFloatAsState(
        targetValue = if (isHovered && enabled) 0.09f else 0.07f,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
    )
    val secondaryTransparentAlpha by animateFloatAsState(
        targetValue = if (isHovered && enabled) 0.07f else 0f,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
    )
    val secondaryOutlinedAlpha by animateFloatAsState(
        targetValue = if (isHovered && enabled) 0.05f else 0f,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
    )

    return this.then(
        when (variant) {
            is ButtonVariant.Primary -> {
                Modifier.graphicsLayer {
                    alpha = primaryHoverAlpha
                }
            }

            is ButtonVariant.Secondary.Opaque -> {
                Modifier.drawWithContent {
                    drawContent()
                    drawRect(
                        color = tintColor.copy(alpha = secondaryFilledAlpha)
                    )
                }
            }

            is ButtonVariant.Secondary.Transparent -> {
                Modifier.drawWithContent {
                    drawContent()
                    drawRect(
                        color = tintColor.copy(alpha = secondaryTransparentAlpha)
                    )
                }
            }

            is ButtonVariant.Secondary.Outlined -> {
                Modifier.drawWithContent {
                    drawContent()
                    if (secondaryOutlinedAlpha > 0f) {
                        drawRect(
                            color = tintColor.copy(alpha = secondaryOutlinedAlpha)
                        )
                    }
                }
            }
        }
    )
}