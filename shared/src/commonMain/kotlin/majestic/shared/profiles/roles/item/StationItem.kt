package majestic.shared.profiles.roles.item

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
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.editor.tools.StateColors
import majestic.shared.menu.MenuOptionColors
import majestic.shared.menu.OptionMenu
import majestic.shared.profiles.roles.RoleColors
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.data.RoleOption
import majestic.shared.profiles.roles.utils.toBackgroundColor
import majestic.shared.profiles.roles.utils.toShape
import majestic.tooling.onClick

data class StationItemColors(
    val background: StateColors,
    val icon: ColorPair,
    val title: Color,
    val subtitle: Color,
    val add: ColorPair,
    val more: Color,
    val options: MenuOptionColors
)

internal fun Modifier.toStationItem(
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
        color = colors.toBackgroundColor(hovered = hovered),
        shape = orientation.toShape(stations = stations, campus = campus)
    )
    .padding(if (orientation is Landscape) 20.dp else 10.dp)

@Composable
internal fun StationItem(
    modifier: Modifier,
    station: RoleData,
    colors: StationItemColors,
    labels: RoleLabels,
    orientation: ScreenOrientation,
    onStation: () -> Unit,
    onAdd: () -> Unit,
    actions: List<OptionMenu<RoleOption>>,
    onOption: (RoleOption) -> Unit
) = Row(
    modifier = modifier
        .pointerHoverIcon(PointerIcon.Hand)
        .onClick { onStation() },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    StationMain(
        modifier = Modifier.wrapContentSize(),
        station = station,
        colors = colors,
        labels = labels
    )

    StationActions(
        modifier = Modifier.padding(end = 10.dp).wrapContentSize(),
        orientation = orientation,
        onAdd = onAdd,
        colors = colors,
        actions = actions,
        onOption = onOption
    )
}
