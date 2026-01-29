package majestic.shared.users.label.table.actions.reset

import majestic.shared.users.label.table.actions.tools.ConfirmationLabels

data class UsersResetLabels(
    val action: String,
    val confirm: ConfirmationLabels,
    val prompt: ResetPromptLabels
)