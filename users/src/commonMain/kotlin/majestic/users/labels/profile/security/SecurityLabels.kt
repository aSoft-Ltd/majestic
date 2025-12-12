package majestic.users.labels.profile.security

import majestic.users.labels.general.SectionLabels

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
) {
    data class SecurityFormLabels(
        val password: PasswordFormLabels
    )

    data class SwitchLabels(
        val on: String,
        val off: String
    )

    internal companion object {
        val english by lazy {
            SecurityLabels(
                btnNewPassword = "Set New Password",
                btnLogoutAll = "Logout All Devices",
                btnLogout = "Logout this Device",
                btnDelete = "Delete Account",
                switch = SwitchLabels(
                    on = "On",
                    off = "Off"
                ),
                password = SectionLabels(
                    label = "Password",
                    description = "Click the Set New Password button to update your password. " +
                            "A dialog box will appear—just follow the on-screen instructions."
                ),
                logout = SectionLabels(
                    label = "Logout Devices",
                    description = "Manage your active sessions for security. Log out from all " +
                            "devices at once or sign out only from this device to stay in control."
                ),
                twoFactor = SectionLabels(
                    label = "Two-Factor Authentication",
                    description = "Turn on two-factor authentication to enhance your account's security. " +
                            "Each time you log in, we'll send a 5-digit code to your email or phone number."
                ),
                deleteAccount = SectionLabels(
                    label = "Delete Account",
                    description = "Delete your account and all linked data permanently. This action is irreversible, " +
                            "and you won’t be able to recover any information once it's removed."
                ),
                forms = SecurityFormLabels(
                    password = PasswordFormLabels.english
                )
            )
        }

        val swahili by lazy {
            SecurityLabels(
                btnNewPassword = "Weka Nenosiri Jipya",
                btnLogoutAll = "Ondoa Vifaa Vyote",
                btnLogout = "Ondoa Kifaa",
                btnDelete = "Futa Akaunti",
                switch = SwitchLabels(
                    on = "Washa",
                    off = "Zima"
                ),
                password = SectionLabels(
                    label = "Nenosiri",
                    description = "Bofya kitufe cha Weka Nenosiri Jipya ili kubadilisha nenosiri lako. " +
                            "Sanduku la mazungumzo litaonekana-fuata tu maelekezo ya skrini."
                ),
                logout = SectionLabels(
                    label = "Ondoa Kifaa",
                    description = "Dhibiti vipindi vyako vinavyoendelea kwa usalama. Ondoka kwenye vifaa " +
                            "vyote mara moja au uondoke kwenye kifaa hiki pekee ili uendelee kudhibiti."
                ),
                twoFactor = SectionLabels(
                    label = "Uthibitishaji wa Hatua Mbili",
                    description = "Washa uthibitishaji wa hatua mbili ili kuongeza usalama wa akaunti yako. " +
                            "Kila wakati unapoingia, tutatuma msimbo wa tarakimu 5 kwa barua pepe au namba ya simu."
                ),
                deleteAccount = SectionLabels(
                    label = "Futa Akaunti",
                    description = "Futa akaunti yako na data zote zilizounganishwa kwa kudumu. Hatua hii haiwezi " +
                            "kurejeshwa, na hautaweza kupata tena taarifa yoyote mara tu itakapoondolewa."
                ),
                forms = SecurityFormLabels(
                    password = PasswordFormLabels.swahili
                )
            )
        }
    }
}
