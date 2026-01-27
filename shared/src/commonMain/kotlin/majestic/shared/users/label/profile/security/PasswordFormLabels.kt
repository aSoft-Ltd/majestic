package majestic.shared.users.label.profile.security

import majestic.shared.users.label.contacts.TextInputLabels

data class PasswordFormLabels(
    val title: String,
    val oldPass: TextInputLabels,
    val newPass: TextInputLabels,
    val submit: String,
    val info: String
)