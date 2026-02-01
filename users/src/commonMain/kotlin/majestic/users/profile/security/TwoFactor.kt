package majestic.users.profile.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.ToggleSwitch
import majestic.ToggleSwitchColors
import majestic.icons.Res
import majestic.icons.ic_access
import majestic.shared.users.label.profile.security.SecurityLabels


@Composable
internal fun ColumnScope.TwoFactor(
    modifier: Modifier = Modifier,
    colors: TwoFactorColors,
    orientation: ScreenOrientation,
    labels: SecurityLabels,
) = SecurityRow(
    modifier = modifier,
    icon = Res.drawable.ic_access,
    theme = colors.theme,
    orientation = orientation,
) {
    SectionHeading(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(2f) else Modifier),
        labels = labels.twoFactor,
        color = colors.theme.surface.contra.color
    )
    Row(
        modifier = Modifier.then(if (orientation == Landscape) Modifier.weight(1f) else Modifier),
        horizontalArrangement = Arrangement.End,
    ) {
        var checked by remember { mutableStateOf(false) }
        val textColor = colors.theme.surface.contra.color
        Text(
            modifier = Modifier.padding(end = 10.dp),
            text = if (checked) labels.switch.on else labels.switch.off,
            color = if (checked) textColor else textColor.copy(0.7f),
        )
        ToggleSwitch(
            checked = checked,
            width = 50.dp,
            height = 25.dp,
            colors = colors.toggle,
            onCheckedChange = { checked = it },
        )
    }
}