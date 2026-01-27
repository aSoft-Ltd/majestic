package majestic.shared.users.label.profile

import majestic.shared.users.label.contacts.ContactLabels
import majestic.shared.users.label.contacts.PermissionLabels
import majestic.shared.users.label.general.TabSectionLabels
import majestic.shared.users.label.profile.security.SecurityLabels

data class TabLabels(
    val permissions: TabSectionLabels<PermissionLabels>,
    val contacts: TabSectionLabels<ContactLabels>,
    val roles: String,
    val security: TabSectionLabels<SecurityLabels>
)