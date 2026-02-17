package majestic.shared.profiles.roles.assign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.SearchDefaultColors
import majestic.dialogs.DialogColors
import majestic.editor.toolbar.underline
import majestic.shared.profiles.roles.assign.form.Actions
import majestic.shared.profiles.roles.assign.form.Header
import majestic.shared.profiles.roles.assign.form.Item
import majestic.shared.profiles.roles.assign.form.ItemColors
import majestic.shared.profiles.roles.assign.form.SubHeader
import majestic.shared.profiles.roles.data.RoleAssignmentLabels

data class SearchColors(
    val default: SearchDefaultColors,
    val background: Color,
    val icon: Color
)

data class FormColors(
    val search: SearchColors,
    val background: Color,
    val stats: Color,
    val statsIcon: Color,
    val title: Color,
    val confirmButton: ColorPair,
    val cancelButton: ColorPair,
    val divider: Color,
    val modal: DialogColors,
    val item: ItemColors
)

@Composable
internal fun RoleAssignment(
    colors: FormColors,
    labels: RoleAssignmentLabels,
    controller: AssignmentController
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(12.dp))
        .background(colors.modal.containerColor)
) {
    Header(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .underline(colors.divider, .5.dp),
        labels = labels,
        controller = controller,
        colors = colors
    )

//    HorizontalDivider(color = colors.divider, thickness = 0.5.dp)

    // Sub-header with search and stats
    SubHeader(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
        colors = colors,
        controller = controller,
        labels = labels
    )

    Column(
        modifier = Modifier.weight(1f).fillMaxWidth().padding(horizontal = 24.dp)
    ) {
        controller.filteredRoles.forEachIndexed { index, itemState ->
            Item(
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

    // Bottom Actions
    Actions(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        labels = labels,
        colors = colors,
        controller = controller
    )
}

