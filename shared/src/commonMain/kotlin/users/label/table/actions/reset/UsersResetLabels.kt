package users.label.table.actions.reset

import users.label.table.actions.tools.ConfirmationLabels

data class UsersResetLabels(
    val action: String,
    val confirm: ConfirmationLabels,
    val prompt: ResetPromptLabels
)