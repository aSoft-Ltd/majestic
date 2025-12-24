package majestic.users.labels.roles

data class RolesLabels(
    val heading: String,
    val addButton: String,
    val addCampus: String,
    val campus: CampusLabels,
    val roleItem: RoleItemLabels,
    val assignModal: AssignRoleModalLabels,
    val actions: RolesActionsLabels
) {
    data class CampusLabels(
        val rolesSingular: String,
        val rolesPlural: String,
        val addRoleButton: String,
        val delete: DeleteCampusLabels
    )

    data class RolesActionsLabels(
        val addRole: String,
        val viewRole: String,
        val editRole: String,
        val deleteRole: String
    )

    data class RoleItemLabels(
        val setupBadge: String,
        val setupAction: String,
        val permissions: String,
        val assignAction: String,
        val unassignAction: String
    )

    data class AssignRoleModalLabels(
        val title: String,
        val assignSelected: String,
        val cancel: String,
        val searchPlaceholder: String
    )

    data class DeleteCampusLabels(
        val title: String,
        val message: String,
        val confirmButton: String,
        val cancelButton: String
    )

    internal companion object {
        val english by lazy {
            RolesLabels(
                heading = "Roles & Campuses",
                addButton = "Add",
                addCampus = "Add Campus",
                campus = CampusLabels(
                    rolesSingular = "Role",
                    rolesPlural = "Roles",
                    addRoleButton = "Add Role",
                    delete = DeleteCampusLabels(
                        title = "Delete Campus",
                        message = "Are you sure you want to delete {campusName}? This will remove all associated roles.",
                        confirmButton = "Delete Campus",
                        cancelButton = "Cancel"
                    )
                ),
                roleItem = RoleItemLabels(
                    setupBadge = "Setup Required",
                    setupAction = "Setup",
                    permissions = "Permissions",
                    assignAction = "Assign",
                    unassignAction = "Unassign"
                ),
                assignModal = AssignRoleModalLabels(
                    title = "Assign Roles to",
                    assignSelected = "Assign Selected",
                    cancel = "Cancel",
                    searchPlaceholder = "Search roles..."
                ),
                actions = RolesActionsLabels(
                    addRole = "Add Role",
                    viewRole = "View Role",
                    editRole = "Edit Role",
                    deleteRole = "Delete Role"
                )
            )
        }

        val swahili by lazy {
            RolesLabels(
                heading = "Majukumu na Kambi",
                addButton = "Ongeza",
                addCampus = "Ongeza Kambi",
                campus = CampusLabels(
                    rolesSingular = "Jukumu",
                    rolesPlural = "Majukumu",
                    addRoleButton = "Ongeza Jukumu",
                    delete = DeleteCampusLabels(
                        title = "Futa Kambi",
                        message = "Una uhakika unataka kufuta {campusName}? Hii itaondoa majukumu yote yanayohusiana.",
                        confirmButton = "Futa Kambi",
                        cancelButton = "Ghairi"
                    )
                ),
                roleItem = RoleItemLabels(
                    setupBadge = "Inahitaji Usanidi",
                    setupAction = "Sanidi",
                    permissions = "Ruhusa",
                    assignAction = "Teua",
                    unassignAction = "Ondoa"
                ),
                assignModal = AssignRoleModalLabels(
                    title = "Kabidhisha Majukumu kwa",
                    assignSelected = "Kabidhisha Vilichaguliwa",
                    cancel = "Ghairi",
                    searchPlaceholder = "Tafuta majukumu..."
                ),
                actions = RolesActionsLabels(
                    addRole = "Ongeza Jukumu",
                    viewRole = "Angalia Jukumu",
                    editRole = "Hariri Jukumu",
                    deleteRole = "Futa Jukumu"
                )
            )
        }
    }
}