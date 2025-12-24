package majestic.users.profile.roles.assignRoleModalContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.users.labels.roles.RolesLabels
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.buttons.FlatButtonColors

@Composable
 fun AssignRoleFooter(
    selectedCount: Int,
    labels: RolesLabels.AssignRoleModalLabels,
    orientation: ScreenOrientation,
    onDismiss: () -> Unit,
    onAssign: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isPortrait = orientation == Portrait
    val isEnabled = selectedCount > 0

    val buttons: @Composable (Modifier) -> Unit = { buttonModifier ->
        val roundedModifier = buttonModifier.clip(CircleShape)

        FlatButton(
            modifier = roundedModifier,
            label = labels.cancel,
            onClick = onDismiss,
            colors = FlatButtonColors(
                hovered = ColorPair(
                    foreground = Color.White,
                    background = Color.White.copy(alpha = 0.30f)
                ),
                inactive = ColorPair(
                    foreground = Color.White,
                    background = Color.White.copy(alpha = 0.20f)
                )
            )
        )

        FlatButton(
            modifier = roundedModifier,
            label = if (isEnabled) {
                "${labels.assignSelected} ($selectedCount)"
            } else {
                labels.assignSelected
            },
            onClick = { if (isEnabled) onAssign() },
            colors = FlatButtonColors(
                hovered = ColorPair(
                    foreground = Color(0xCC15181D),
                    background = Color.White.copy(alpha = 0.9f)
                ),
                inactive = ColorPair(
                    foreground = if (isEnabled) Color(0xCC15181D)
                    else Color(0xCC15181D).copy(alpha = 0.60f),
                    background = if (isEnabled) Color.White
                    else Color.White.copy(alpha = 0.5f)
                )
            )
        )
    }

    if (isPortrait) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color(0xFF1D2430))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            buttons(Modifier.fillMaxWidth())
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color(0xFF1D2430))
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
            buttons(Modifier)
        }
    }
}