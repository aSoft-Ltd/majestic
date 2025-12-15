package majestic.users.labels.table

data class UserSelectActionLabels(
    val delete: String,
    val send: String,
    val export: String,
    val archive: String,
    val assign: String,
    val cancel: String,
) {
    companion object {
        val english by lazy {
            UserSelectActionLabels(
                delete = "Delete",
                send = "Send Email",
                export = "Export",
                archive = "Archive",
                assign = "Assign",
                cancel = "Cancel",
            )
        }
        val swahili by lazy {
            UserSelectActionLabels(
                delete = "Futa",
                send = "Tuma Barua Pepe",
                export = "Hamisha",
                archive = "Hifadhi",
                assign = "Kabidhi",
                cancel = "Ghairi",
            )
        }
    }
}
