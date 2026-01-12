package majestic.users.profile.roles.assignRoleModalContent

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import majestic.ColorPair
import majestic.users.labels.roles.AssignRoleModalLabels
import majestic.users.labels.roles.RolesLabels
import majestic.users.tools.buttons.FlatButton
import majestic.users.tools.buttons.FlatButtonColors

@Composable
fun AssignRoleFooterButtons(
    selectedCount: Int,
    labels: AssignRoleModalLabels,
    onDismiss: () -> Unit,
    onAssign: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isEnabled = selectedCount > 0
    val roundedModifier = modifier.clip(CircleShape)

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