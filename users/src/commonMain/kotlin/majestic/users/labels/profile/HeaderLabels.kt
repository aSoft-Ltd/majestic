package majestic.users.labels.profile

import majestic.users.labels.general.GenderLabels


data class HeaderLabels(
    val joined: String,
    val phone: String,
    val lastActive: String,
    val dob: String,
    val gender: GenderLabels
)
