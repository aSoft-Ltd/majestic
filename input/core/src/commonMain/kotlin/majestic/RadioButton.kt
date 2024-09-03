package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp

data class RadioColorGroup(
    val background: Color,
    val label: Color,
    val border: Color
) {
    companion object {
        val default by lazy {
            RadioColorGroup(Color.Unspecified, Color.Unspecified, Color.Unspecified)
        }
    }
}

data class RadioColors(
    val selected: RadioColorGroup = RadioColorGroup.default,
    val unselected: RadioColorGroup = RadioColorGroup.default,
    val disabled: RadioColorGroup = RadioColorGroup.default
)

@Composable
fun RadioButton(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: RadioColors = RadioColors()
) {
    val backgroundColor: Color
    val labelColor: Color
    val borderColor: Color

    if (enabled && selected) {
        backgroundColor = colors.selected.background
        labelColor = colors.selected.label
        borderColor = colors.selected.border
    } else if (!selected) {
        backgroundColor = colors.unselected.background
        labelColor = colors.unselected.label
        borderColor = colors.unselected.border
    } else {
        backgroundColor = colors.disabled.background
        labelColor = colors.disabled.label
        borderColor = colors.disabled.border
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .background(color = backgroundColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
                enabled = enabled,
                onClick = onSelect
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(all = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selected) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.CheckCircle,
                    tint = labelColor,
                    contentDescription = "Selected Icon"
                )
            }
            Text(
                text = label,
                color = labelColor
            )
        }
    }
}
