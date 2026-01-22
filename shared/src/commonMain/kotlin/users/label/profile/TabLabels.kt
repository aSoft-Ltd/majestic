package users.label.profile

import users.label.contacts.ContactLabels
import users.label.contacts.PermissionLabels
import users.label.general.TabSectionLabels
import users.label.profile.security.SecurityLabels

data class TabLabels(
    val permissions: TabSectionLabels<PermissionLabels>,
    val contacts: TabSectionLabels<ContactLabels>,
    val roles: String,
    val security: TabSectionLabels<SecurityLabels>
)