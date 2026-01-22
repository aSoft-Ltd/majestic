package users.label.profile.security

import users.label.contacts.TextInputLabels

data class PasswordFormLabels(
    val title: String,
    val oldPass: TextInputLabels,
    val newPass: TextInputLabels,
    val submit: String,
    val info: String
)