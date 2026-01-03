package majestic.users.labels.table.actions.reset

import majestic.users.labels.table.actions.tools.ConfirmationLabels

data class UsersResetLabels(
    val action: String,
    val confirm: ConfirmationLabels,
    val prompt: ResetPromptLabels
) {
    companion object {
        val english by lazy {
            UsersResetLabels(
                action = "Reset Password",
                confirm = ConfirmationLabels(
                    title = "Are you sure?",
                    description = "Proceed to reset password for",
                    submit = "Reset",
                    cancel = "Cancel"
                ),
                prompt = ResetPromptLabels.english
            )
        }
        val swahili by lazy {
            UsersResetLabels(
                action = "Badili Nywila",
                confirm = ConfirmationLabels(
                    title = "Una uhakika?",
                    description = "Endelea kubadili neno siri la",
                    submit = "Badili",
                    cancel = "Ghairi"
                ),
                prompt = ResetPromptLabels.swahili
            )
        }
    }
}
