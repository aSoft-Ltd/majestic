package majestic.shared.profiles.contacts.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.NoRippleInteractionSource
import org.jetbrains.compose.resources.painterResource
import majestic.icons.Res
import majestic.icons.ic_tick_solid

@Composable
internal fun ButtonChoice(
    label: String,
    selected: Boolean,
    color: ColorPair = ColorPair(
        foreground = Color.White,
        background = Color.Black
    ),
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val bgColor = when {
        selected -> color.background
        isHovered -> color.background.copy(alpha = 0.3f)
        else -> Color.Transparent
    }
    val textColor = when {
        selected -> color.foreground
        isHovered -> color.foreground.copy(alpha = 0.8f)
        else -> color.foreground.copy(alpha = 0.5f)
    }
    val borderColor = when {
        selected -> Color.Transparent
        else -> color.foreground.copy(alpha = 0.1f)
    }

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource = interactionSource)
            .padding(10.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        icon()
        Text(
            text = label,
            color = textColor,
            lineHeight = 18.sp
        )
        if (selected) Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(Res.drawable.ic_tick_solid),
            tint = textColor,
            contentDescription = "Icon"
        )
    }
}