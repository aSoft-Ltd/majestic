package majestic.choiceSelect

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.CheckboxColors
import majestic.ChoiceColors
import majestic.ColorPair
import majestic.SmartSelect
import majestic.choiceSelect.tools.SelectOption
import majestic.choiceSelect.tools.SelectTrigger
import majestic.icons.Res
import majestic.icons.ic_arrow_down_01_solid
import org.jetbrains.compose.resources.DrawableResource

data class ChoiceFilterColors(
    val default: ColorPair,
    val choice: ChoiceColors,
    val popup: ColorPair
)

sealed interface OptionStyle {
    data object TrailingCheckCircle : OptionStyle
    data object LeadingCheckbox : OptionStyle
    data object TrailingCheck : OptionStyle
}

sealed interface TriggerLabelMode {
    data object Default : TriggerLabelMode
    data object LabelWithCount : TriggerLabelMode
}

@Composable
fun ChoiceSelect(
    orientation: ScreenOrientation = Landscape,
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
    enableHover: Boolean = true,
    optionStyle: OptionStyle = OptionStyle.TrailingCheckCircle,
    labelMode: TriggerLabelMode = TriggerLabelMode.Default,
    dropdownWidth: Boolean = true,
    checkboxColors: CheckboxColors? = null,
    selectedLabelColor: Color? = null,
    checkmarkColor: Color? = null
) {
    var isExpanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val activeHover = enableHover && isHovered
    val fg = if (activeHover) colors.default.foreground else colors.default.foreground.copy(0.7f)
    val bg = if (activeHover) colors.default.foreground.copy(0.1f) else colors.default.foreground.copy(0.05f)
    var triggerWidthPx by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val dropdownMod = if (dropdownWidth && triggerWidthPx > 0) Modifier.width(with(density) { triggerWidthPx.toDp() }) else Modifier

    SmartSelect(
        modifier = modifier,
        items = items,
        item = { item ->
            SelectOption(
                orientation = orientation,
                colors = colors,
                name = item,
                icon = icon,
                isSelected = selected.contains(item),
                style = optionStyle,
                onToggle = { onChange(item) },
                checkboxColors = checkboxColors,
                selectedLabelColor = selectedLabelColor,
                deliverabilityCheckmarkColor = checkmarkColor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        selected = {
            SelectTrigger(
                orientation = orientation,
                foreground = fg,
                arrowTint = colors.default.foreground.copy(0.5f),
                selected = selected,
                icon = icon,
                emptyLabel = emptyLabel,
                arrowIcon = arrowIcon,
                isExpanded = isExpanded,
                labelMode = labelMode,
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { triggerWidthPx = it.width }
                    .clip(RoundedCornerShape(12.dp))
                    .background(bg)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .then(if (enableHover) Modifier.hoverable(interactionSource) else Modifier)
                    .padding(10.dp)
            )
        },
        placeholder = {
            SelectTrigger(
                orientation = orientation,
                foreground = fg,
                arrowTint = colors.default.foreground.copy(0.5f),
                selected = selected,
                icon = icon,
                emptyLabel = emptyLabel,
                arrowIcon = arrowIcon,
                isExpanded = isExpanded,
                labelMode = labelMode,
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { triggerWidthPx = it.width }
                    .clip(RoundedCornerShape(12.dp))
                    .background(bg)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .then(if (enableHover) Modifier.hoverable(interactionSource) else Modifier)
                    .padding(10.dp)
            )
        },
        onChange = { it?.let(onChange) },
        onExpanded = { isExpanded = it },
        border = if (enableHover && borderWhenHover != null && isExpanded) borderWhenHover else border,
        drawerContainerColor = colors.popup.background,
        shape = shape,
        dropDownShape = RoundedCornerShape(12.dp),
        dropdownModifier = dropdownMod
    )
}