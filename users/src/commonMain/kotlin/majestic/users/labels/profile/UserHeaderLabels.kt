package majestic.users.labels.profile

import majestic.users.labels.general.GenderLabels


data class UserHeaderLabels(
    val joined: String,
    val phone: String,
    val lastActive: String,
    val dob: String,
    val gender: GenderLabels
) {
    companion object {
        val english by lazy {
            UserHeaderLabels(
                joined = "Joined",
                phone = "Phone",
                lastActive = "Last Active",
                dob = "Date of Birth",
                gender = GenderLabels.english
            )
        }
        val swahili by lazy {
            UserHeaderLabels(
                joined = "Kujiunga",
                phone = "Namba Ya Simu",
                lastActive = "Mwisho Mtandaoni",
                dob = "Tarehe Ya Kuzaliwa",
                gender = GenderLabels.swahili
            )
        }
    }
}
