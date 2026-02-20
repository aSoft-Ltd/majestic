package majestic.shared.profiles.roles.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleData

internal sealed interface RoleScreenState
internal object Stations : RoleScreenState
internal object Roles : RoleScreenState
internal object Details : RoleScreenState

internal class RoleScreenController {
    var view by mutableStateOf<RoleScreenState>(Stations)
    var activeStation by mutableStateOf<RoleData?>(null)
    var activeRole by mutableStateOf<Role?>(null)

    fun stations() {
        view = Stations
        activeStation = null
        activeRole = null
    }

    fun roles(station: RoleData) {
        view = Roles
        activeStation = station
        activeRole = null
    }

    fun details(role: Role) {
        view = Details
        activeRole = role
    }

    fun back() {
        when (view) {
            Details -> {
                view = Roles
                activeRole = null
            }

            Roles -> {
                view = Stations
                activeStation = null
                activeRole = null
            }

            Stations -> Unit
        }
    }
}

@Composable
internal fun rememberRoleScreenController() = remember { RoleScreenController() }
