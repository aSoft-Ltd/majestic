package majestic.users.tools.buttons


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.NoRippleInteractionSource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class ButtonAnimateColors(
    val hovered: ColorPair = ColorPair(
        foreground = Color(0xFFAB47BC),
        background = Color(0xFFAB47BC).copy(alpha = 0.2f)
    ),
    val inactive: ColorPair = ColorPair(
        foreground = Color.White.copy(alpha = 0.7f),
        background = Color.White.copy(alpha = 0.05f)
    ),
    val text: Color = Color.White.copy(alpha = 0.7f)
) {
    companion object {
        val default by lazy { ButtonAnimateColors() }
    }
}

@Composable
internal fun ButtonAnimate(
    label: String,
    icon: DrawableResource,
    onClick: () -> Unit,
    expanded: Boolean = true,
    isOpen: Boolean = false,
    colors: ButtonAnimateColors = ButtonAnimateColors.default,
    animationSpec: FiniteAnimationSpec<IntSize> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow,
    )
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor = if (isHovered) colors.hovered.background else colors.inactive.background
    val iconColor = if (isHovered) colors.hovered.foreground else colors.inactive.foreground

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .padding(10.dp)
            .animateContentSize(animationSpec)
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                enabled = true,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            tint = iconColor,
            contentDescription = "Icon",
            modifier = Modifier.size(24.dp)
        )

        if ((isHovered || isOpen) && expanded) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = label,
                color = colors.text,
                fontSize = 14.sp
            )
        }
    }
}