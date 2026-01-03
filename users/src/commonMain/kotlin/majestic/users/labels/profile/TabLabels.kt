package majestic.users.labels.profile

import majestic.users.labels.general.TabSectionLabels
import majestic.users.labels.profile.contact.ContactLabels
import majestic.users.labels.profile.security.SecurityLabels

data class TabLabels(
    val permissions: TabSectionLabels<PermissionLabels>,
    val contacts: TabSectionLabels<ContactLabels>,
    val roles: String,
    val security: TabSectionLabels<SecurityLabels>
) {
    companion object {
        val english by lazy {
            TabLabels(
                permissions = TabSectionLabels(tab = "Permissions", content = PermissionLabels.english),
                contacts = TabSectionLabels(tab = "Contacts", content = ContactLabels.english),
                roles = "Roles",
                security = TabSectionLabels(tab = "Security", content = SecurityLabels.english)
            )
        }
        val swahili by lazy {
            TabLabels(
                permissions = TabSectionLabels(tab = "Ruhusa", content = PermissionLabels.swahili),
                contacts = TabSectionLabels(tab = "Mawasiliano", content = ContactLabels.swahili),
                roles = "Majukumu",
                security = TabSectionLabels(tab = "Usalama", content = SecurityLabels.swahili)
            )
        }
    }
}
