package majestic.shared.profiles.roles.details.responsibilities

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.Permission


internal fun Modifier.responsibilityItem(
    interaction: MutableInteractionSource,
    hovered: Boolean,
    orientation: ScreenOrientation,
    colors: ResponsibilityColors,
    index: Int = 0,
    responsibilities: List<Permission>
) = this
    .fillMaxWidth()
    .hoverable(interaction)
    .background(
        color = if (hovered) colors.background.focused else colors.background.unfocused,
        shape = RoundedCornerShape(
            bottomStart = if (index == responsibilities.lastIndex) 10.dp else 0.dp,
            bottomEnd = if (index == responsibilities.lastIndex) 10.dp else 0.dp
        )
    )
    .padding(if (orientation is Landscape) 20.dp else 10.dp)


@Composable
internal fun ResponsibilityItem(
    modifier: Modifier,
    index: Int,
    orientation: ScreenOrientation,
    responsibility: Permission,
    colors: ResponsibilityColors,
    type: ResponsibilityType
) = when (type) {
    NumberResponsibility -> Description(
        modifier = modifier,
        orientation = orientation,
        colors = colors,
        index = index,
        responsibility = responsibility,
        type = type
    )

    IconResponsibility -> IconDescription(
        modifier = modifier,
        orientation = orientation,
        colors = colors,
        index = index,
        responsibility = responsibility,
        type = type
    )
}

