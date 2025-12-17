package majestic.payments.labels

data class MenuLabels(
    val view: String,
    val edit: String,
    val close: String,
    val delete: String
) {
    companion object {
        val english: MenuLabels by lazy {
            MenuLabels(
                view = "View",
                edit = "Edit",
                close = "Close",
                delete = "Delete"
            )
        }

        val swahili: MenuLabels by lazy {
            MenuLabels(
                view = "Tazama",
                edit = "Hariri",
                close = "Funga",
                delete = "Futa"
            )
        }
    }
}
