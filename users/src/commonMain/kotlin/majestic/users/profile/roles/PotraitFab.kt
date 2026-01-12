package majestic.users.profile.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import majestic.ColorPair
import majestic.ExpandDirection
import majestic.FloatingActionButton
import majestic.floatActionButton
import majestic.tooling.onClick
import majestic.users.tools.buttons.FlatButton

@Composable
internal fun PortraitFab(
    label: String,
    backgroundColor: androidx.compose.ui.graphics.Color,
    theme: majestic.ThemeColor,
    flatButtonColors: majestic.users.tools.buttons.FlatButtonColors,
    isOpen: Boolean,
    onToggle: () -> Unit,
    onAddCampus: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        modifier = modifier
            .floatActionButton(backgroundColor)
            .onClick { onToggle() },
        direction = ExpandDirection.UP,
        expanded = isOpen,
        color = ColorPair(
            background = theme.surface.contra.color,
            foreground = theme.surface.actual.color,
        ),
        content = {
            FlatButton(
                colors = flatButtonColors,
                label = label,
                onClick = {
                    onToggle()
                    onAddCampus()
                }
            )
        }
    )

}