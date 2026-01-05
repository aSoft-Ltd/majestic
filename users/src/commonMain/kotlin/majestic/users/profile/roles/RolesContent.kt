package majestic.users.profile.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.campus.CampusMenuAction
import majestic.users.tools.ProfilePortraitHeader

@Composable
internal fun RolesContent(
    campuses: List<CampusData>,
    labels: RolesLabels,
    colors: RolesColors,
    orientation: ScreenOrientation,
    navigator: Navigator,
    onCampusAction: (String, CampusMenuAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        if (orientation is Portrait) {
            ProfilePortraitHeader(
                title = labels.heading,
                colors = colors.profileHeader,
                navigator = navigator
            )
        }

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(colors.background)
                .verticalScroll(rememberScrollState())
                .then(
                    if (orientation is Landscape) Modifier
                    else Modifier.padding(20.dp)
                ),
            verticalArrangement = if (orientation is Landscape) {
                Arrangement.Top
            } else {
                Arrangement.spacedBy(10.dp)
            }
        ) {
            RolesHeader(
                title = labels.heading,
                colors = colors,
                orientation = orientation
            )

            CampusesList(
                campuses = campuses,
                labels = labels,
                colors = colors,
                orientation = orientation,
                onCampusAction = onCampusAction
            )
        }
    }
}