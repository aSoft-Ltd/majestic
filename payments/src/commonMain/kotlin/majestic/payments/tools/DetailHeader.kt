package majestic.payments.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.payments.tools.menu.MenuOption
import majestic.payments.tools.menu.MenuOptionColors
import majestic.payments.tools.menu.OptionMenu

data class DetailHeaderColors(
    val background: Color,
    val foreground: Color,
    val icon: ColorPair,
    val menu: MenuOptionColors
)

@Composable
internal fun <T> DetailHeader(
    colors: DetailHeaderColors,
    orientation: ScreenOrientation,
    options: List<OptionMenu<T>>,
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
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        label()
    }
    MenuOption(
        colors = colors.menu,
        orientation = orientation,
        actions = options,
        onAction = { /* TODO */ }
    )
}

@Composable
internal fun <T> DetailHeader(
    colors: DetailHeaderColors,
    orientation: ScreenOrientation,
    title: String,
    options: List<OptionMenu<T>>,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) = DetailHeader(
    colors = colors,
    orientation = orientation,
    icon = icon,
    options = options,
    modifier = modifier
) {
    Text(
        text = title,
        color = colors.foreground,
        fontSize = 17.sp,
        lineHeight = 1.sp
    )
}
