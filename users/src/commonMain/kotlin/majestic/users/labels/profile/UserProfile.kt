package majestic.users.labels.profile

import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.labels.profile.security.SecurityLabels
import majestic.users.labels.roles.RolesLabels

data class UserProfile(
    val permission: PermissionLabels,
    val contact: ContactLabels,
    val security: SecurityLabels,
    val roles: RolesLabels
) {
    companion object {
        val english by lazy {
            UserProfile(
                permission = PermissionLabels.english,
                contact = ContactLabels.english,
                security = SecurityLabels.english,
                roles = RolesLabels.english
            )
        }
        val swahili by lazy {
            UserProfile(
                permission = PermissionLabels.swahili,
                contact = ContactLabels.swahili,
                security = SecurityLabels.swahili,
                roles = RolesLabels.swahili
            )
        }
    }
}