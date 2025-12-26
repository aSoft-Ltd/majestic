package majestic.users.labels.profile

data class PermissionLabels(
    val breadcrumb: String
) {
    companion object {
        val english by lazy {
            PermissionLabels(
                breadcrumb = "Permissions"
            )
        }
        val swahili by lazy {
            PermissionLabels(
                breadcrumb = "Ruhusa"
            )
        }
    }
}
