package users.label.profile.security

import users.label.general.SectionLabels

data class SecurityLabels(
    val password: SectionLabels,
    val logout: SectionLabels,
    val twoFactor: SectionLabels,
    val deleteAccount: SectionLabels,
    val btnNewPassword: String,
    val btnLogoutAll: String,
    val btnLogout: String,
    val btnDelete: String,
    val switch: SwitchLabels,
    val forms: SecurityFormLabels,
)

data class SecurityFormLabels(
    val password: PasswordFormLabels
)

data class SwitchLabels(
    val on: String,
    val off: String
)