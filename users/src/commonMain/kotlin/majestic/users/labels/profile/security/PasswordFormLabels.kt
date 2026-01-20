package majestic.users.labels.profile.security

import majestic.users.labels.general.TextInputLabels

data class PasswordFormLabels(
    val title: String,
    val oldPass: majestic.users.labels.general.TextInputLabels,
    val newPass: majestic.users.labels.general.TextInputLabels,
    val submit: String,
    val info: String
)