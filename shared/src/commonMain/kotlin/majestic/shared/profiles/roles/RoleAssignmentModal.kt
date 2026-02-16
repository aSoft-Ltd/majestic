package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.Dark
import majestic.dialogs.Modal
import majestic.icons.Res
import majestic.icons.ic_cancel
import majestic.icons.ic_search
import majestic.icons.ic_vertical_settings
import majestic.search.SearchField
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoleAssignmentModal(
    controller: AssignmentController,
    colors: RoleAssignmentColors,
    labels: RoleAssignmentLabels
) = Modal(
    colors = colors.modal,
    onDismiss = { controller.close() },
    closeIcon = Res.drawable.ic_cancel,
    modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp, vertical = 20.dp)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(colors.modal.containerColor)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_vertical_settings),
                contentDescription = null,
                tint = Color(0xFFFFA500),
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = "${labels.title} ${controller.userName}",
                color = colors.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        HorizontalDivider(color = colors.divider, thickness = 0.5.dp)

        // Sub-header with search and stats
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(Res.drawable.ic_vertical_settings),
                    contentDescription = null,
                    tint = colors.stats,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(8.dp))
                val selectedCount = controller.selectedRoles.size
                val totalCount = controller.allRoles.size
                Text(
                    text = "$selectedCount/$totalCount ${labels.selected}",
                    color = colors.stats,
                    fontSize = 14.sp
                )
            }

            SearchField(
                modifier = Modifier
                    .width(250.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(colors.search.background)
                    .padding(horizontal = 16.dp),
                value = controller.searchQuery,
                onChange = { controller.searchQuery = it },
                hint = labels.search,
                theme = theme,
                icon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = null,
                        tint = colors.search.icon,
                        modifier = Modifier.size(18.dp)
                    )
                }
            )
        }

        // Roles List
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

        // Bottom Actions
        Row(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = labels.cancel,
                color = colors.cancelButton.foreground,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(colors.cancelButton.background)
                    .onClick { controller.close() }
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = labels.assignSelected,
                color = colors.confirmButton.foreground,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(colors.confirmButton.background)
                    .onClick { controller.close() }
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

