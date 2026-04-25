package majestic.shared.tools

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
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
import majestic.SmartSelect
import majestic.ThemeColor
import majestic.icons.Res
import majestic.icons.ic_arrow_down
import org.jetbrains.compose.resources.painterResource

/**
 * A generic selection component that wraps [SmartSelect] with a consistent UI style.
 *
 * @param T The type of items to select from.
 * @param items List of available options.
 * @param selected The currently selected item.
 * @param onSelect Callback when a new item is selected.
 * @param label A lambda to convert the item [T] into a displayable string.
 * @param theme The application [ThemeColor].
 */
@Composable
fun <T> PageSelection(
    items: List<T>,
    selected: T,
    onSelect: (T) -> Unit,
    label: (T) -> String,
    theme: ThemeColor,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    SmartSelect(
        modifier = modifier,
        dropdownModifier = Modifier.padding(8.dp),
        items = items,
        item = { item ->
            SelectionItemRow(
                color = ColorPair(
                    foreground = theme.surface.contra.color,
                    background = theme.dominant.actual.color
                ),
                text = label(item),
                isSelected = item == selected
            )
        },
        selected = { SelectedItemView(theme, label(selected), isExpanded) },
        placeholder = { SelectedItemView(theme, label(selected), isExpanded) },
        value = selected,
        onChange = { it?.let(onSelect) },
        onExpanded = { isExpanded = it },
        drawerContainerColor = theme.surface.actual.color,
        shape = RoundedCornerShape(12.dp),
        dropDownShape = RoundedCornerShape(12.dp)
    )
}

@Composable
private fun SelectedItemView(
    theme: ThemeColor,
    text: String,
    isExpanded: Boolean = false
) {
    val animateRotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    Row(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(theme.surface.contra.color.copy(alpha = 0.05f))
            .padding(vertical = 10.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = theme.surface.contra.color,
            lineHeight = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            modifier = Modifier.size(10.dp).graphicsLayer { rotationZ = animateRotation },
            painter = painterResource(Res.drawable.ic_arrow_down),
            tint = theme.surface.contra.color.copy(0.5f),
            contentDescription = null,
        )
    }
}

@Composable
private fun SelectionItemRow(
    color: ColorPair,
    text: String,
    isSelected: Boolean
) = Row(
    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 4.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = color.foreground,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        modifier = Modifier.weight(1f)
    )
    if (isSelected) Icon(
        modifier = Modifier.size(16.dp),
        imageVector = Icons.Filled.CheckCircle,
        tint = color.background,
        contentDescription = null,
    )
}
