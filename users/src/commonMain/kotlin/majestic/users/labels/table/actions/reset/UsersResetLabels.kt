package majestic.users.labels.table.actions.reset

import majestic.users.labels.table.actions.tools.ConfirmationLabels

data class UsersResetLabels(
    val action: String,
    val confirm: majestic.users.labels.table.actions.tools.ConfirmationLabels,
    val prompt: majestic.users.labels.table.actions.reset.ResetPromptLabels
)