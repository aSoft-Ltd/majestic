package academia.users.labels.profile.contact

data class ActionsLabels(
    val primary: String,
    val email: Particular,
    val phone: Particular
) {

    data class Particular(
        val edit: String,
        val duplicate: String,
        val delete: String,
    )

    internal companion object {
        val english by lazy {
            ActionsLabels(
                primary = "Set as Primary",
                email = Particular(
                    edit = "Edit Email",
                    duplicate = "Duplicate Email",
                    delete = "Delete Email",
                ),
                phone = Particular(
                    edit = "Edit Phone",
                    duplicate = "Duplicate Phone",
                    delete = "Delete Phone",
                ),
            )
        }

        val swahili by lazy {
            ActionsLabels(
                primary = "Weka kama Msingi",
                email = Particular(
                    edit = "Badili Barua pepe",
                    duplicate = "Nakili Barua pepe",
                    delete = "Futa Barua pepe",
                ),
                phone = Particular(
                    edit = "Badili Simu",
                    duplicate = "Nakili Simu",
                    delete = "Futa Simu",
                )
            )
        }
    }
}
