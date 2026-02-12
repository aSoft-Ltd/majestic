package majestic.shared.profiles.roles.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.roles.data.RoleData

internal fun ScreenOrientation.toShape(
    stations: List<RoleData>,
    campus: RoleData
) = when (this) {
    is Landscape -> when {
        stations.indexOf(campus) == stations.lastIndex -> RoundedCornerShape(
            bottomStart = 20.dp,
            bottomEnd = 20.dp
        )

        else -> RoundedCornerShape(0.dp)
    }

    is Portrait -> RoundedCornerShape(8.dp)
}
