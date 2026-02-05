package majestic.choiceSelect.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.IconCheckCircle
import majestic.choiceSelect.ChoiceFilterColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ItemRow(
    colors: ChoiceFilterColors,
    name: String,
    icon: DrawableResource,
    isSelected: Boolean
) {
    val state = if (isSelected) colors.choice.selected else colors.choice.unselected
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(icon),
                tint = colors.default.foreground.copy(0.7f),
                contentDescription = null
            )
            Text(
                text = name,
                fontSize = 14.sp,
                color = colors.default.foreground
            )
        }
        IconCheckCircle(
            size = 16.dp,
            selected = isSelected,
            colors = _root_ide_package_.majestic.IconCheckColors(
                background = state.icon.background,
                border = state.border,
                icon = state.icon
            )
        )
    }
}