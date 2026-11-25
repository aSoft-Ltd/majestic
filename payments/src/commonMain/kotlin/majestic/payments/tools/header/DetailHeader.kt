package majestic.payments.tools.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.button.appearence.listItemIconButton
import majestic.button.basic.IconButton
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownColors
import majestic.icons.Res
import majestic.icons.ic_arrow_down_01
import majestic.icons.ic_more_horizontal
import majestic.icons.ic_more_vertical
import majestic.shared.tools.dropdown.toDropdownItems
import majestic.shared.tools.menu.OptionMenu
import org.jetbrains.compose.resources.vectorResource

internal const val COLLAPSE_MILLIS = 300

data class DetailHeaderColors(
    val background: Color,
    val foreground: Color,
    val icon: ColorPair,
    val info: InfoEntryColors,
    val dropdownColors: DropdownColors,
    val tooltip: ColorPair,
)

@Composable
internal fun <T> DetailHeader(
    colors: DetailHeaderColors,
    options: List<OptionMenu<T>>,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    content: @Composable () -> Unit,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    icon()
    Column(modifier = Modifier.weight(1f)) { content() }
    Dropdown(
        items = options.toDropdownItems(),
        onAction = { /* TODO */ },
        colors = colors.dropdownColors,
        icon = vectorResource(Res.drawable.ic_more_vertical),
        isListItem = true
    )
}

@Composable
internal fun <T> DetailHeader(
    colors: DetailHeaderColors,
    options: List<OptionMenu<T>>,
    details: List<InfoEntryItem>,
    collapsed: Boolean = false,
    onToggleCollapsed: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    icon()
    Column(modifier = Modifier.weight(1f)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            label()
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                if (onToggleCollapsed != null) IconButton(
                    icon = vectorResource(Res.drawable.ic_arrow_down_01),
                    rotation = if (collapsed) 0f else 180f,
                    modifier = Modifier.listItemIconButton(
                        color = colors.dropdownColors.trigger,
                        onClick = onToggleCollapsed
                    )
                )
                Dropdown(
                    items = options.toDropdownItems(),
                    onAction = { /* TODO */ },
                    colors = colors.dropdownColors,
                    icon = vectorResource(Res.drawable.ic_more_horizontal),
                    isListItem = true
                )
            }
        }
        AnimatedVisibility(
            visible = !collapsed,
            enter = fadeIn(tween(COLLAPSE_MILLIS)) + expandVertically(tween(COLLAPSE_MILLIS)),
            exit = fadeOut(tween(COLLAPSE_MILLIS)) + shrinkVertically(tween(COLLAPSE_MILLIS))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (detail in details) InfoEntry(
                    colors = colors.info,
                    icon = detail.icon,
                    title = detail.title,
                    description = detail.description,
                )
            }
        }
    }
}

@Composable
internal fun <T> DetailHeader(
    colors: DetailHeaderColors,
    title: String,
    options: List<OptionMenu<T>>,
    details: List<InfoEntryItem>,
    collapsed: Boolean = false,
    onToggleCollapsed: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) = DetailHeader(
    colors = colors,
    icon = icon,
    options = options,
    details = details,
    collapsed = collapsed,
    onToggleCollapsed = onToggleCollapsed,
    modifier = modifier
) { Text(text = title, color = colors.foreground, fontSize = 18.sp, lineHeight = 1.sp) }