package majestic.choiceSelect

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.SmartSelect
import majestic.choiceSelect.tools.ItemRow
import majestic.choiceSelect.tools.SelectedRow
import majestic.icons.Res
import majestic.icons.ic_arrow_down_01_solid
import org.jetbrains.compose.resources.DrawableResource

data class ChoiceFilterColors(
    val default: majestic.ColorPair,
    val choice: majestic.ChoiceColors,
    val popup: majestic.ColorPair
)

@Composable
fun ChoiceSelect(
    colors: ChoiceFilterColors,
    selected: List<String>,
    items: List<String>,
    icon: DrawableResource,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    arrowIcon: DrawableResource = Res.drawable.ic_arrow_down_01_solid,
    border: BorderStroke? = null,
    borderWhenHover: BorderStroke? = null,
    enableHover: Boolean = true
) {
    var isExpanded by remember { mutableStateOf(false) }
    SmartSelect(
        modifier = modifier,
        items = items,
        item = { item ->
            ItemRow(
                colors = colors,
                name = item,
                icon = icon,
                isSelected = selected.contains(item)
            )
        },
        selected = {
            SelectedRow(
                colors = colors.default,
                selected = selected,
                icon = icon,
                arrowIcon = arrowIcon,
                isExpanded = isExpanded,
                enableHover = enableHover
            )
        },
        placeholder = {
            SelectedRow(
                colors = colors.default,
                selected = selected,
                icon = icon,
                arrowIcon = arrowIcon,
                isExpanded = isExpanded,
                enableHover = enableHover
            )
        },
        onChange = { it?.let(onChange) },
        onExpanded = { isExpanded = it },
        border = if (enableHover && borderWhenHover != null && isExpanded)
            borderWhenHover else border,
        drawerContainerColor = colors.popup.background,
        shape = shape,
        dropDownShape = RoundedCornerShape(12.dp),
        dropdownModifier = Modifier.width(IntrinsicSize.Max)
    )
}