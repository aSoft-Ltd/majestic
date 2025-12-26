package majestic.users.profile.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.users.labels.settings.LanguageController
import majestic.users.labels.settings.observeUsersLabels
import majestic.users.profile.permissions.detail.Details
import majestic.users.profile.permissions.detail.Permissions
import majestic.users.profile.permissions.detail.toDetailProperties
import majestic.users.tools.colors.toBackground
import majestic.users.tools.data.Permissions

internal fun Modifier.generalStyles(orientation: ScreenOrientation, theme: ThemeColor) = when (orientation) {
    is Landscape -> this
        .clip(RoundedCornerShape(20.dp))
        .fillMaxWidth()
        .wrapContentHeight().background(
            color = theme.toBackground.copy(.5f),
            shape = RoundedCornerShape(20.dp)
        )

    is Portrait -> this.fillMaxSize()
}


@Composable
fun GeneralPermissions(
    orientation: ScreenOrientation,
    permissions: List<Permissions>,
    theme: ThemeColor,
    language: LanguageController,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val current = rememberPermissionScreenState()
    val labels by observeUsersLabels(language)
    when (current.view) {
        Main -> Permissions(
            modifier = Modifier
                .generalStyles(orientation = orientation, theme = theme)
                .verticalScroll(rememberScrollState()),
            current = current,
            orientation = orientation,
            theme = theme,
            permissions = permissions
        )

        Detailed -> current.activeObj?.let { activePermissions ->
            Details(
                modifier = Modifier.generalStyles(orientation = orientation, theme = theme),
                current = current,
                orientation = orientation,
                props = activePermissions.toDetailProperties(),
                labels = labels.profile.tabs.permissions.content,
                theme = theme
            )
        }
    }
}