package majestic.users.labels.profile

import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.labels.profile.security.SecurityLabels

data class UserProfile(
    val contact: ContactLabels,
    val security: SecurityLabels
) {
    companion object {
        val english by lazy {
            UserProfile(
                contact = ContactLabels.english,
                security = SecurityLabels.english
            )
        }
        val swahili by lazy {
            UserProfile(
                contact = ContactLabels.swahili,
                security = SecurityLabels.swahili
            )
        }
    }
}
