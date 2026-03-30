package majestic.shared.profiles.roles.details.station

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.button.Button
import majestic.button.appearence.listItemIconButton
import majestic.button.basic.BasicButtonContent
import majestic.dropdown.Dropdown
import majestic.icons.Res
import majestic.icons.ic_add
import majestic.icons.ic_more_horizontal
import majestic.shared.profiles.roles.data.RoleOption
import majestic.shared.tools.dropdown.toDropdownItems
import majestic.shared.tools.menu.OptionMenu
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun StationActions(
    modifier: Modifier,
    orientation: ScreenOrientation,
    onAdd: () -> Unit,
    colors: StationItemColors,
    actions: List<OptionMenu<RoleOption>>,
    onOption: (RoleOption) -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(24.dp),
    verticalAlignment = Alignment.CenterVertically,
) {
    if (orientation is Landscape) Button(
        modifier = Modifier.listItemIconButton(
            color = colors.foreground,
            onClick = onAdd
        )
    ) { colors ->
        BasicButtonContent(
            icon = vectorResource(Res.drawable.ic_add),
            colors = colors
        )
    }

    Dropdown(
        items = actions.toDropdownItems(),
        onAction = onOption,
        colors = colors.dropdown,
        icon = vectorResource(Res.drawable.ic_more_horizontal),
        rotationTarget = if (orientation == Portrait) 90f else 0f,
        isListItem = true
    )
}