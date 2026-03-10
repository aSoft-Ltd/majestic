package majestic.shared.profiles.roles.details.responsibilities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.Permission
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun IconDescription(
    modifier: Modifier,
    index: Int,
    orientation: ScreenOrientation,
    responsibility: Permission,
    colors: ResponsibilityColors,
    type: ResponsibilityType
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    Icon(
        painter = painterResource(responsibility.resource),
        contentDescription = null,
        tint = colors.title.unfocused,
        modifier = Modifier.padding(top = 4.dp).size(20.dp)
    )
    Description(
        modifier = Modifier.weight(1f),
        orientation = orientation,
        colors = colors,
        index = index,
        responsibility = responsibility,
        type = type
    )
}