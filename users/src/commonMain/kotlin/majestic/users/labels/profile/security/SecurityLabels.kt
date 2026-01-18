package majestic.users.labels.profile.security

import majestic.users.labels.general.SectionLabels

data class SecurityLabels(
    val password: majestic.users.labels.general.SectionLabels,
    val logout: majestic.users.labels.general.SectionLabels,
    val twoFactor: majestic.users.labels.general.SectionLabels,
    val deleteAccount: majestic.users.labels.general.SectionLabels,
    val btnNewPassword: String,
    val btnLogoutAll: String,
    val btnLogout: String,
    val btnDelete: String,
    val switch: majestic.users.labels.profile.security.SwitchLabels,
    val forms: majestic.users.labels.profile.security.SecurityFormLabels,
)

data class SecurityFormLabels(
    val password: majestic.users.labels.profile.security.PasswordFormLabels
)

data class SwitchLabels(
    val on: String,
    val off: String
)