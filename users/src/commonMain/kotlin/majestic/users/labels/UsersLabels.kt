package majestic.users.labels

import majestic.users.labels.profile.UserProfile

data class UsersLabels(
    val profile: UserProfile
) {
    companion object {
        val english by lazy {
            UsersLabels(
                profile = UserProfile.english
            )
        }
        val swahili by lazy {
            UsersLabels(
                profile = UserProfile.swahili
            )
        }
    }
}
