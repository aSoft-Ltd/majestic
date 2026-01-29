package majestic.shared.users.label.profile

import majestic.shared.users.label.general.GenderLabels


data class HeaderLabels(
    val joined: String,
    val phone: String,
    val lastActive: String,
    val dob: String,
    val gender: GenderLabels
)
