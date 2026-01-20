package majestic.users.labels.profile.contact

import majestic.users.labels.general.TextInputLabels

data class DedicatedFormLabels(
    val title: String,
    val input: TextInputLabels,
    val availability: Availability,
    val submit: String,
    val info: String
)