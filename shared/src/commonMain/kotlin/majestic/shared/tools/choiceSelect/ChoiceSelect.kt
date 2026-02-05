package majestic.shared.tools.choiceSelect

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.ChoiceColors
import majestic.ColorPair
import majestic.SmartSelect
import majestic.shared.tools.choiceSelect.tools.SelectOption
import majestic.shared.tools.choiceSelect.tools.SelectTrigger
import majestic.icons.Res
import majestic.icons.ic_arrow_down_01_solid
import org.jetbrains.compose.resources.DrawableResource

data class ChoiceFilterColors(
    val default: ColorPair,
    val choice: ChoiceColors,
    val popup: ColorPair
)

@Composable
fun ChoiceSelect(
    colors: ChoiceFilterColors,
    selected: List<String>,
    items: List<String>,
    emptyLabel: String = "Select",
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
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val activeHover = enableHover && isHovered
    val fg = if (activeHover) colors.default.foreground else colors.default.foreground.copy(0.7f)
    val bg = if (activeHover) colors.default.foreground.copy(0.1f)
    else colors.default.foreground.copy(0.05f)

    SmartSelect(
        modifier = modifier,
        items = items,
        item = { item ->
            SelectOption(
                colors = colors,
                name = item,
                icon = icon,
                isSelected = selected.contains(item),
                modifier = Modifier.fillMaxWidth()
            )
        },
        selected = {
            SelectTrigger(
                fg = fg,
                arrowTint = colors.default.foreground.copy(0.5f),
                selected = selected,
                icon = icon,
                emptyLabel = emptyLabel,
                arrowIcon = arrowIcon,
                isExpanded = isExpanded,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(bg)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .then(if (enableHover) Modifier.hoverable(interactionSource) else Modifier)
                    .padding(10.dp)
            )
        },
        placeholder = {
            SelectTrigger(
                fg = fg,
                arrowTint = colors.default.foreground.copy(0.5f),
                selected = selected,
                icon = icon,
                emptyLabel = emptyLabel,
                arrowIcon = arrowIcon,
                isExpanded = isExpanded,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(bg)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .then(if (enableHover) Modifier.hoverable(interactionSource) else Modifier)
                    .padding(10.dp)
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