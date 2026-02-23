package majestic.payments.tools.header

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.dropdown.Dropdown
import majestic.dropdown.DropdownColors
import majestic.icons.Res
import majestic.icons.ic_more_horizontal
import majestic.icons.ic_more_vertical
import majestic.payments.tools.dropdown.toDropdownItems
import majestic.payments.tools.menu.MenuOptionColors
import majestic.payments.tools.menu.OptionMenu
import org.jetbrains.compose.resources.vectorResource

data class DetailHeaderColors(
    val background: Color,
    val foreground: Color,
    val icon: ColorPair,
    val info: InfoEntryColors,
    val menu: MenuOptionColors,
    val dropdownColors: DropdownColors,
    val tooltip: ColorPair,
)

@Composable
internal fun <T> DetailHeader(
    colors: DetailHeaderColors,
    orientation: ScreenOrientation,
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
    Column(modifier = Modifier.weight(1f)) {
        content()
    }
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
    orientation: ScreenOrientation,
    options: List<OptionMenu<T>>,
    details: List<InfoEntryItem>,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(20.dp)
) {
    icon()
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            label()
            Dropdown(
                items = options.toDropdownItems(),
                onAction = { /* TODO */ },
                colors = colors.dropdownColors,
                icon = vectorResource(Res.drawable.ic_more_horizontal),
                isListItem = true
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (detail in details) InfoEntry(
                colors = colors.info,
                icon = detail.icon,
                title = detail.title,
                description = detail.description,
                modifier = Modifier.widthIn(min = 120.dp)
            )
        }
    }
}

@Composable
internal fun <T> DetailHeader(
    colors: DetailHeaderColors,
    orientation: ScreenOrientation,
    title: String,
    options: List<OptionMenu<T>>,
    details: List<InfoEntryItem>,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) = DetailHeader(
    colors = colors,
    orientation = orientation,
    icon = icon,
    options = options,
    details = details,
    modifier = modifier
) {
    Text(
        text = title,
        color = colors.foreground,
        fontSize = 18.sp,
        lineHeight = 1.sp
    )
}