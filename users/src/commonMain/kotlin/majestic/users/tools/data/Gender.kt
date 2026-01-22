package majestic.users.tools.data

import users.label.general.GenderLabels

enum class Gender {
    MALE, FEMALE;

    fun getLabels(labels: GenderLabels) = when (this) {
        MALE -> labels.male
        FEMALE -> labels.female
    }
}