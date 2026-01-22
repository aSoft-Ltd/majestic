package users.label.profile

import users.label.general.GenderLabels


data class HeaderLabels(
    val joined: String,
    val phone: String,
    val lastActive: String,
    val dob: String,
    val gender: GenderLabels
)
