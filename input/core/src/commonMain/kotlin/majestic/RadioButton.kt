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

@Composable
fun RadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    label: String,
    selectedColor: Color = Color.Unspecified,
    selectedLabelColor: Color = Color.Unspecified,
    selectedBorderColor: Color = Color.Unspecified,
    unselectedColor: Color = Color.Unspecified,
    unselectedLabelColor: Color = Color.Unspecified,
    unselectedBorderColor: Color = Color.Unspecified,
    disabledColor: Color = Color.Unspecified,
    disabledLabelColor: Color = Color.Unspecified,
    disabledBorderColor: Color = Color.Unspecified,
    onSelect: () -> Unit
) {
    val backgroundColor: Color
    val labelColor: Color
    val borderColor: Color

    if (enabled && selected) {
        backgroundColor = selectedColor
        labelColor = selectedLabelColor
        borderColor = selectedBorderColor
    } else if (!selected) {
        backgroundColor = unselectedColor
        labelColor = unselectedLabelColor
        borderColor = unselectedBorderColor
    } else {
        backgroundColor = disabledColor
        labelColor = disabledLabelColor
        borderColor = disabledBorderColor
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
