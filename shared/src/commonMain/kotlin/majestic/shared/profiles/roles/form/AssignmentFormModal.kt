package majestic.shared.profiles.roles.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.dialogs.Modal
import majestic.icons.Res
import majestic.icons.ic_cancel
import majestic.shared.profiles.roles.data.RoleAssignmentLabels

@Composable
fun AssignmentFormModal(
    controller: AssignmentController,
    colors: RoleAssignmentColors,
    labels: RoleAssignmentLabels
) = Modal(
    colors = colors.modal,
    onDismiss = { controller.close() },
    closeIcon = Res.drawable.ic_cancel,
    modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp, vertical = 20.dp)
) {

}
