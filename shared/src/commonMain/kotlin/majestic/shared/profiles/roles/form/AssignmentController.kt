package majestic.shared.profiles.roles.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData

class AssignmentController(
    val userName: String,
    val totalStations: List<RoleData>,
    val assignedStations: List<RoleData>
) {
    var isVisible by mutableStateOf(false)
    var searchQuery by mutableStateOf("")

    data class RoleItemState(
        val role: Role,
        val station: RoleData,
        val isAssigned: Boolean
    )

    val allRoles by derivedStateOf {
        totalStations.flatMap { station ->
            val isAssigned = assignedStations.any { it.station == station.station }
            station.roles.map { RoleItemState(it, station, isAssigned) }
        }
    }

    val filteredRoles by derivedStateOf {
        if (searchQuery.isEmpty()) {
            allRoles
        } else {
            allRoles.filter {
                it.role.title.contains(searchQuery, ignoreCase = true) ||
                        it.role.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    val selectedRoles = mutableStateListOf<Role>()

    fun toggleSelection(role: Role) {
        if (selectedRoles.contains(role)) {
            selectedRoles.remove(role)
        } else {
            selectedRoles.add(role)
        }
    }

    fun open() {
        isVisible = true
    }

    fun close() {
        isVisible = false
    }

    @Composable
    fun Render(
        colors: RoleAssignmentColors,
        labels: majestic.shared.profiles.roles.data.RoleAssignmentLabels
    ) {
        if (isVisible) {
            AssignmentFormModal(
                controller = this,
                colors = colors,
                labels = labels
            )
        }
    }
}
