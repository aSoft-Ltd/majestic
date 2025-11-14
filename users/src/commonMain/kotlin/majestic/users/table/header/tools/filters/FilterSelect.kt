package majestic.users.table.header.tools.filters

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.Select
import majestic.SelectColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class FilterSelectColors(
    val selector: SelectColors,
    val pop: ColorPair
)

@Composable
internal fun <T> FilterSelect(
    value: T?,
    items: List<T>,
    icon: DrawableResource,
    colors: FilterSelectColors,
    modifier: Modifier = Modifier,
    hint: String = "Select",
    onSelect: (T) -> Unit = {},
    option: @Composable (T) -> Unit = { Text("$it") }
) {
    var expanded by remember { mutableStateOf(false) }
    val icon = vectorResource(icon)
    val selectColors = colors.selector
    var selected by remember(value) { mutableStateOf(value) }
    val popCompColors = colors.pop

    Select(
        modifier = modifier,
        items = items,
        value = value,
        expanded = expanded,
        onExpanded = { expanded = it },
        colors = selectColors,
        icon = icon,
        option = option,
        onSelect = {
            selected = it
            onSelect(it)
        },
        placeholder = {
            ItemSelect(selectColors, it, icon) {
                Text(hint, color = popCompColors.foreground.copy(0.5f), fontSize = 12.sp)
            }
        },
        selected = { ItemSelect(selectColors, expanded, icon) { option(it) } }
    )
}

@Composable
private fun ItemSelect(
    colors: SelectColors,
    isExpanded: Boolean = false,
    icon: ImageVector,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .border(
            1.dp,
            color = if (isExpanded) colors.focused.border else colors.blurred.border,
            RoundedCornerShape(8.dp)
        )
        .padding(horizontal = 16.dp, vertical = 6.dp),
    content: @Composable () -> Unit
) {
    val animateRotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) { content() }
        Spacer(Modifier.width(8.dp))
        Icon(
            modifier = Modifier.size(12.dp).wrapContentSize().graphicsLayer { rotationX = animateRotation },
            imageVector = icon,
            contentDescription = "Dropdown Arrow",
            tint = if (isExpanded) colors.focused.text else colors.blurred.text
        )
    }
}
