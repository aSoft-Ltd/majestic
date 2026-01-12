package majestic.users.profile.roles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import majestic.users.tools.buttons.ButtonAnimate
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_add

@Composable
internal fun LandscapeAddButton(
    label: String,
    colors: majestic.users.tools.buttons.ButtonAnimateColors,
    isOpen: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ButtonAnimate(
        modifier = modifier,
        label = label,
        icon = Res.drawable.ic_add,
        isOpen = isOpen,
        colors = colors,
        onClick = onToggle
    )
}