package majestic.users.tools

import majestic.users.tools.data.Permission
import majestic.users.tools.data.Permissions
import majestic.users.tools.data.Role
import majestic.users.tools.data.UserStatus
import org.jetbrains.compose.resources.DrawableResource


private val dateJoined = listOf("24 Feb 2025", "28 Mar 2025", "30 Apr 2025", "26 May 2025")
private val lastActive = listOf("24 Feb 2025", "28 Mar 2025", "30 Apr 2025", "26 May 2025")

private val userEmails = listOf(
    "John Doe" to "john.doe@gmail.com",
    "Jane Smith" to "jane.smith@outlook.com",
    "Peter Jones" to "peter.jones@gmail.com",
    "Mary Williams" to "mary.williams@outlook.com",
    "David Brown" to "david.brown@gmail.com"
)

data class UsersData(
    val id: String,
    var selected: Boolean,
    val userAvatar: DrawableResource? = null,
    val fullName: String,
    val email: String,
    val dateJoined: String,
    val lastActive: String,
    val rolesCount: Int,
    val permissionsCount: Int,
    val permissions: List<Permission>,
    val roles: List<Role>,
    val status: UserStatus,
) {
    companion object {
        fun getUserData(
            avatars: List<DrawableResource?>,
            permissionList: List<Permissions>,
            roles: List<Role>
        ): UsersData {
            val randomPermissions = permissionList.flatMap { it.permissions }.shuffled().take((5..15).random())
            val randomRoles = roles.shuffled().take((1..roles.size).random())
            return UsersData(
                id = "019975ac-2e24-ba77-e7017690f${(0..69).random()}b",
                selected = false,
                userAvatar = avatars.random(),
                fullName = userEmails.random().first,
                email = userEmails.random().second,
                dateJoined = dateJoined.random(),
                lastActive = lastActive.random(),
                rolesCount = randomRoles.size,
                roles = randomRoles,
                permissionsCount = randomPermissions.size,
                permissions = randomPermissions,
                status = UserStatus.entries.random(),
            )
        }
    }
}