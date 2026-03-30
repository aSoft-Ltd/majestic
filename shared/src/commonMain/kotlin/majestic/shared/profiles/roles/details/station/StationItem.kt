package majestic.shared.profiles.roles.details.station

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.dropdown.DropdownColors
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import majestic.shared.profiles.roles.data.RoleOption
import majestic.shared.profiles.roles.utils.toShape
import majestic.shared.tools.menu.OptionMenu
import majestic.tooling.onClick

data class StationItemColors(
    val background: Color,
    val foreground: Color,
    val icon: ColorPair,
    val dropdown: DropdownColors
)

internal fun Modifier.stationItem(
    interaction: MutableInteractionSource,
    orientation: ScreenOrientation,
    background: Color,
    stations: List<RoleData>,
    campus: RoleData
) = this
    .fillMaxWidth()
    .clip(orientation.toShape(stations = stations, campus = campus))
    .background(background)
    .hoverable(interaction)
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