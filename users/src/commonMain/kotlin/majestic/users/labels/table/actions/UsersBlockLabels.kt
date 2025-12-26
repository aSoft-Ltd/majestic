package majestic.users.labels.table.actions

import majestic.users.labels.table.actions.tools.ActionDialogLabels
import majestic.users.labels.table.actions.tools.ConfirmationLabels

data class UsersBlockLabels(
    val action: String,
    val dialog: ActionDialogLabels
) {
    companion object {
        val english by lazy {
            UsersDeleteLabels(
                action = "Block User",
                dialog = ActionDialogLabels(
                    confirmation = ConfirmationLabels(
                        title = "Are you sure?",
                        description = "You are about to block",
                        submit = "Block",
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
                        description = "Unakaribia kumzuia",
                        submit = "Zuia",
                        cancel = "Ghairisha"
                    ),
                    common = CommonPromptLabels.swahili
                )
            )
        }
    }
}
