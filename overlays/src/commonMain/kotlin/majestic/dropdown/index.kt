package majestic.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_check
import majestic.icons.ic_circle
import majestic.icons.ic_circle_check

data class DropdownItem<T>(
    val value: T,
    val label: String,
    val leadingIcon: ImageVector? = null,
    val isDestructive: Boolean = false
)

internal enum class DropdownMode { Single, Multi, Action }

/**
 * single select overload
 */
@Composable
fun <T> Dropdown(
    items: List<DropdownItem<T>>,
    selected: T?,
    onSelection: (T) -> Unit,
    colors: DropdownColors,
    placeholder: String = "Select...",
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    popupWidth: Dp? = null,
    matchButtonWidth: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val selectedItem = items.find { it.value == selected }
    val label = selectedItem?.label ?: placeholder
    val icon = selectedItem?.leadingIcon ?: leadingIcon

    DropdownBase(
        items = items,
        mode = DropdownMode.Single,
        colors = colors,
        onItemClick = onSelection,
        enabled = enabled,
        loading = loading,
        popupWidth = popupWidth,
        matchButtonWidth = matchButtonWidth,
        modifier = modifier,
        triggerContent = { expanded ->
            DropdownTriggerContent(
                label = label,
                leadingIcon = icon,
                colors = colors,
                loading = loading,
                expanded = expanded,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        }
    ) { item ->
        val isCurrent = item.value == selected
        DropdownItemRow(
            item = item,
            colors = colors,
            isSelected = isCurrent,
            trailingIconRes = if (isCurrent) Res.drawable.ic_check else null
        )
    }
}

/**
 * icon-only single select overload
 */
@Composable
fun <T> Dropdown(
    items: List<DropdownItem<T>>,
    selected: T?,
    onSelection: (T) -> Unit,
    colors: DropdownColors,
    icon: ImageVector,
    enabled: Boolean = true,
    loading: Boolean = false,
    rotationTarget: Float? = null,
    popupWidth: Dp? = null,
    matchButtonWidth: Boolean = false,
    modifier: Modifier = Modifier,
) {
    DropdownBase(
        items = items,
        mode = DropdownMode.Single,
        colors = colors,
        onItemClick = onSelection,
        enabled = enabled,
        loading = loading,
        popupWidth = popupWidth,
        matchButtonWidth = matchButtonWidth,
        modifier = modifier,
        triggerContent = {
            DropdownIconTriggerContent(
                icon = icon,
                colors = colors,
                loading = loading,
                rotationTarget = rotationTarget
            )
        }
    ) { item ->
        val isCurrent = item.value == selected
        DropdownItemRow(
            item = item,
            colors = colors,
            isSelected = isCurrent,
            trailingIconRes = if (isCurrent) Res.drawable.ic_check else null
        )
    }
}

/**
 * multi select overload
 */
@Composable
fun <T> Dropdown(
    items: List<DropdownItem<T>>,
    selected: List<T>,
    onSelection: (List<T>) -> Unit,
    colors: DropdownColors,
    placeholder: String = "Select...",
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    popupWidth: Dp? = null,
    matchButtonWidth: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val label = if (selected.isEmpty()) placeholder else "${selected.size} selected"

    DropdownBase(
        items = items,
        mode = DropdownMode.Multi,
        colors = colors,
        onItemClick = { value ->
            val next = if (selected.contains(value)) selected - value else selected + value
            onSelection(next)
        },
        enabled = enabled,
        loading = loading,
        popupWidth = popupWidth,
        matchButtonWidth = matchButtonWidth,
        modifier = modifier,
        triggerContent = { expanded ->
            DropdownTriggerContent(
                label = label,
                leadingIcon = leadingIcon,
                colors = colors,
                loading = loading,
                expanded = expanded,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        }
    ) { item ->
        val isCurrent = selected.contains(item.value)
        DropdownItemRow(
            item = item,
            colors = colors,
            isSelected = isCurrent,
            trailingIconRes = if (isCurrent) Res.drawable.ic_circle_check else Res.drawable.ic_circle
        )
    }
}

/**
 * icon-only multi select overload
 */
@Composable
fun <T> Dropdown(
    items: List<DropdownItem<T>>,
    selected: List<T>,
    onSelection: (List<T>) -> Unit,
    colors: DropdownColors,
    icon: ImageVector,
    enabled: Boolean = true,
    loading: Boolean = false,
    rotationTarget: Float? = null,
    popupWidth: Dp? = null,
    matchButtonWidth: Boolean = false,
    isListItem: Boolean = false,
    modifier: Modifier = Modifier,
) {
    DropdownBase(
        items = items,
        mode = DropdownMode.Multi,
        colors = colors,
        onItemClick = { value ->
            val next = if (selected.contains(value)) selected - value else selected + value
            onSelection(next)
        },
        enabled = enabled,
        loading = loading,
        popupWidth = popupWidth,
        matchButtonWidth = matchButtonWidth,
        isListItem = isListItem,
        modifier = modifier,
        triggerContent = {
            DropdownIconTriggerContent(
                icon = icon,
                colors = colors,
                loading = loading,
                rotationTarget = rotationTarget
            )
        }
    ) { item ->
        val isCurrent = selected.contains(item.value)
        DropdownItemRow(
            item = item,
            colors = colors,
            isSelected = isCurrent,
            trailingIconRes = if (isCurrent) Res.drawable.ic_circle_check else Res.drawable.ic_circle
        )
    }
}

/**
 * action dropdown overload: no state, just triggers actions
 */
@Composable
fun <T> Dropdown(
    items: List<DropdownItem<T>>,
    onAction: (T) -> Unit,
    colors: DropdownColors,
    label: String,
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    popupWidth: Dp? = null,
    matchButtonWidth: Boolean = false,
    modifier: Modifier = Modifier,
) {
    DropdownBase(
        items = items,
        mode = DropdownMode.Action,
        colors = colors,
        onItemClick = onAction,
        enabled = enabled,
        loading = loading,
        popupWidth = popupWidth,
        matchButtonWidth = matchButtonWidth,
        modifier = modifier,
        triggerContent = { expanded ->
            DropdownTriggerContent(
                label = label,
                leadingIcon = leadingIcon,
                colors = colors,
                loading = loading,
                expanded = expanded,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        }
    ) { item ->
        DropdownItemRow(
            item = item,
            colors = colors,
            isSelected = false,
            isDestructive = item.isDestructive
        )
    }
}

/**
 * icon-only action dropdown overload: no state, just triggers actions.
 */
@Composable
fun <T> Dropdown(
    items: List<DropdownItem<T>>,
    onAction: (T) -> Unit,
    colors: DropdownColors,
    icon: ImageVector,
    enabled: Boolean = true,
    loading: Boolean = false,
    rotationTarget: Float? = null,
    popupWidth: Dp? = null,
    matchButtonWidth: Boolean = false,
    isListItem: Boolean = false,
    modifier: Modifier = Modifier,
) {
    DropdownBase(
        items = items,
        mode = DropdownMode.Action,
        colors = colors,
        onItemClick = onAction,
        enabled = enabled,
        loading = loading,
        popupWidth = popupWidth,
        matchButtonWidth = matchButtonWidth,
        isListItem = isListItem,
        modifier = modifier,
        triggerContent = {
            DropdownIconTriggerContent(
                icon = icon,
                colors = colors,
                loading = loading,
                rotationTarget = rotationTarget
            )
        }
    ) { item ->
        DropdownItemRow(
            item = item,
            colors = colors,
            isSelected = false,
            isDestructive = item.isDestructive
        )
    }
}