package majestic.users.labels.profile

import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.labels.profile.security.SecurityLabels

data class UserTabLabels(
    val permissions: String,
    val contact: ContactLabels,
    val security: SecurityLabels,
    val roles: String,
) {
    companion object {
        val english by lazy {
            UserTabLabels(
                permissions = "Permissions",
                contact = ContactLabels.english,
                security = SecurityLabels.english,
                roles = "Roles",
            )
        }
        val swahili by lazy {
            UserTabLabels(
                permissions = "Ruhusa",
                contact = ContactLabels.swahili,
                security = SecurityLabels.swahili,
                roles = "Majukumu",
            )
        }
    }
}
