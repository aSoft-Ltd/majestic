package majestic.shared.button

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor

/**
 * Button composable that supports multiple variants, sizes, and icon configurations.
 *
 * Replaces: CircleButton, FlatButton, IconButton from majestic and IconButton imported from material, StickyButton
 *
 * Supports:
 * - Text-only buttons
 * - Icon-only buttons (automatically sized to be square)
 * - Icon + text combinations (leading and/or trailing icons)
 * - Expandable buttons (icon collapses to icon-only, expands on hover)
 * - Loading states (spinner replaces leading icon)
 * - Multiple variants (Primary, Secondary.Filled, Secondary.Transparent, Secondary.Outlined)
 * - Color tones (Default, Destructive, Success)
 *
 * @param label Optional button text. When null, renders as icon-only button.
 * @param onClick Callback invoked when button is clicked.
 * @param variant Visual style variant. Default is [ButtonVariant.Primary.Default].
 * @param size Button size affecting padding and icon dimensions.
 * @param enabled Whether the button is interactive. Disabled buttons show reduced opacity.
 * @param loading Shows spinner in leading icon position and disables interaction.
 * @param expandable When true, button shows icon-only by default and expands to show text on hover (desktop only).
 * @param forceExpanded Forces expandable button to stay expanded even when not hovered (useful for dropdown states).
 * @param orientation Required for expandable buttons to determine mobile vs desktop behavior.
 * @param leadingIcon Optional icon displayed before text. Supports rotation and DrawableResource or ImageVector.
 * @param trailingIcon Optional icon displayed after text. Also supports rotation and DrawableResource or ImageVector.
 * @param modifier Good old modifier.
 */
@Composable
fun Button(
    label: String? = null,
    onClick: () -> Unit,
    variant: ButtonVariant = ButtonVariant.Primary.Default,
    size: ButtonSize = ButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    expandable: Boolean = false,
    forceExpanded: Boolean = false,
    orientation: ScreenOrientation? = null,
    leadingIcon: (@Composable ButtonScope.() -> Unit)? = null,
    trailingIcon: (@Composable ButtonScope.() -> Unit)? = null,
    modifier: Modifier = Modifier.Companion,
    theme: ThemeColor
) {
    // expandable button validation
    require(!expandable || (leadingIcon != null && orientation != null)) {
        "Expandable button requires leadingIcon and orientation to be provided"
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val colors = theme.toButtonColors(variant, enabled)
    val isDisabled = !enabled || loading
    val cursorIcon = if (isDisabled) PointerIcon.Default else PointerIcon.Hand
    val shouldShowText = when {
        label == null -> false
        !expandable -> true
        orientation is Portrait -> false
        forceExpanded -> true
        orientation is Landscape -> isHovered
        else -> true
    }
    val showLeadingIcon = loading || leadingIcon != null
    val showTrailingIcon = trailingIcon != null && !loading && !expandable
    // logic for icon only button to have equal height and width
    val isIconOnly =
        (label == null || !shouldShowText) && (leadingIcon != null || trailingIcon != null)
    val finalPadding = if (isIconOnly) {
        val verticalPadding = size.toPadding().calculateTopPadding()
        PaddingValues(
            horizontal = verticalPadding,
            vertical = verticalPadding
        )
    } else {
        size.toPadding()
    }

    val scope = remember(isHovered, size, colors) {
        ButtonScope(colors, size, isHovered)
    }

    Box(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .clip(RoundedCornerShape(percent = 50))
            .background(colors.background)
            .then(
                if (colors.border != null) {
                    Modifier.border(
                        width = 1.dp,
                        color = colors.border,
                        shape = RoundedCornerShape(percent = 50)
                    )
                } else Modifier.Companion
            )
            .buttonHoverEffect(variant, enabled, isHovered, theme)
            .pointerHoverIcon(cursorIcon)
            .hoverable(interactionSource)
            .pointerInput(enabled, loading) {
                detectTapGestures {
                    if (enabled && !loading) {
                        onClick()
                    }
                }
            }
            .animateContentSize(
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(finalPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // leading icon or spinner
            if (showLeadingIcon) {
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(size.toDp()),
                        color = colors.foreground,
                        strokeWidth = 2.dp
                    )
                } else leadingIcon?.invoke(scope)

                if (shouldShowText) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
            // text
            if (shouldShowText && label !== null) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = (16 * 1.2).sp,
                    color = colors.foreground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            // trailing icon
            if (showTrailingIcon) {
                if (shouldShowText) {
                    Spacer(modifier = Modifier.weight(1f, fill = true).widthIn(min = 12.dp))
                }
                trailingIcon.invoke(scope)
            }
        }
    }
}

@Composable
internal fun ButtonScope.LeadingIcon(
    resource: ImageVector,
    description: String? = null,
    rotation: Float = 0f,
    modifier: Modifier = Modifier,
) = ButtonIcon(resource, description, rotation, modifier, size.toDp())

@Composable
internal fun ButtonScope.TrailingIcon(
    resource: ImageVector,
    description: String? = null,
    rotation: Float = 0f,
    modifier: Modifier = Modifier,
) = ButtonIcon(resource, description, rotation, modifier, 8.dp)

@Composable
private fun ButtonScope.ButtonIcon(
    resource: ImageVector,
    description: String? = null,
    rotation: Float = 0f,
    modifier: Modifier = Modifier,
    size: Dp,
) {
    val angle by animateFloatAsState(rotation)
    Icon(
        imageVector = resource,
        contentDescription = description,
        tint = colors.foreground,
        modifier = modifier
            .size(size)
            .graphicsLayer { rotationZ = angle }
    )
}