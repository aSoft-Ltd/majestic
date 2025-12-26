package majestic.users.labels.table.actions

import majestic.users.labels.table.actions.tools.ActionDialogLabels
import majestic.users.labels.table.actions.tools.ConfirmationLabels

data class UsersDeleteLabels(
    val action: String,
    val dialog: ActionDialogLabels
) {
    companion object {
        val english by lazy {
            UsersDeleteLabels(
                action = "Delete User",
                dialog = ActionDialogLabels(
                    confirmation = ConfirmationLabels(
                        title = "Are you sure?",
                        description = "You are about to delete",
                        submit = "Delete",
                        cancel = "Cancel"
                    ),
                    common = CommonPromptLabels.english
                )
            )
        }
        val swahili by lazy {
            UsersDeleteLabels(
                action = "Zuia Mtumiaji",
                dialog = ActionDialogLabels(
                    confirmation = ConfirmationLabels(
                        title = "Una uhakika?",
                        description = "Unakaribia kumfuta",
                        submit = "Mfute",
                        cancel = "Ghairi"
                    ),
                    common = CommonPromptLabels.swahili
                )
            )
        }
    }
}
