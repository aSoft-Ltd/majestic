package majestic.users.labels.table

data class UserFilterLabels(
    val search: String,
    val date: String,
    val to: String,
    val letterRange: String,
    val from: String,
    val ascend: String,
    val descend: String,
    val status: UsersStatusLabels,
    val cancel: String,
    val apply: String
) {
    companion object {
        val english by lazy {
            UserFilterLabels(
                search = "Search",
                date = "Search Date",
                to = "To",
                from = "From",
                letterRange = "A To Z",
                ascend = "Ascending",
                descend = "Descending",
                status = UsersStatusLabels.english,
                cancel = "Cancel",
                apply = "Apply",
            )
        }

        val swahili by lazy {
            UserFilterLabels(
                search = "Tafuta",
                date = "Tafuta Tarehe",
                to = "Mpaka",
                from = "Kutoka",
                letterRange = "A Mpaka Z",
                ascend = "Kuongezeka",
                descend = "Kupungua",
                status = UsersStatusLabels.swahili,
                cancel = "Ghairisha",
                apply = "Tumia",
            )
        }
    }
}
