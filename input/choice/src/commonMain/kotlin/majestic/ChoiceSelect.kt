package majestic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_arrow_down_01_solid
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

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

@Composable
private fun SelectedRow(
    colors: ColorPair,
    selected: List<String>,
    icon: DrawableResource,
    arrowIcon: DrawableResource,
    isExpanded: Boolean,
    enableHover: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val activeHover = enableHover && isHovered
    val fg = if (activeHover) colors.foreground else colors.foreground.copy(0.7f)
    val bg = if (activeHover) colors.foreground.copy(0.1f)
    else colors.foreground.copy(0.05f)
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(300)
    )
    val label = when {
        selected.isEmpty() -> "Select"
        selected.size == 1 -> selected.first()
        else -> "${selected.size} Selected"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .pointerHoverIcon(PointerIcon.Hand)
            .then(if (enableHover) Modifier.hoverable(interactionSource) else Modifier)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(icon),
            tint = fg,
            contentDescription = null
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = fg,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            modifier = Modifier
                .size(16.dp)
                .graphicsLayer { rotationX = rotation },
            painter = painterResource(arrowIcon),
            tint = colors.foreground.copy(0.5f),
            contentDescription = null
        )
    }
}


@Composable
private fun ItemRow(
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
            colors = IconCheckColors(
                background = state.icon.background,
                border = state.border,
                icon = state.icon
            )
        )
    }
}
