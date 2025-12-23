package majestic.users.profile.roles

val sampleCampuses = listOf(
    CampusData(
        id = "campus-ny",
        name = "New York Campus",
        rolesCount = 12
    ),
    CampusData(
        id = "campus-sf",
        name = "San Francisco Campus",
        rolesCount = 8
    ),
    CampusData(
        id = "campus-la",
        name = "Los Angeles Campus",
        rolesCount = 10
    ),
    CampusData(
        id = "campus-ch",
        name = "Chicago Campus",
        rolesCount = 6
    ),
    CampusData(
        id = "campus-au",
        name = "Austin Campus",
        rolesCount = 4
    )
)

val sampleRoles = listOf(
    RoleData(
        id = "role-admin",
        name = "Administrator",
        description = "Full access to manage users, roles, and system settings",
        actionType = RoleActionType.SETUP
    ),
    RoleData(
        id = "role-manager",
        name = "Campus Manager",
        description = "Manages campus operations and staff assignments"
    ),
    RoleData(
        id = "role-instructor",
        name = "Instructor",
        description = "Responsible for teaching and course content delivery"
    ),
    RoleData(
        id = "role-support",
        name = "Support Staff",
        description = "Provides administrative and technical support"
    ),
    RoleData(
        id = "role-viewer",
        name = "Viewer",
        description = "Read-only access to campus information"
    ),    RoleData(
        id = "role-manager-2",
        name = "Campus Manager",
        description = "Manages campus operations and staff assignments"
    ),
    RoleData(
        id = "role-instructor-2",
        name = "Instructor",
        description = "Responsible for teaching and course content delivery"
    ),
    RoleData(
        id = "role-support-2",
        name = "Support Staff",
        description = "Provides administrative and technical support"
    ),
    RoleData(
        id = "role-viewer-2",
        name = "Viewer",
        description = "Read-only access to campus information"
    ),
)
