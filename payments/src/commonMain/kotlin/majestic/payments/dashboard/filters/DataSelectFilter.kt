package majestic.payments.dashboard.filters

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import majestic.ChoiceColors
import majestic.ColorPair
import majestic.IconCheckCircle
import majestic.IconCheckColors
import majestic.SmartSelect
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_arrow_down_01_solid

data class SelectFilterColors(
    val default: ColorPair,
    val choice: ChoiceColors,
    val popMain: ColorPair,
    val popComp: ColorPair,
)

@Composable
fun DataSelectFilter(
    colors: SelectFilterColors,
    selected: List<String>,
    items: List<String>,
    icon: DrawableResource,
    onChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    SmartSelect(
        modifier = modifier,
        items = items,
        item = { item: String ->
            Item(
                colors = colors,
                name = item,
                icon = icon,
                isSelected = selected.contains(item)
            )
        },
        selected = { SelectedItem(colors = colors.default, selected = selected, icon = icon, isExpanded = isExpanded) },
        placeholder = { SelectedItem(colors = colors.default, selected = selected, icon = icon, isExpanded = isExpanded) },
        onChange = { it?.let(onChange) },
        onExpanded = { isExpanded = it },
        drawerContainerColor = colors.popMain.background,
        shape = RoundedCornerShape(12.dp),
        dropDownShape = RoundedCornerShape(12.dp),
        dropdownModifier = Modifier.width(IntrinsicSize.Max)
    )
}

@Composable
private fun SelectedItem(
    colors: ColorPair,
    selected: List<String>,
    icon: DrawableResource,
    isExpanded: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val color = if (isHovered) colors.foreground else colors.foreground.copy(0.7f)
    val bgColor = if (isHovered) colors.foreground.copy(0.1f) else colors.foreground.copy(0.05f)
    val animateRotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    val label = when {
        selected.isEmpty() -> "Select"
        selected.size == 1 -> selected.first()
        else -> "${selected.size} Selected"
    }

    Row(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .pointerHoverIcon(PointerIcon.Hand)
            .hoverable(interactionSource)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 7.dp,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(icon),
            tint = color,
            contentDescription = null,
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = color,
            lineHeight = 0.1.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Icon(
            modifier = Modifier.size(16.dp).graphicsLayer { rotationX = animateRotation },
            painter = painterResource(Res.drawable.ic_arrow_down_01_solid),
            tint = colors.foreground.copy(0.5f),
            contentDescription = null,
        )
    }
}

@Composable
private fun Item(
    colors: SelectFilterColors,
    name: String,
    icon: DrawableResource,
    isSelected: Boolean
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    val color = if (isSelected) colors.choice.selected else colors.choice.unselected

    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(icon),
            tint = colors.default.foreground.copy(0.7f),
            contentDescription = null,
        )
        Text(text = name, fontSize = 14.sp, color = colors.default.foreground)
    }
    IconCheckCircle(
        size = 16.dp,
        selected = isSelected,
        colors = IconCheckColors(
            background = color.icon.background,
            border = color.border,
            icon = color.icon
        )
    )
}
