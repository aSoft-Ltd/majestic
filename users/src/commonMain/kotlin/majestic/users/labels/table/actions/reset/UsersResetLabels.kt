package majestic.users.labels.table.actions.reset

import majestic.users.labels.table.actions.tools.ConfirmationLabels

data class UsersResetLabels(
    val action: String,
    val confirm: ConfirmationLabels,
    val prompt: ResetPromptLabels
)