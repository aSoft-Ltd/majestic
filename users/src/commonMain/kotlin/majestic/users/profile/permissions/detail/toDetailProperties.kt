package majestic.users.profile.permissions.detail

import majestic.users.tools.data.Permissions


internal fun Permissions.toDetailProperties() = DetailedProperties(
    permissions = permissions,
    trailingIcon = resource,
    trailingTitle = title
)