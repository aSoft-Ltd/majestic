package majestic.users.profile.roles.assignRoleModalContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.users.labels.roles.RolesLabels

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

    if (isPortrait) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AssignRoleFooterButtons(
                selectedCount = selectedCount,
                labels = labels,
                onDismiss = onDismiss,
                onAssign = onAssign,
                modifier = Modifier.fillMaxWidth()
            )
        }
    } else {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AssignRoleFooterButtons(
                selectedCount = selectedCount,
                labels = labels,
                onDismiss = onDismiss,
                onAssign = onAssign,
                modifier = Modifier.wrapContentWidth()
            )
        }
    }
}