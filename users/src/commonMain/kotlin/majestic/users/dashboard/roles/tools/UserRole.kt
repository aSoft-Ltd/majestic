package majestic.users.dashboard.roles.tools

data class UserRole(
    val id: String,
    val name: String,
    val description: String,
    val permissions: Int,
    val usersAssigned: Int,
    val hasInfoIcon: Boolean = false
) {
    companion object {
        val roles = listOf(
            UserRole(
                id = "head_teacher",
                name = "Head Teacher",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 24,
                usersAssigned = 12,
                hasInfoIcon = false
            ),
            UserRole(
                id = "class_teacher",
                name = "Class Teacher",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 24,
                usersAssigned = 12,
                hasInfoIcon = true
            ),
            UserRole(
                id = "subject_teacher",
                name = "Subject Teacher",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 24,
                usersAssigned = 12,
                hasInfoIcon = true
            ),
            UserRole(
                id = "academic_master",
                name = "Academic Master",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 18,
                usersAssigned = 4,
                hasInfoIcon = true
            ),
            UserRole(
                id = "stream_teacher",
                name = "Stream Teacher",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 16,
                usersAssigned = 8,
                hasInfoIcon = true
            ),
            UserRole(
                id = "bursar",
                name = "Bursar",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 20,
                usersAssigned = 5,
                hasInfoIcon = true
            ),
            UserRole(
                id = "it_officer",
                name = "IT Officer",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 22,
                usersAssigned = 3,
                hasInfoIcon = true
            ),
            UserRole(
                id = "school_chef",
                name = "School Chef",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 24,
                usersAssigned = 12,
                hasInfoIcon = false
            ),
            UserRole(
                id = "school_driver",
                name = "School Driver",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 24,
                usersAssigned = 12,
                hasInfoIcon = true
            ),
            UserRole(
                id = "receptionist",
                name = "Receptionist",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 19,
                usersAssigned = 10,
                hasInfoIcon = true
            ),
            UserRole(
                id = "school_watchman",
                name = "School Watchman",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 24,
                usersAssigned = 12,
                hasInfoIcon = true
            ),
            UserRole(
                id = "principal",
                name = "Principal",
                description = "Has full access to oversee academic, financial, and administrative operations, ensuring smooth coordination across the school.",
                permissions = 24,
                usersAssigned = 12,
                hasInfoIcon = true
            )
        )
    }
}