package majestic.shared.users.tools

import majestic.shared.users.label.general.GenderLabels

enum class Gender {
    MALE, FEMALE;

    fun getLabels(labels: GenderLabels) = when (this) {
        MALE -> labels.male
        FEMALE -> labels.female
    }
}