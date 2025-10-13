package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import majestic.tooling.onClick

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

fun Modifier.choiceButton(background: Color, border: Color) = clip(CircleShape)
    .border(1.dp, border, CircleShape)
    .background(color = background)
    .padding(10.dp)
    .pointerHoverIcon(PointerIcon.Hand)

fun Modifier.choiceButton(selected: Boolean, colors: ChoiceColors = ChoiceColors()) = choiceButton(
    background = if (selected) colors.selected.background else colors.unselected.background,
    border = if (selected) colors.selected.border else colors.unselected.border,
)

@Composable
fun ChoiceButton(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit = {},
    colors: ChoiceColors = ChoiceColors(),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    modifier: Modifier = Modifier.choiceButton(selected, colors)
) {
    val color = if (selected) colors.selected else colors.unselected
    Row(
        modifier = modifier.onClick {
            if (!selected) onSelect()
        },
        horizontalArrangement = horizontalArrangement,
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
        Spacer(Modifier.width(5.dp))
        Text(
            modifier = Modifier.padding(end = 5.dp),
            text = label,
            color = color.label,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
fun ChoiceButton(
    label: @Composable (Boolean) -> Unit,
    selected: Boolean,
    colors: ChoiceColors = ChoiceColors(),
    modifier: Modifier = Modifier.choiceButton(selected, colors)
) {
    val color = if (selected) colors.selected else colors.unselected
    Row(
        modifier = modifier,
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
        label(selected)
    }
}