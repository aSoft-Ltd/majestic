package majestic.users.labels.roles

data class CampusLabels(
    val rolesSingular: String,
    val rolesPlural: String,
    val addRoleButton: String,
    val delete: DeleteCampusLabels
) {
    companion object {
        val english by lazy {
            CampusLabels(
                rolesSingular = "Role",
                rolesPlural = "Roles",
                addRoleButton = "Add Role",
                delete = DeleteCampusLabels.english
            )
        }

        val swahili by lazy {
            CampusLabels(
                rolesSingular = "Jukumu",
                rolesPlural = "Majukumu",
                addRoleButton = "Ongeza Jukumu",
                delete = DeleteCampusLabels.swahili
            )
        }
    }
}