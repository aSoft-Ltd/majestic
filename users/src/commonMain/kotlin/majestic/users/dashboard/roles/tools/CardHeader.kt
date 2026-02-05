package majestic.users.dashboard.roles.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_info_circle
import majestic.shared.menu.MenuOption
import majestic.shared.menu.OptionMenu
import majestic.shared.users.dashboard.RoleHeaderColors
import org.jetbrains.compose.resources.vectorResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CardHeader(
    title: String,
    actions: List<OptionMenu<RoleAction>>,
    colors: RoleHeaderColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            color = colors.title
        )
        Icon(
            imageVector = vectorResource(Res.drawable.ic_info_circle),
            contentDescription = "Info Icon",
            tint = Color(0xFFAC7E4C),
            modifier = Modifier.size(17.dp)
        )
    }

    MenuOption(
        colors = colors.menu,
        orientation = orientation,
        actions = actions,
        onAction = {},
    )
}