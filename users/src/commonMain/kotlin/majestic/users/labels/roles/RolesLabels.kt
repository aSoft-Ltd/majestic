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
    companion object {
        val english by lazy {
            RolesLabels(
                heading = "Roles & Campuses",
                addButton = "Add",
                addCampus = "Add Campus",
                campus = CampusLabels.english,
                roleItem = RoleItemLabels.english,
                assignModal = AssignRoleModalLabels.english,
                actions = RolesActionsLabels.english
            )
        }

        val swahili by lazy {
            RolesLabels(
                heading = "Majukumu na Kambi",
                addButton = "Ongeza",
                addCampus = "Ongeza Kambi",
                campus = CampusLabels.swahili,
                roleItem = RoleItemLabels.swahili,
                assignModal = AssignRoleModalLabels.swahili,
                actions = RolesActionsLabels.swahili
            )
        }
    }
}