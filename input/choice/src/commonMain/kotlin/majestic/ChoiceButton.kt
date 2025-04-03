package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.colors.ColorPair

data class ChoiceColorGroup(
    val background: Color,
    val label: Color,
    val border: Color,
    val icon: ColorPair
)

data class ChoiceColors(
    val selected: ChoiceColorGroup = ChoiceColorGroup(
        background = Color.Black,
        label = Color.White,
        border = Color.Transparent,
        icon = ColorPair(
            background = Color.White,
            foreground = Color.Black
        )
    ),
    val unselected: ChoiceColorGroup = ChoiceColorGroup(
        background = Color.Transparent,
        label = Color.White,
        border = Color.White.copy(0.1f),
        icon = ColorPair(
            background = Color.Transparent,
            foreground = Color.Transparent
        )
    )
)

@Composable
fun ChoiceButton(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ChoiceColors = ChoiceColors()
) {
    val color = if (selected) colors.selected else colors.unselected
    Row(
        modifier = modifier
            .clip(CircleShape)
            .border(1.dp, color.border, CircleShape)
            .background(color = color.background)
            .padding(10.dp)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                enabled = true,
                onClick = onSelect
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconCheckCircle(
            selected = selected,
            colors = IconCheckColors(
                background = color.icon.background,
                border = color.border,
                icon = color.icon
            )
        )
        Text(modifier = Modifier.padding(end = 5.dp), text = label, color = color.label)
    }
}
