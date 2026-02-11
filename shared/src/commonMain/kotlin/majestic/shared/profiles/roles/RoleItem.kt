package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.editor.tools.StateColors
import majestic.shared.menu.MenuOptionColors
import majestic.shared.menu.OptionMenu
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.data.RoleOption
import majestic.shared.profiles.roles.item.Actions
import majestic.shared.profiles.roles.item.Main
import majestic.shared.profiles.roles.utils.toBackgroundColor
import majestic.shared.profiles.roles.utils.toShape

data class RoleItemBackgrounds(
    val portrait: Color,
    val landscape: StateColors
)

data class RoleItemColors(
    val backgrounds: RoleItemBackgrounds,
    val icon: ColorPair,
    val title: Color,
    val subtitle: Color,
    val add: ColorPair,
    val more: Color,
    val options: MenuOptionColors
)

internal fun Modifier.toRoleItem(
    interaction: MutableInteractionSource,
    orientation: ScreenOrientation,
    hovered: Boolean,
    colors: RoleColors,
    stations: List<RoleData>,
    campus: RoleData
) = this
    .fillMaxWidth()
    .hoverable(interaction)
    .background(
        color = colors.toBackgroundColor(orientation = orientation, hovered = hovered),
        shape = orientation.toShape(stations = stations, campus = campus)
    )
    .padding(if (orientation is Landscape) 20.dp else 10.dp)

@Composable
internal fun RoleItem(
    modifier: Modifier,
    role: RoleData,
    colors: RoleItemColors,
    labels: RoleLabels,
    orientation: ScreenOrientation,
    onAdd: () -> Unit,
    actions: List<OptionMenu<RoleOption>>,
    onOption: (RoleOption) -> Unit
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Main(
        modifier = Modifier.wrapContentSize(),
        role = role,
        colors = colors,
        labels = labels
    )

    Actions(
        modifier = Modifier.padding(end = 10.dp).wrapContentSize(),
        orientation = orientation,
        onAdd = onAdd,
        colors = colors,
        actions = actions,
        onOption = onOption
    )
}