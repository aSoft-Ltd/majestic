package majestic.users.profile.roles

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation

@Composable
internal fun RolesHeader(
    title: String,
    colors: RolesColors,
    orientation: ScreenOrientation
) {
    if (orientation is Landscape) {
        Text(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp),
            text = title,
            color = colors.theme.surface.contra.color.copy(0.5f),
        )
    }
}