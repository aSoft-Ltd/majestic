package majestic.users.labels.table

data class UsersTableHeaderLabels(
    val users: String,
    val name: String,
    val email: String,
    val selected: String,
    val id: String,
    val dateJoined: String,
    val lastActive: String,
    val roles: String,
    val permission: String,
    val status: String,
    val export: String,
    val actions: UserSelectActionLabels,
    val filters: UserFilterLabels
) {
    companion object {
        val english by lazy {
            UsersTableHeaderLabels(
                users = "Users",
                name = "Name",
                email = "Email",
                selected = "Selected",
                id = "ID",
                dateJoined = "Date Joined",
                lastActive = "Last Active",
                roles = "Roles",
                permission = "Permission",
                status = "Status",
                export = "Export",
                actions = UserSelectActionLabels.english,
                filters = UserFilterLabels.english
            )
        }
        val swahili by lazy {
            UsersTableHeaderLabels(
                users = "Watumiaji",
                name = "Jina",
                email = "Barua Pepe",
                selected = "Wamechaguliwa",
                id = "Kitambulisho",
                dateJoined = "Tarehe Yakujiunga",
                lastActive = "Mara Ya Mwisho Mtandaoni",
                roles = "Wajibu",
                permission = "Ruhusa",
                status = "Hali",
                export = "Shusha",
                actions = UserSelectActionLabels.swahili,
                filters = UserFilterLabels.swahili
            )
        }
    }
}
