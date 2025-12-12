package majestic.users.labels.profile.security

import majestic.users.labels.general.TextInputLabels

data class PasswordFormLabels(
    val title: String,
    val oldPass: TextInputLabels,
    val newPass: TextInputLabels,
    val submit: String,
    val info: String
) {
    companion object {
        val english by lazy {
            PasswordFormLabels(
                title = "Change Password",
                oldPass = TextInputLabels(
                    label = "Old Password",
                    placeholder = "Enter old password"
                ),
                newPass = TextInputLabels(
                    label = "New Password",
                    placeholder = "Enter new password"
                ),
                submit = "Change Password",
                info = "Let's ensure it's you"
            )
        }

        val swahili by lazy {
            PasswordFormLabels(
                title = "Badilisha Nenosiri",
                oldPass = TextInputLabels(
                    label = "Nenosiri la Zamani",
                    placeholder = "Weka nenosiri la zamani"
                ),
                newPass = TextInputLabels(
                    label = "Nenosiri Jipya",
                    placeholder = "Weka nenosiri jipya"
                ),
                submit = "Badilisha Nenosiri",
                info = "Hakikisha ni wewe"
            )
        }
    }
}
