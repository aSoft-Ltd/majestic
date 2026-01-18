package majestic.users.labels.profile

import majestic.users.labels.profile.security.SecurityLabels
import majestic.users.labels.general.TabSectionLabels
import majestic.users.labels.profile.contact.ContactLabels

data class TabLabels(
    val permissions: TabSectionLabels<PermissionLabels>,
    val contacts: TabSectionLabels<ContactLabels>,
    val roles: String,
    val security: TabSectionLabels<SecurityLabels>
)