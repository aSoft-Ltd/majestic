package majestic.payments.dashboard.filters

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.IconCheckCircle
import majestic.IconCheckColors
import majestic.SmartSelect
import majestic.ThemeColor
import majestic.payments.tools.colors.toChoiceColors
import majestic.payments.tools.colors.toPopMainColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_arrow_down_01_solid

@Composable
fun DataSelectFilter(
    theme: ThemeColor,
    selected: List<String>,
    items: List<String>,
    icon: DrawableResource,
    onChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val popColors = theme.toPopMainColors()

    SmartSelect(
        modifier = modifier,
        items = items,
        item = { item: String ->
            Item(
                theme = theme,
                name = item,
                icon = icon,
                isSelected = selected.contains(item)
            )
        },
        selected = { SelectedItem(colors = popColors, selected = selected, icon = icon, isExpanded = isExpanded) },
        placeholder = { SelectedItem(colors = popColors, selected = selected, icon = icon, isExpanded = isExpanded) },
        onChange = { it?.let(onChange) },
        onExpanded = { isExpanded = it },
        drawerContainerColor = popColors.background,
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
            .background(colors.background)
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
            tint = colors.foreground,
            contentDescription = null,
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = colors.foreground,
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
    theme: ThemeColor,
    name: String,
    icon: DrawableResource,
    isSelected: Boolean
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    val c = theme.toChoiceColors()
    val color = if (isSelected) c.selected else c.unselected

    Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(icon),
            tint = theme.surface.contra.color.copy(0.7f),
            contentDescription = null,
        )
        Text(text = name, fontSize = 14.sp, color = theme.surface.contra.color)
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
