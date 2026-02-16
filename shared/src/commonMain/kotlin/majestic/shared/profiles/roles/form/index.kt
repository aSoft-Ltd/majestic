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
import majestic.shared.profiles.roles.data.RoleAssignmentLabels

@Composable
internal fun AddRolesForm(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(12.dp))
        .background(colors.modal.containerColor),
    controller: AssignmentController,
    colors: RoleAssignmentColors,
    labels: RoleAssignmentLabels
) = Column(
    modifier = modifier
) {
    FormHeader(
        userName = controller.userName,
        colors = colors,
        labels = labels
    )

    HorizontalDivider(color = colors.divider, thickness = 0.5.dp)

    FormSubHeader(
        controller = controller,
        colors = colors,
        labels = labels
    )

    LazyColumn(
        modifier = Modifier.weight(1f).fillMaxWidth().padding(horizontal = 24.dp)
    ) {
        itemsIndexed(controller.filteredRoles) { index, itemState ->
            RoleAssignmentItem(
                index = index + 1,
                role = itemState.role,
                isSelected = itemState.isAssigned || controller.selectedRoles.contains(itemState.role),
                colors = colors,
                labels = labels,
                onToggle = { controller.toggleSelection(itemState.role) }
            )
            Spacer(Modifier.height(16.dp))
        }
    }

    FormActions(
        onCancel = { controller.close() },
        onConfirm = { controller.close() },
        colors = colors,
        labels = labels
    )
}