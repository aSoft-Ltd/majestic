package majestic.users.labels.roles

data class DeleteCampusLabels(
    val title: String,
    val message: String,
    val confirmButton: String,
    val cancelButton: String
) {
    companion object {
        val english by lazy {
            DeleteCampusLabels(
                title = "Delete Campus",
                message = "Are you sure you want to delete {campusName}? This will remove all associated roles.",
                confirmButton = "Delete Campus",
                cancelButton = "Cancel"
            )
        }

        val swahili by lazy {
            DeleteCampusLabels(
                title = "Futa Kambi",
                message = "Una uhakika unataka kufuta {campusName}? Hii itaondoa majukumu yote yanayohusiana.",
                confirmButton = "Futa Kambi",
                cancelButton = "Ghairi"
            )
        }
    }
}