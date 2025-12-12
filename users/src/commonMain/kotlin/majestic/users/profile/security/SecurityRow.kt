package majestic.users.profile.security

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.ThemeColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SecurityRow(
    icon: DrawableResource,
    theme: ThemeColor,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = Row(modifier = modifier.padding(horizontal = 30.dp)) {
    Icon(
        painter = painterResource(icon),
        contentDescription = null,
        tint = theme.surface.contra.color.copy(0.5f),
        modifier = Modifier.padding(end = 20.dp)
    )
    content()
}