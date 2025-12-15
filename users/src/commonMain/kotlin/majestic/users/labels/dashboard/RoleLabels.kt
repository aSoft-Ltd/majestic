package majestic.users.labels.dashboard

data class RoleLabels(
    val view: String,
    val edit: String,
    val duplicate:String,
    val delete: String
){
    companion object {
        val english by lazy {
            RoleLabels(
                view = "View Role",
                edit = "Edit Role",
                duplicate = "Duplicate Role",
                delete = "Delete Role"
            )
        }

        val swahili by lazy {
            RoleLabels(
                view = "Angalia Jukumu",
                edit = "Hariri Jukumu",
                duplicate = "Rudufisha Jukumu",
                delete = "Futa Jukumu"
            )
        }
    }

}
