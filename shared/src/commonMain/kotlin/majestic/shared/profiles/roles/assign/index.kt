package majestic.shared.profiles.roles.assign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.dialogs.DialogColors
import majestic.shared.profiles.roles.assign.form.Actions
import majestic.shared.profiles.roles.assign.form.HeadingsColor
import majestic.shared.profiles.roles.assign.form.Item
import majestic.shared.profiles.roles.assign.form.ItemColors
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleAssignmentLabels


data class FormColors(
    val background: Color,
    val headings: HeadingsColor,
    val confirmButton: ColorPair,
    val cancelButton: ColorPair,
    val divider: Color,
    val modal: DialogColors,
    val bottomBar: Color,
    val item: ItemColors
)

@Composable
fun RoleAssignment(
    modifier: Modifier,
    colors: FormColors,
    labels: RoleAssignmentLabels,
    controller: AssignmentController
) = Column(modifier = modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        controller.filteredRoles.forEachIndexed { index, itemState ->
            Item(
                Modifier.fillMaxWidth().padding(vertical = 16.dp),
                index = index + 1,
                role = itemState.role,
                isSelected = controller.selectedRoles.contains(itemState.role),
                colors = colors.item,
                labels = labels,
                onToggle = { controller.toggleSelection(itemState.role) }
            )
        }
    }
    Actions(
        modifier = Modifier
            .background(colors.bottomBar)
            .fillMaxWidth()
            .padding(16.dp),
        labels = labels,
        colors = colors,
        controller = controller
    )
}